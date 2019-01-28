package com.demo.api.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author Lye
 */
public class GsonUtils {
    private GsonUtils(){}


    private static Gson gson = new Gson();

    /**
     * 将对象转为json
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * 将对象转为json，包含null值
     *
     * @param object
     * @return
     */
    public static String toJsonWithNulls(Object object) {
        return new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create().toJson(object);
    }

    /**
     * json转实体类
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * json转实体类
     *
     * @param json
     * @param typeToken
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, TypeToken<?> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }
}
