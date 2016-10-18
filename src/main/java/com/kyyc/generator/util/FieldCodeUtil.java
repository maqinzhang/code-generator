package com.kyyc.generator.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldCodeUtil {

	/**
	 * @category 首字母大写
	 * @param srcStr
	 * @return
	 */
	public static String firstCharacterToUpper(String srcStr) {
		return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
	}

	/**
	 * @category 首字母小写
	 * @param srcStr
	 * @return
	 */
	public static String firstCharacterToLower(String srcStr) {
		return srcStr.substring(0, 1).toLowerCase() + srcStr.substring(1);
	}

	/**
	 * @category 替换字符串并让它的下一个字母为大写
	 * @param srcStr
	 *            替换的字符串
	 * @param org
	 *            需要替换的字符
	 * @param ob
	 *            替换后的字符
	 * @return
	 */
	public static String replaceUnderlineAndfirstToUpper(String srcStr, String org, String ob) {
		String newString = "";
		int first = 0;
		while (srcStr.indexOf(org) != -1) {
			first = srcStr.indexOf(org);
			if (first != srcStr.length()) {
				newString = newString + srcStr.substring(0, first) + ob;
				srcStr = srcStr.substring(first + org.length(), srcStr.length());
				if (first != 1) {
					srcStr = firstCharacterToUpper(srcStr);
				}
			}
		}
		newString = newString + srcStr;
		return newString;
	}

	/**
	 * @category 获取table column对应的 class field名称
	 * @param columnName
	 * @return
	 */
	public static String getFieldName(String columnName) {
		columnName = columnName.toLowerCase();
		return replaceUnderlineAndfirstToUpper(columnName, "_", "");
	}

	/**
	 * 将下划线风格替换为驼峰风格
	 */
	public static String underlineToCamelhump(String str) {
		str = str.toLowerCase();
		Matcher matcher = Pattern.compile("_[a-z]").matcher(str);
		StringBuilder builder = new StringBuilder(str);
		for (int i = 0; matcher.find(); i++) {
			builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
		}
		if (Character.isUpperCase(builder.charAt(0))) {
			builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
		}
		return builder.toString();
	}
}
