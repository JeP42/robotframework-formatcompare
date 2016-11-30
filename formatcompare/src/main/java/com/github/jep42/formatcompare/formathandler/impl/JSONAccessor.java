package com.github.jep42.formatcompare.formathandler.impl;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public final class JSONAccessor {


	private JSONAccessor() {
		super();
	}


	public static String getJSONValue(Object jsonObject, String jsonElement) {
		return JsonPath.read(jsonObject, jsonElement);   //"$.data.data2.value"
	}

	public static long getJSONValueAsLong(Object jsonObject, String jsonElement) {
		return JsonPath.read(jsonObject, jsonElement);
	}

	public static Object getJSONObject(String jsonString) {
		return Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
	}


}