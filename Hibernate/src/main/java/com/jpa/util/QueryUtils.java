package com.jpa.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.jpa.criteria.JoinImplementor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.NumberUtils;

import com.esotericsoftware.reflectasm.MethodAccess;

import groovy.lang.Tuple;

public class QueryUtils {

	public static final JoinType DEFAULT_JOIN_TYPE;
	public static final Pattern TENANT_ID_VALIDATOR;

	public static <T> Specification<T> newSpecification(Map<String, Object> params) {
		return buildSpecification(params);
	}

	public static <T> Specification<T> buildSpecification(Map<String, Object> params) {
		///return new MapSpecification(params);
		return null;
	}

	public static Predicate buildFinalPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder,
			Map<String, Join<?, ?>> joinMap, Map<String, Object> params) {
		if (params == null) {
			params = new HashMap();
		}

		applyDataPermissions((Map) params, root.getJavaType());
		Object distinct = ((Map) params).get("DISTINCT");
		if (distinct != null) {
			if (distinct instanceof Boolean) {
				query.distinct((Boolean) distinct);
			} else if (distinct instanceof String) {
				query.distinct(Boolean.valueOf((String) distinct));
			}

			((Map) params).remove("DISTINCT");
		}

		((Map) params).remove("IGNORETENANTID");
		if (joinMap == null) {
			joinMap = new HashMap();
		}

		if (((Map) params).containsKey("FETCH")) {
			String[] var6 = ((Map) params).get("FETCH").toString().split(",");
			int var7 = var6.length;

			for (int var8 = 0; var8 < var7; ++var8) {
				String str = var6[var8];
				JoinType joinType = DEFAULT_JOIN_TYPE;
				String fetchAttrName = str;
				if (str.contains("_")) {
					joinType = JoinType.valueOf(str.substring(str.lastIndexOf("_") + 1));
					fetchAttrName = str.substring(0, str.lastIndexOf("_"));
				}

				if (joinType.equals(JoinType.LEFT) && !query.isDistinct()) {
					query.distinct(true);
				}

				Fetch<?, ?> fetch = root.fetch(fetchAttrName, joinType);
				JoinImplementor<?, ?> joinImpl = (JoinImplementor) fetch;
				((Map) joinMap).put(root.getModel().getName() + "." + fetchAttrName, joinImpl);
			}

			((Map) params).remove("FETCH");
		}

		List<Predicate> finalPredicates = new ArrayList();
		Iterator var15 = ((Map) params).keySet().iterator();

		while (var15.hasNext()) {
			String key = (String) var15.next();
			Object value = ((Map) params).get(key);
			if (value != null) {
				finalPredicates.add(createPredicate(root, query, builder, (Map) joinMap, key, value));
			}
		}

		return builder.and((Predicate[]) finalPredicates.toArray(new Predicate[0]));
	}

	private static String replaceORSymbol(String key) {
		return key.replace("_OR_", "||").replace("_AND_", "&&");
	}

	private static String replaceSymbolInBrackets(String key) {
		List<Integer[]> braketsList = new ArrayList();
		Integer[] brakets = new Integer[2];

		for (int i = 0; i < key.length(); ++i) {
			if (key.charAt(i) == '(') {
				if (brakets[0] != null) {
					throw new IllegalArgumentException(
							"Does not support nesting brackets in search condition key \"" + key + "\".");
				}

				brakets[0] = i;
			} else if (key.charAt(i) == ')') {
				brakets[1] = i;
				if (brakets[0] == null) {
					throw new IllegalArgumentException(
							"Search condition key \"" + key + "\" got the wrong brackets position.");
				}

				braketsList.add(brakets);
				brakets = new Integer[2];
			}
		}

		String temp = "temp_string_for_replace_symbol";

		for (int i = braketsList.size() - 1; i >= 0; --i) {
			Integer[] braketArray = (Integer[]) braketsList.get(i);
			String partKey = key.substring(braketArray[0] + 1, braketArray[1]);
			key = key.replace(partKey, temp);
			partKey = partKey.replaceAll("\\|\\|", "\\|").replaceAll("&&", "&");
			key = key.replace(temp, partKey);
		}

		return key;
	}

	private static String getJoinMapKey(Root<?> root, String[] paths, int index) {
		String joinMapKey = "";

		for (int i = 0; i <= index; ++i) {
			joinMapKey = joinMapKey + "." + paths[i].split("@")[0];
		}

		joinMapKey = root.getJavaType().getSimpleName() + joinMapKey;
		return joinMapKey;
	}

	public static Path<?> queryPath(Root<?> root, CriteriaBuilder builder, Map<String, Join<?, ?>> joinMap,
			String fullPath) {
		fullPath = fullPath.replaceAll("\\.", "_");
		String[] paths = fullPath.split("_");
		Path<?> path = root;
		Join<?, ?> currentJoin = null;

		for (int i = 0; i < paths.length; ++i) {
			String pathStr = paths[i];
			String joinMapKey = getJoinMapKey(root, paths, i);
			if (i == paths.length - 1) {
				if (pathStr.contains("@")) {
					pathStr = pathStr.split("@")[0];
				}

				if (currentJoin == null) {
					path = root.get(pathStr);
				} else {
					path = currentJoin.get(pathStr);
				}
			} else {
				Join<?, ?> join = (Join) joinMap.get(joinMapKey);
				JoinType joinType = DEFAULT_JOIN_TYPE;
				if (pathStr.contains("@")) {
					joinType = JoinType.valueOf(pathStr.split("@")[1]);
					pathStr = pathStr.split("@")[0];
				}

				if (join == null && i == 0) {
					join = root.join(pathStr, joinType);
				} else if (join == null) {
					join = (Join) joinMap.get(getJoinMapKey(root, paths, i - 1));
					join = join.join(pathStr, joinType);
				}

				currentJoin = join;
				joinMap.put(joinMapKey, join);
			}
		}

		return (Path) path;
	}

	public static <T> Path<T> queryPath(Root<T> root, String name, Map<String, Join> joinMap) {
		String[] names = name.split("_|\\.");
		Path expression = null;
		int i;
		if (names.length >= 2 && joinMap != null && joinMap.containsKey(names[0])) {
			expression = ((Join) joinMap.get(names[0])).get(names[1]);

			for (i = 2; i < names.length; ++i) {
				if (joinMap.containsKey(names[i - 1])) {
					expression = ((Join) joinMap.get(names[i - 1])).get(names[i]);
				} else {
					expression = expression.get(names[i]);
				}
			}
		} else {
			expression = root.get(names[0]);

			for (i = 1; i < names.length; ++i) {
				expression = expression.get(names[i]);
			}
		}

		return expression;
	}

	public static String appendLikeWild(Object val) {
		String str = val.toString();
		return str.indexOf("%") != -1 ? str : "%" + str + "%";
	}

	private static Object getValue(String key, Object[] values, Object value, int i, boolean isArray, Root<?> root,
			CriteriaQuery<?> query, CriteriaBuilder builder, Map<String, Join<?, ?>> joinMap) {
		Object currentValue = isArray ? values[i] : value;
		if (currentValue != null) {
			String stringValue = currentValue.toString();
			if (stringValue.startsWith("$")) {
				currentValue = queryPath(root, builder, joinMap, stringValue.substring(1, stringValue.length()));
			} else if (!stringValue.equalsIgnoreCase("true") && !stringValue.equalsIgnoreCase("false")) {
				if (isTime(key, root, builder, joinMap) && TimeUtils.matchCommonTimePattern(stringValue)) {
					try {
						currentValue = TimeUtils.toCalendar(stringValue, "yyyy-MM-dd HH:mm:ss");
					} catch (ParseException var13) {
						throw new RuntimeException(var13);
					}
				} else if (isTime(key, root, builder, joinMap) && TimeUtils.matchCommonDatePattern(stringValue)) {
					try {
						currentValue = TimeUtils.toCalendar(stringValue, "yyyy-MM-dd");
					} catch (ParseException var12) {
						throw new RuntimeException(var12);
					}
				}
			} else {
				currentValue = Boolean.valueOf(stringValue);
			}
		}

		return currentValue;
	}

	private static boolean isTime(String key, Root<?> root, CriteriaBuilder builder, Map<String, Join<?, ?>> joinMap) {
		if (key.contains("EXISTS")) {
			return false;
		} else {
			String fullPath = key.substring(key.indexOf("_") + 1, key.length());
			Path<?> path = queryPath(root, builder, joinMap, fullPath);
			Class<?> javaType = path.getJavaType();
			return Date.class.isAssignableFrom(javaType) || Calendar.class.isAssignableFrom(javaType)
					|| java.sql.Date.class.isAssignableFrom(javaType);
		}
	}

	private static Predicate createSubPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder,
			Map<String, Join<?, ?>> joinMap, String key, Object value) {
		Predicate predicate = buildPredicate(root, query, builder, joinMap, key,
				createSubquery(root, query, builder, joinMap, key, value));
		return predicate;
	}

	public static Subquery<?> createSubquery(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder,
			Map<String, Join<?, ?>> joinMap, String key, Object value) {
		JpaSubquery jpaSubquery = null;
		if (!(value instanceof JpaSubquery)) {
			throw new IllegalArgumentException(
					"Value of subquery " + key + " is not a JpaSubquery object or a Map object.");
		} else {
			jpaSubquery = (JpaSubquery) value;
			if (jpaSubquery.getEntityClass() == null) {
				throw new IllegalArgumentException(
						"Entity type named " + jpaSubquery.getEntityClass().getName() + "is not found.");
			} else {
				Map<String, Object> params = jpaSubquery.getParams();
				Subquery subquery = query.subquery(jpaSubquery.getEntityClass());
				Root subRoot = subquery.from(jpaSubquery.getEntityClass());
				List<Predicate> subPredicates = new ArrayList();
				Iterator var11 = params.keySet().iterator();

				while (var11.hasNext()) {
					String subKey = (String) var11.next();
					Object subKeyValue = params.get(subKey);
					subPredicates.add(createPredicate(subRoot, query, builder, joinMap, subKey, subKeyValue));
				}

				subquery.where(builder.and((Predicate[]) subPredicates.toArray(new Predicate[0])));
				if (StringUtils.isEmpty(jpaSubquery.getSelectionField())) {
					subquery.select(subRoot);
				} else {
					Path selectionPath = queryPath(subRoot, builder, joinMap, jpaSubquery.getSelectionField());
					Expression selectionExp = null;
					if (jpaSubquery.getSelectionType() != null) {
						switch (jpaSubquery.getSelectionType().ordinal()) {
						case 1:
							selectionExp = builder.count(selectionPath);
							break;
						case 2:
							selectionExp = builder.min(selectionPath);
							break;
						case 3:
							selectionExp = builder.max(selectionPath);
							break;
						case 4:
							selectionExp = builder.avg(selectionPath);
							break;
						case 5:
							selectionExp = builder.sum(selectionPath);
							break;
						case 6:
							selectionExp = builder.lower(selectionPath);
							break;
						case 7:
							selectionExp = builder.upper(selectionPath);
						}
					} else {
						selectionExp = selectionPath;
					}

					subquery.select((Expression) selectionExp);
				}

				return subquery;
			}
		}
	}

	private static Predicate buildPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder,
			Map<String, Join<?, ?>> joinMap, String key, Object value) {
		if (value instanceof JpaSubquery) {
			return createSubPredicate(root, query, builder, joinMap, key, value);
		} else {
			if (value instanceof Subquery) {
				;
			}

			Predicate predicate = null;
			if (key.contains("EXISTS")) {
				if (key.startsWith("EXISTS")) {
					return Operator.EXISTS.explain((Expression) value, builder, value);
				}

				if (key.startsWith("NOTEXISTS")) {
					return Operator.NOT_EXISTS.explain((Expression) value, builder, value);
				}
			}

			String opretor = key.substring(0, key.indexOf("_"));
			String fullPath = key.substring(key.indexOf("_") + 1, key.length());
			Path<?> path = queryPath(root, builder, joinMap, fullPath);
			if (String.class.isAssignableFrom(path.getJavaType()) && value != null
					&& NumberUtils.isNumber(value.toString())) {
				value = value.toString();
			}

			if ("EQ".equals(opretor)) {
				predicate = Operator.EQUAL.explain(path, builder, value);
			} else if ("LIKE".equals(opretor)) {
				predicate = Operator.LIKE.explain(path, builder, value);
			} else if ("NOTLIKE".equals(opretor)) {
				predicate = Operator.NOT_LIKE.explain(path, builder, value);
			} else if ("GT".equals(opretor)) {
				predicate = Operator.GREATER_THAN.explain(path, builder, value);
			} else if ("GE".equals(opretor)) {
				predicate = Operator.GREATER_THAN_OR_EQUAL.explain(path, builder, value);
			} else if ("LT".equals(opretor)) {
				predicate = Operator.LESS_THAN.explain(path, builder, value);
			} else if ("LE".equals(opretor)) {
				predicate = Operator.LESS_THAN_OR_EQUAL.explain(path, builder, value);
			} else if ("NE".equals(opretor)) {
				predicate = Operator.NOT_EQUAL.explain(path, builder, value);
			} else if ("IN".equals(opretor)) {
				predicate = Operator.IN.explain(path, builder, value);
			} else if ("NOTIN".equals(opretor)) {
				predicate = Operator.NOT_IN.explain(path, builder, value);
			} else if ("SIZE".equals(opretor)) {
				predicate = Operator.SIZE_OF.explain(path, builder, value);
			} else if ("NOTSIZE".equals(opretor)) {
				predicate = Operator.NOT_SIZE_OF.explain(path, builder, value);
			} else if ("IS".equals(opretor) && value != null) {
				if ("NULL".equals(value.toString())) {
					predicate = Operator.NULL.explain(path, builder, value);
				} else if ("NOTNULL".equals(value.toString())) {
					predicate = Operator.NOT_NULL.explain(path, builder, value);
				} else if ("BLANK".equals(value.toString())) {
					predicate = Operator.BLANK.explain(path, builder, value);
				} else if ("NOTBLANK".equals(value.toString())) {
					predicate = Operator.NOT_BLANK.explain(path, builder, value);
				} else if ("TRUE".equals(value.toString())) {
					predicate = Operator.TRUE.explain(path, builder, value);
				} else if ("FALSE".equals(value.toString())) {
					predicate = Operator.FALSE.explain(path, builder, value);
				}
			}

			return predicate;
		}
	}

	private static Predicate createPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder,
			Map<String, Join<?, ?>> joinMap, String key, Object value) {
		key = replaceORSymbol(key);
		key = replaceSymbolInBrackets(key);
		List<Predicate> orPs = new ArrayList();
		String[] ors = key.split("\\|\\|");
		int i = 0;
		Object[] values = null;
		boolean isArray = false;
		if (value != null && value.getClass().isArray()) {
			isArray = key.contains("&") || key.contains("|");
			values = (Object[]) ((Object[]) value);
		} else if (value != null && List.class.isAssignableFrom(value.getClass())) {
			List<Object> list = (List) value;
			isArray = key.contains("&") || key.contains("|");
			values = list.toArray();
		}

		String[] var34 = ors;
		int var12 = ors.length;

		for (int var13 = 0; var13 < var12; ++var13) {
			String or = var34[var13];
			or = or.trim();
			String[] ands = or.split("&&");
			List<Predicate> andPs = new ArrayList();
			String[] var17 = ands;
			int var18 = ands.length;

			for (int var19 = 0; var19 < var18; ++var19) {
				String and = var17[var19];
				and = and.trim();
				if (and.startsWith("(") && and.endsWith(")")) {
					and = and.substring(1, and.length() - 1).trim();
					String[] subAnds;
					String[] var23;
					int var24;
					int var25;
					String subAnd;
					ArrayList subOrPs;
					if (and.contains("|")) {
						subOrPs = new ArrayList();
						subAnds = and.split("\\|");
						var23 = subAnds;
						var24 = subAnds.length;

						for (var25 = 0; var25 < var24; ++var25) {
							subAnd = var23[var25];
							subAnd = subAnd.trim();
							if (!Operator.isQualifiedOperator(and.split("_")[0])) {
								subAnd = ands[0].split("_")[0] + "_" + subAnd;
							}

							String[] subAnds = subAnd.split("&");
							List<Predicate> subAndPs = new ArrayList();
							String[] var29 = subAnds;
							int var30 = subAnds.length;

							for (int var31 = 0; var31 < var30; ++var31) {
								String subAnd = var29[var31];
								subAnd = subAnd.trim();
								Object currentValue = getValue(subAnd, values, value, i, isArray, root, query, builder,
										joinMap);
								subAndPs.add(buildPredicate(root, query, builder, joinMap, subAnd, currentValue));
								++i;
							}

							Predicate finalSubAnd = builder.and((Predicate[]) subAndPs.toArray(new Predicate[0]));
							subOrPs.add(finalSubAnd);
						}

						andPs.add(builder.or((Predicate[]) subOrPs.toArray(new Predicate[0])));
					} else if (and.contains("&")) {
						subOrPs = new ArrayList();
						subAnds = and.split("&");
						var23 = subAnds;
						var24 = subAnds.length;

						for (var25 = 0; var25 < var24; ++var25) {
							subAnd = var23[var25];
							subAnd = subAnd.trim();
							if (!Operator.isQualifiedOperator(and.split("_")[0])) {
								subAnd = ands[0].split("_")[0] + "_" + subAnd;
							}

							Object currentValue = getValue(subAnd, values, value, i, isArray, root, query, builder,
									joinMap);
							subOrPs.add(buildPredicate(root, query, builder, joinMap, subAnd, currentValue));
							++i;
						}

						andPs.add(builder.and((Predicate[]) subOrPs.toArray(new Predicate[0])));
					}
				} else {
					if (!Operator.isQualifiedOperator(and.split("_")[0])) {
						and = ors[0].split("_")[0] + "_" + and;
					}

					Object currentValue = getValue(and, values, value, i, isArray, root, query, builder, joinMap);
					andPs.add(buildPredicate(root, query, builder, joinMap, and, currentValue));
					++i;
				}
			}

			orPs.add(builder.and((Predicate[]) andPs.toArray(new Predicate[0])));
		}

		return builder.or((Predicate[]) orPs.toArray(new Predicate[0]));
	}

	public static <T> Page<T> convertTuple(Page<Tuple> tuplePage, Page<T> resultPage, Class<T> domainClass,
			List<String> columns) {
		List<Tuple> tuples = tuplePage.getRecords();
		List<T> list = convertTuple(tuples, domainClass, columns);
		resultPage.setRecords(list);
		resultPage.setTotalCount(tuplePage.getTotalCount());
		return resultPage;
	}

	public static <T> List<T> convertTuple(List<Tuple> tuples, Class<T> resultType, List<String> columns) {
		List<T> list = new ArrayList();
		List<Integer> setterIndexes = new ArrayList();
		MethodAccess ma = MethodAccess.get(resultType);

		String currentColumn;
		for (int i = 0; i < columns.size(); ++i) {
			String column = (String) columns.get(i);

			try {
				currentColumn = "set" + (column.charAt(0) + "").toUpperCase() + column.substring(1, column.length());
				setterIndexes.add(ma.getIndex(currentColumn));
			} catch (Exception var14) {
				if (!column.startsWith("is")) {
					throw new RuntimeException(var14);
				}

				column = column.substring(2, column.length());
				String setterName = "set" + (column.charAt(0) + "").toUpperCase()
						+ column.substring(1, column.length());

				try {
					setterIndexes.add(ma.getIndex(setterName));
				} catch (Exception var12) {
					throw new RuntimeException(var12);
				}
			}
		}

		Iterator var15 = tuples.iterator();

		while (var15.hasNext()) {
			Tuple tuple = (Tuple) var15.next();
			currentColumn = null;

			try {
				T entity = resultType.newInstance();
				Object[] values = tuple.toArray();

				for (int i = 0; i < columns.size(); ++i) {
					currentColumn = (String) columns.get(i);
					ma.invoke(entity, (Integer) setterIndexes.get(i), new Object[] { values[i] });
				}

				list.add(entity);
			} catch (Exception var13) {
				throw new RuntimeException("Convert tuple failed at column[" + currentColumn
						+ "], Maybe type mismatched or convert null value to basic types like int, boolean e.g. Maybe you modified result type ["
						+ resultType
						+ "] without restart application, such as add/remove field. Please restart application and try again. Original error message: "
						+ ExceptionUtils.getStackTrace(var13));
			}
		}

		return list;
	}

	public static <T> T convertTuple(Tuple tuple, Class<T> resultType, List<String> columns) {
		List<Integer> setterIndexes = new ArrayList();
		MethodAccess ma = MethodAccess.get(resultType);

		for (int i = 0; i < columns.size(); ++i) {
			String column = (String) columns.get(i);

			try {
				String setterName = "set" + (column.charAt(0) + "").toUpperCase()
						+ column.substring(1, column.length());
				setterIndexes.add(ma.getIndex(setterName));
			} catch (Exception var12) {
				if (!column.startsWith("is")) {
					throw new RuntimeException(var12);
				}

				column = column.substring(2, column.length());
				String setterName = "set" + (column.charAt(0) + "").toUpperCase()
						+ column.substring(1, column.length());

				try {
					setterIndexes.add(ma.getIndex(setterName));
				} catch (Exception var10) {
					throw new RuntimeException(var10);
				}
			}
		}

		String currentColumn = null;

		try {
			T entity = resultType.newInstance();
			Object[] values = tuple.toArray();

			for (int i = 0; i < columns.size(); ++i) {
				currentColumn = (String) columns.get(i);
				ma.invoke(entity, (Integer) setterIndexes.get(i), new Object[] { values[i] });
			}

			return entity;
		} catch (Exception var11) {
			throw new RuntimeException("Convert tuple failed at column[" + currentColumn
					+ "], Maybe type mismatched or convert null value to basic types like int, boolean e.g. Maybe you modified result type ["
					+ resultType
					+ "] without restart application, such as add/remove field. Please restart application and try again. Original error message: "
					+ ExceptionUtils.getStackTrace(var11));
		}
	}

	public static Page<Map<String, Object>> convertTupleToMap(Page<Tuple> tuplePage,
			Page<Map<String, Object>> resultPage, List<String> columns) {
		List<Tuple> tuples = tuplePage.getRecords();
		List<Map<String, Object>> list = convertTupleToMap(tuples, columns);
		resultPage.setRecords(list);
		resultPage.setTotalCount(tuplePage.getTotalCount());
		return resultPage;
	}

	public static List<Map<String, Object>> convertTupleToMap(List<Tuple> tuples, List<String> columns) {
		return convertTupleToMap(tuples, columns, columns);
	}

	public static List<Map<String, Object>> convertTupleToMap(List<Tuple> tuples, List<String> columns,
			List<String> aliases) {
		if (columns.size() != aliases.size()) {
			throw new RuntimeException("Aliases sized and columns size mismatched.");
		} else {
			List<Map<String, Object>> list = new ArrayList();
			Iterator var4 = tuples.iterator();

			while (var4.hasNext()) {
				Tuple tuple = (Tuple) var4.next();
				Map<String, Object> map = new HashMap();
				Object[] values = tuple.toArray();

				for (int i = 0; i < aliases.size(); ++i) {
					map.put(aliases.get(i), values[i]);
				}

				list.add(map);
			}

			return list;
		}
	}

	public static <S> List<S> convertSqlResult(List<Object[]> resultSet, List<String> columns, Class<S> resultType) {
		List<S> result = new ArrayList();
		List<Integer> setterIndexes = new ArrayList();
		MethodAccess ma = MethodAccess.get(resultType);

		for (int i = 0; i < columns.size(); ++i) {
			String column = (String) columns.get(i);

			try {
				setterIndexes.add(ma.getIndex(
						"set" + (column.charAt(0) + "").toUpperCase() + column.substring(1, column.length())));
			} catch (SecurityException var10) {
				throw new RuntimeException(var10);
			}
		}

		try {
			Object entity;
			Iterator var12;
			if (columns.size() == 1) {
				var12 = resultSet.iterator();

				while (var12.hasNext()) {
					Object value = var12.next();
					entity = resultType.newInstance();
					ma.invoke(entity, (Integer) setterIndexes.get(0), new Object[] { convertSqlValue(value) });
					result.add(entity);
				}
			} else {
				var12 = resultSet.iterator();

				while (var12.hasNext()) {
					Object[] values = (Object[]) var12.next();
					entity = resultType.newInstance();

					for (int i = 0; i < columns.size(); ++i) {
						ma.invoke(entity, (Integer) setterIndexes.get(i), new Object[] { convertSqlValue(values[i]) });
					}

					result.add(entity);
				}
			}

			return result;
		} catch (IllegalAccessException | IllegalArgumentException | InstantiationException var11) {
			throw new RuntimeException("Convert tuple failed, Maybe you modified result type [" + resultType
					+ "] without restart application, such as add/remove field e.g. Please restart application and try again. Original error message: "
					+ var11.getMessage());
		}
	}

	public static Object convertSqlValue(Object value) {
		if (value != null) {
			if (Timestamp.class.isAssignableFrom(value.getClass())) {
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(((Timestamp) value).getTime());
				return c;
			}

			if (BigInteger.class.isAssignableFrom(value.getClass())) {
				return new Long(((BigInteger) value).longValueExact());
			}
		}

		return value;
	}

	public static Sort buildSort(String... sorts) {
		return orderBy(sorts);
	}

	public static Sort orderBy(String... sorts) {
		Sort sort = null;
		if (sorts != null && sorts.length > 0) {
			List<Order> orders = new ArrayList();
			String[] var3 = sorts;
			int var4 = sorts.length;

			for (int var5 = 0; var5 < var4; ++var5) {
				String pd = var3[var5];
				String[] pds = pd.split(",");
				String prop = pds[0].trim();
				Direction direction = Direction.ASC;
				if (pds.length == 2) {
					direction = Direction.valueOf(pds[1].trim().toUpperCase());
				}

				Order order = new Order(direction, prop);
				orders.add(order);
			}

			sort = Sort.by(orders);
		}

		return sort;
	}

	public static boolean isConditionEmpty(Map<String, Object> params) {
		if (params == null) {
			return true;
		} else if (params.containsKey("EMPTY")) {
			return true;
		} else {
			Set<String> keys = params.keySet();
			Set<String> removes = new HashSet();
			Iterator var3 = keys.iterator();

			while (true) {
				String key;
				do {
					if (!var3.hasNext()) {
						if (keys.size() == removes.size()) {
							return true;
						}

						boolean allNull = true;
						Iterator var8 = keys.iterator();

						while (var8.hasNext()) {
							String key = (String) var8.next();
							Object value = params.get(key);
							if (value != null) {
								allNull = false;
								break;
							}
						}

						return allNull;
					}

					key = (String) var3.next();
				} while (!key.equals("DISTINCT") && !key.startsWith("FETCH") && !key.equals("IGNORETENANTID"));

				removes.add(key);
			}
		}
	}

	public static <T> Map<String, Object> applyDataPermissions(Map<String, Object> params, Class<T> entityType) {
		Object ignored = params.get("IGNOREDATAPERMISSIONS");
		List<String> ignoreFields = new ArrayList();
		if (ignored != null) {
			if ("ALL".equals(ignored.toString().trim())) {
				params.remove("IGNOREDATAPERMISSIONS");
				return params;
			}

			ignoreFields = Arrays.asList(ignored.toString().split(","));
			if (((List) ignoreFields).size() > 0) {
			}
		}

		Map<String, Object> dataPermissions = DataPermissionUtils.buildParams(entityType, (List) ignoreFields);
		params.putAll(dataPermissions);
		params.remove("IGNOREDATAPERMISSIONS");
		return params;
	}

	public static String reconstractSqlForColumns(String sql, List<String> columns) {
		return sql.replaceFirst("\\*", StringUtils.join(columns, ","));
	}

	public static String addTenantIdConditionToSQL(String sql) {
		if (sql.toLowerCase().contains("where")) {
			;
		}

		return sql;
	}

	public static String addTenantIdConditionToHQL(String hql) {
		if (hql.toLowerCase().contains("where")) {
			;
		}

		return hql;
	}

	public static boolean hasTenantIdCondition(String sql) {
		return TENANT_ID_VALIDATOR.matcher(sql).find();
	}

	static {
		DEFAULT_JOIN_TYPE = JoinType.LEFT;
		TENANT_ID_VALIDATOR = Pattern.compile("(?i)tenantId\\s*=\\s*");
	}

}
