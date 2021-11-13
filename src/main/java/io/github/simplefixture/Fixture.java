package io.github.simplefixture;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import io.github.simplefixture.config.FixtureConfig;
import io.github.simplefixture.utils.ClassUtils;
import io.github.simplefixture.utils.FieldUtils;
import io.github.simplefixture.utils.JsonUtils;
import io.github.simplefixture.valuegenerator.*;

import java.lang.reflect.*;
import java.time.ZonedDateTime;
import java.util.*;

public class Fixture {
    private Field field;
    private Mode mode = Mode.NORMAL;
    private FixtureConfig config = new FixtureConfig();

    public Fixture(){
        CacheContext.clear();
    }

    public Fixture(FixtureConfig config, Field field){
        if(this.config.getValues().size()>0){
            config.getValues().putAll(this.config.getValues());
        }
        this.config = config;
        this.field = field;
    }

    public <T> T create(Class<T> c) {
        try {
            if(!c.isInterface()){
                if(ClassUtils.isNoneObjectType(c)){
                    return (T) generateValue(c);
                }else{
                    T instance = (T) ClassUtils.newInstance(c);
                    CacheContext.cache(c, instance);

                    Field[] fields = ClassUtils.getDeclaredAllFields(instance);

                    for(Field f : fields){
                        this.field = f;
                        Class<?> type = f.getType();
                        if(!CacheContext.exist(type)){
                            FieldUtils.setField(f, instance, generateValue(type));
                        }else{
                            FieldUtils.setField(f, instance, CacheContext.get(type));
                        }
                    }
                    return instance;
                }
            }
        } catch (Throwable e) {
            //ignore exception
        }
        return null;
    }

    public <T> T  create(String json, Class<T> clazz){
        return (T) JsonUtils.create(json, clazz);
    }

    public Fixture config(FixtureConfig config) {
        if(this.config.getValues().size()>0){
            config.getValues().putAll(this.config.getValues());
        }
        this.config = config;
        return this;
    }

    public Fixture setProperty(String fieldKey, Object value){
        this.config.setProperty(fieldKey, value);
        return this;
    }

    private Object randomNullValue(Object value){
        if(Mode.CHAOS.equals(mode)){
            if(!this.config.getValues().containsKey(field.getName())){
                if(!ClassUtils.isNoneObjectType(field.getType())){
                    Random rn = new Random();
                    int i = rn.nextInt(10) + 1;
                    if(i==1){
                        System.out.println(field.getName());
                        return null;
                    }
                }
            }
        }
        return value;
    }

    public Fixture mode(Mode mode) {
        this.mode = mode;
        return this;
    }

    private Object generateValue(Class<?> c){
        Object value;
        CacheContext.cache(field);
        
        if(c==byte.class) {
            value = new ByteValueGenerator().field(field).config(config).create();
        }else if(c==byte[].class){
            value= new ByteArrayValueGenerator().field(field).config(config).create();
        }else if(c==boolean.class||c==Boolean.class){
            value= new BooleanValueGenerator().field(field).config(config).create();
        }else if(c==long.class||c==Long.class){
            value= new LongValueGenerator().field(field).config(config).create();
        }else if(c==int.class||c==Integer.class){
            value= new IntegerValueGenerator().field(field).config(config).create();
        }else if(c==float.class||c==Float.class){
            value= new FloatValueGenerator().field(field).config(config).create();
        }else if(c==double.class||c==Double.class){
            value= new DoubleValueGenerator().field(field).config(config).create();
        }else if(c==String.class){
            value= new StringValueGenerator().field(field).config(config).create();
        }else if(c.isArray()){
            value= new ArrayValueGenerator(c.getComponentType()).field(field).config(config).create();
        }else if(c == List.class){
            value= new CollectionValueGenerator(((ParameterizedType)field.getGenericType()).getActualTypeArguments()).field(field).config(config).create();
        }else if(c.isEnum()){
            value= new EnumValueGenerator(c).field(field).config(config).create();
        }else if(c==Map.class){
            value= new MapValueGenerator(((ParameterizedType)field.getGenericType()).getActualTypeArguments()).field(field).config(config).create();
        }else if(c==Date.class){
            value= new Date();
        }else if(c== ZonedDateTime.class){
            value= ZonedDateTime.now();
        }else{
            value= new ObjectValueGenerator(c).field(field).config(config).create();
        }

        return randomNullValue(value);
    }




}
