package com.format.app.util;

public class URLUtils {

	public static String getParameterValue(String url, String key) {
		
		String[] params = url.substring(url.indexOf("?")+1).split("&");
		String value = null;

		for(String pair : params) {
			String pKey = pair.substring(0, pair.indexOf("="));
			String pValues = pair.substring(pair.indexOf("=")+1);
			
			if(pKey.equals(key)) {
				value = pValues;
			}
		}
		
		return value;
	}
}
