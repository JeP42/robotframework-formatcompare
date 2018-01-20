package com.github.jep42.formatcompare.formathandler.impl.json;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public final class JSONAccessor {


    private JSONAccessor() {
        super();
    }


    public static <T> T getJSONValue(Object jsonObject, String jsonElement) {
        return JsonPath.read(jsonObject, jsonElement);   //"$.data.data2.value"
    }


    public static Object getJSONObject(String jsonString) {
        return Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
    }


}
