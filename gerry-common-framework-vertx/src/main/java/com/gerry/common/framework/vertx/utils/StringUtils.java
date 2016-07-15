package com.gerry.common.framework.vertx.utils;

import java.util.Objects;

public abstract class StringUtils {

	public static String replace(String value, Object... args){
		Objects.requireNonNull(value, " value is null");
		StringBuffer sb = new StringBuffer();
		String[] values = value.split("\\?");
		int i = 0;
		for (String val : values) {
			if(VertxEmptyUtils.isEmpty(val.trim())){
				continue;
			}
			sb.append(val).append(args[i]);
			i++;
		}
		return sb.toString();
	}

	public static String trimAllWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		int len = str.length();
		StringBuilder sb = new StringBuilder(str.length());
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (!Character.isWhitespace(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}
	
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

}
