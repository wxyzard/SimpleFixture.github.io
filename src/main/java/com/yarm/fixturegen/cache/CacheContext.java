package com.yarm.fixturegen.cache;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CacheContext {
    private static final Map<Class<?>, Object> fixtureMapCache = new LinkedHashMap<>();
    private static final Map<String, MetaCache> fieldMapCache = new LinkedHashMap<>();

    public static void cache(Class<?> key, Object value){
        fixtureMapCache.put(key, value);
    }

    public static void cache(Field field){
        String key = field.getDeclaringClass().getName() + field.getName();
        MetaCache metaCache = fieldMapCache.get(key);
        if(metaCache==null){
            metaCache = new MetaCache(field);
        }else{
            metaCache.increaseAssignCount();
        }
        fieldMapCache.put(key, metaCache);
    }

    public static boolean exist(Class<?> key){
        return fixtureMapCache.get(key)!=null;
    }

    public static Object get(Class<?> key){
        return fixtureMapCache.get(key);
    }

    public static MetaCache get(Field field){
        String key = field.getDeclaringClass().getName() + field.getName();
        return fieldMapCache.get(key);
    }





}



