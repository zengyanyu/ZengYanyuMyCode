package com.hibernate.config;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.criterion.NullExpression;
import org.hibernate.criterion.PropertyExpression;
import org.hibernate.criterion.SimpleExpression;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月22日 下午7:22:54
 * @see com.hibernate.config.InterpreterFactoryImpl.java
 */
@SuppressWarnings("all")
public class InterpreterFactoryImpl implements InterpreterFactory {

	protected static InterpreterFactoryImpl instance = new InterpreterFactoryImpl();
	protected Map<Class, Interpreter> map = new HashMap<Class, Interpreter>();

	private InterpreterFactoryImpl() {
		map.put(BetweenExpression.class, new BetweenInterpreter());
		map.put(WsdCriteria.class, new CriteriaInterpreter());
		map.put(InExpression.class, new InInterpreter());
		map.put(NotInExpression.class, new NotInInterpreter());
		map.put(LikeExpression.class, new LikeInterpreter());
		map.put(LogicalExpression.class, new LogicalInterpreter());
		map.put(NotExpression.class, new NotInterpreter());
		map.put(NotNullExpression.class, new NotNullInterpreter());
		map.put(NullExpression.class, new NullInterpreter());
		map.put(SimpleExpression.class, new SimpleInterpreter());
		map.put(PropertyExpression.class, new PropertyInterpreter());
		map.put(Order.class, new OrderInterpreter());
		map.put(QueryEntity.class, new InjectEntityInterpreter());
		map.put(Group.class, new GroupInterpreter());
		map.put(Having.class, new HavingInterpreter());
		map.put(Boolean.class, new DistinctInterpreter());
		map.put(OrExpression.class, new OrInterpreter());
	}

	public static InterpreterFactoryImpl getInstance() {
		return instance;
	}

	public void addMap(Class clazz, Interpreter interpreter) {
		map.put(clazz, interpreter);
	}

	public void addMap(String clazz, Interpreter interpreter) throws ClassNotFoundException {
		Class aClass = Class.forName(clazz);
		addMap(aClass, interpreter);
	}

	/**
	 * 根据表达式获取表达式的解析器。
	 *
	 * @param e 表达式。
	 * @return 表达式解析器。
	 */
	@Override
	public Interpreter getInterpreter(Object e) {
		return map.get(e.getClass());
	}

}
