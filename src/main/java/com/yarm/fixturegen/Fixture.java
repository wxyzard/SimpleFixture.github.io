package com.yarm.fixturegen;

import com.yarm.fixturegen.cache.CacheContext;
import com.yarm.fixturegen.utils.ClassUtils;
import com.yarm.fixturegen.valuegenerator.*;

import java.lang.reflect.*;
import java.util.*;

public class Fixture {
    private FixtureConfig config;

    public <T> T create(Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            CacheContext.cache(clazz, instance);
            for(Field f : instance.getClass().getDeclaredFields()){
                Class<?> type = f.getType();
                f.setAccessible(true);
                if(!CacheContext.exist(type)){
                    f.set(instance, generateValue(f));
                }else{
                    f.set(instance, CacheContext.get(type));
                }
            }
            return instance;
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new FixtureGenException(e);
        }
    }

    public Object generateValue(Field f){
        if(f.getType()==boolean.class||f.getType()==Boolean.class){
            return new Random().nextBoolean();
        }else if(f.getType()==long.class||f.getType()==Long.class){ //isPrimitive
            return new Random().nextLong();
        }else if(f.getType()==int.class||f.getType()==Integer.class){
            return new Random().nextInt();
        }else if(f.getType()==String.class){
            return f.getName();
        }else if(f.getType().isArray()){
            return new ArrayValueGenerator(f.getType().getComponentType()).config(config).create();
        }else if(f.getType() == List.class){
            return new CollectionValueGenerator(((ParameterizedType)f.getGenericType()).getActualTypeArguments()).config(config).create();
        }else if(f.getType().isEnum()){
            return new EnumValueGenerator(f.getType()).create();
        }else if(f.getType()==Map.class){
            return new MapValueGenerator(((ParameterizedType)f.getGenericType()).getActualTypeArguments()).config(config).create();
        }

        return new ObjectValueGenerator(f.getType()).config(config).create();
    }

    public Object generateValue(Type type){
        if(type==boolean.class||type==Boolean.class){
            return new Random().nextBoolean();
        }else if(type==long.class||type==Long.class){ //isPrimitive
            return new Random().nextLong();
        }else if(type==int.class||type==Integer.class){
            return new Random().nextInt();
        }else if(type==String.class){
            return UUID.randomUUID().toString();
        }else if(ClassUtils.castToClass(type).isArray()){
            return new ArrayValueGenerator(ClassUtils.castToClass(type).getComponentType()).config(config).create();
        }else if(type == List.class){
            return new CollectionValueGenerator(((ParameterizedType)type).getActualTypeArguments()).config(config).create();
        }else if(ClassUtils.castToClass(type).isEnum()){
            return new EnumValueGenerator(type).create();
        }else if(type==Map.class){
            return new MapValueGenerator(((ParameterizedType)type).getActualTypeArguments()).config(config).create();
        }

        return new ObjectValueGenerator(type).config(config).create();
    }


    public Fixture config(FixtureConfig config) {
        this.config = config;
        return this;
    }
}
