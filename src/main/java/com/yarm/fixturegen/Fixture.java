package com.yarm.fixturegen;

import com.yarm.fixturegen.cache.CacheContext;
import com.yarm.fixturegen.config.FixtureConfig;
import com.yarm.fixturegen.utils.ClassUtils;
import com.yarm.fixturegen.valuegenerator.*;

import java.lang.reflect.*;
import java.util.*;

public class Fixture {
    private FixtureConfig config = new FixtureConfig();
    private Field field;


    public <T> T create(Class<T> clazz) {
        try {
            if(ClassUtils.isNoneObjectType(clazz)){
                return (T) generateValue(clazz);
            }else{
                T instance = clazz.getDeclaredConstructor().newInstance();
                CacheContext.cache(clazz, instance);
                for(Field f : instance.getClass().getDeclaredFields()){
                    Class<?> type = f.getType();
                    if(!CacheContext.exist(type)){
                        CacheContext.cache(f);
                        setField(f, instance, generateValue(f));
                    }else{
                        setField(f, instance, CacheContext.get(type));
                    }
                }
                return instance;
            }
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new FixtureGenException(e);
        }
    }

    private <T> void setField(Field f, T instance, Object value) throws IllegalAccessException {
        f.setAccessible(true);
        f.set(instance, value);
    }

    private Object generateValue(Field _field){
        if(_field.getType()==byte.class||_field.getType()==Byte.class){
            return new ByteValueGenerator().field(_field).config(config).create();
        }else if(_field.getType()==boolean.class||_field.getType()==Boolean.class){
            return new BooleanValueGenerator().field(_field).config(config).create();
        }else if(_field.getType()==long.class||_field.getType()==Long.class){ //isPrimitive
            return new LongValueGenerator().field(_field).config(config).create();
        }else if(_field.getType()==int.class||_field.getType()==Integer.class){
            return new IntegerValueGenerator().field(_field).config(config).create();
        }else if(_field.getType()==float.class||_field.getType()==Float.class){
            return new FloatValueGenerator().field(_field).config(config).create();
        }else if(_field.getType()==double.class||_field.getType()==Double.class){
            return new DoubleValueGenerator().field(_field).config(config).create();
        }else if(_field.getType()==String.class){
            return new StringValueGenerator().field(_field).config(config).create();
        }else if(_field.getType().isArray()){
            return new ArrayValueGenerator(_field.getType().getComponentType()).field(_field).config(config).create();
        }else if(_field.getType() == List.class){
            return new CollectionValueGenerator(((ParameterizedType)_field.getGenericType()).getActualTypeArguments()).field(_field).config(config).create();
        }else if(_field.getType().isEnum()){
            return new EnumValueGenerator(_field.getType()).create();
        }else if(_field.getType()==Map.class){
            return new MapValueGenerator(((ParameterizedType)_field.getGenericType()).getActualTypeArguments()).field(_field).config(config).create();
        }

        return new ObjectValueGenerator(_field.getType()).config(config).create();
    }

    private Object generateValue(Type type){
        if(type==byte.class||type==Byte.class){
            return new ByteValueGenerator().field(field).config(config).create();
        }else if(type==boolean.class||type==Boolean.class){
            return new BooleanValueGenerator().field(field).config(config).create();
        }else if(type==long.class||type==Long.class){
            return new LongValueGenerator().field(field).config(config).create();
        }else if(type==int.class||type==Integer.class){
            return new IntegerValueGenerator().field(field).config(config).create();
        }else if(field.getType()==float.class||field.getType()==Float.class){
            return new FloatValueGenerator().field(field).config(config).create();
        }else if(field.getType()==double.class||field.getType()==Double.class){
            return new DoubleValueGenerator().field(field).config(config).create();
        }else if(type==String.class){
            return new StringValueGenerator().field(field).config(config).create();
        }else if(ClassUtils.castToClass(type).isArray()){
            return new ArrayValueGenerator(ClassUtils.castToClass(type).getComponentType()).field(field).config(config).create();
        }else if(type == List.class){
            return new CollectionValueGenerator(((ParameterizedType)type).getActualTypeArguments()).field(field).config(config).create();
        }else if(ClassUtils.castToClass(type).isEnum()){
            return new EnumValueGenerator(type).config(config).create();
        }else if(type==Map.class){
            return new MapValueGenerator(((ParameterizedType)type).getActualTypeArguments()).field(field).config(config).create();
        }

        return new ObjectValueGenerator(type).config(config).create();
    }

    public Fixture config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    public Fixture field(Field field){
        this.field = field;
        return this;
    }



}
