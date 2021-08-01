package com.yarm.fixturegen.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheContext {
    private static final Map<Class<?>, Object> fixtureMapCache = new LinkedHashMap<>();

    public static Object cache(Class<?> key, Object value){
        Object o = value;
        fixtureMapCache.put(key, o);
        return o;
    }

    public static boolean exist(Class<?> key){
        return fixtureMapCache.get(key)!=null;
    }

    public static Object get(Class<?> key){
        return fixtureMapCache.get(key);
    }

}

