package io.github.simplefixture;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CacheContext {
    private static final Map<Class<?>, Object> fixtureMapCache = new LinkedHashMap<>();
    private static final Map<Integer, MetaCache> fieldMapCache = new LinkedHashMap<>();

    protected static void clear(){
        fixtureMapCache.clear();
        fixtureMapCache.clear();
    }

    protected static void cache(Class<?> key, Object value){
        fixtureMapCache.put(key, value);
    }

    protected static void cache(Field field){
        int key = field.getDeclaringClass().hashCode()+field.hashCode();
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
        int key = field.getDeclaringClass().hashCode()+field.hashCode();
        return fieldMapCache.get(key);
    }

}





