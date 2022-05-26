package com.jpa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class StringUtils {

	private static final String FOLDER_SEPARATOR = "/";
	private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
	private static final String TOP_PATH = "..";
	private static final String CURRENT_PATH = ".";

	public static String[] toStringArray(Collection<String> collection) {
		return collection == null ? null : (String[]) collection.toArray(new String[collection.size()]);
	}

	public static String deleteAny(String inString, String charsToDelete) {
		if (hasLength(inString) && hasLength(charsToDelete)) {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < inString.length(); ++i) {
				char c = inString.charAt(i);
				if (charsToDelete.indexOf(c) == -1) {
					sb.append(c);
				}
			}

			return sb.toString();
		} else {
			return inString;
		}
	}

	public static String replace(String inString, String oldPattern, String newPattern) {
		if (hasLength(inString) && hasLength(oldPattern) && newPattern != null) {
			StringBuilder sb = new StringBuilder();
			int pos = 0;
			int index = inString.indexOf(oldPattern);

			for (int patLen = oldPattern.length(); index >= 0; index = inString.indexOf(oldPattern, pos)) {
				sb.append(inString.substring(pos, index));
				sb.append(newPattern);
				pos = index + patLen;
			}

			sb.append(inString.substring(pos));
			return sb.toString();
		} else {
			return inString;
		}
	}

	public static boolean hasLength(CharSequence str) {
		return str != null && str.length() > 0;
	}

	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}

	public static String applyRelativePath(String path, String relativePath) {
		int separatorIndex = path.lastIndexOf("/");
		if (separatorIndex != -1) {
			String newPath = path.substring(0, separatorIndex);
			if (!relativePath.startsWith("/")) {
				newPath = newPath + "/";
			}

			return newPath + relativePath;
		} else {
			return relativePath;
		}
	}

	public static String getFilename(String path) {
		if (path == null) {
			return null;
		} else {
			int separatorIndex = path.lastIndexOf("/");
			return separatorIndex != -1 ? path.substring(separatorIndex + 1) : path;
		}
	}

	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens,
			boolean ignoreEmptyTokens) {
		if (str == null) {
			return null;
		} else {
			StringTokenizer st = new StringTokenizer(str, delimiters);
			ArrayList tokens = new ArrayList();

			while (true) {
				String token;
				do {
					if (!st.hasMoreTokens()) {
						return toStringArray(tokens);
					}

					token = st.nextToken();
					if (trimTokens) {
						token = token.trim();
					}
				} while (ignoreEmptyTokens && token.length() <= 0);

				tokens.add(token);
			}
		}
	}

	public static String join(List<?> list, String sparator) {
		return join(list.toArray(), sparator, 0, list.size());
	}

	public static String join(Object[] array, String separator) {
		return join(array, separator, 0, array.length);
	}

	public static String join(Object[] array, String separator, String prefix, String suffix) {
		return join(array, separator, 0, array.length, prefix, suffix);
	}

	public static String join(final Object[] array, final String separator, final int startIndex, final int endIndex) {
		return join(array, separator, startIndex, endIndex, "", "");
	}

	public static String join(final Object[] array, final String separator, final int startIndex, final int endIndex,
			String prefix, String suffix) {
		if (array == null) {
			return null;
		} else {
			int noOfItems = endIndex - startIndex;
			if (noOfItems <= 0) {
				return "";
			} else {
				StringBuilder buf = new StringBuilder(noOfItems * 16);

				for (int i = startIndex; i < endIndex; ++i) {
					if (i > startIndex) {
						buf.append(separator);
					}

					if (array[i] != null) {
						buf.append(prefix).append(array[i]).append(suffix);
					}
				}

				return buf.toString();
			}
		}
	}

	public static boolean hasText(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		} else {
			int strLen = str.length();

			for (int i = 0; i < strLen; ++i) {
				if (!Character.isWhitespace(str.charAt(i))) {
					return true;
				}
			}

			return false;
		}
	}

	public static boolean hasText(String str) {
		return hasText((CharSequence) str);
	}

	public static boolean isBlank(String str) {
		return !hasText(str);
	}

	public static boolean isNotBlank(String str) {
		return hasText(str);
	}

	public static boolean isEmpty(String str) {
		return !hasLength(str);
	}

	public static boolean isNotEmpty(String str) {
		return hasLength(str);
	}

	private static String typeArrayString(List<Class<?>> all, String delim) {
		return typeArrayString(all, delim, "", "");
	}

	private static String typeArrayString(List<Class<?>> coll, String delim, String prefix, String suffix) {
		if (coll != null && !coll.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			Iterator it = coll.iterator();

			while (it.hasNext()) {
				sb.append(prefix).append(((Class) it.next()).getName()).append(suffix);
				if (it.hasNext()) {
					sb.append(delim);
				}
			}

			return sb.toString();
		} else {
			return "";
		}
	}

	public static boolean equals(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
	}

	public static List<String> resolveResources(String text, String prefix) {
		if (isBlank(text)) {
			return Collections.emptyList();
		} else {
			String[] parts = text.split("\\" + prefix + "\\{");
			List<String> list = new ArrayList();
			String[] var4 = parts;
			int var5 = parts.length;

			for (int var6 = 0; var6 < var5; ++var6) {
				String part = var4[var6];
				if (part.contains("}")) {
					part = part.substring(0, part.indexOf("}"));
					list.add(part);
				}
			}

			return list;
		}
	}

	public static String getString(Object object) {
		return getString(object, "");
	}

	public static String getString(Object object, String defaultValue) {
		if (object == null) {
			return defaultValue;
		} else {
			String value = object.toString();
			if (isBlank(value)) {
				value = defaultValue;
			}

			return value;
		}
	}

	public static String lowerFirstChar(String s) {
		if (isBlank(s)) {
			return s;
		} else {
			return s.length() == 1 ? s.toLowerCase() : s.substring(0, 1).toLowerCase() + s.substring(1, s.length());
		}
	}

	public static String upperFirstChar(String s) {
		if (isBlank(s)) {
			return s;
		} else {
			return s.length() == 1 ? s.toUpperCase() : s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
		}
	}

}
