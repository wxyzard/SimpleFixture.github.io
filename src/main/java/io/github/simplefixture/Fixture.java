package io.github.simplefixture;

import io.github.simplefixture.cache.CacheContext;
import io.github.simplefixture.config.FixtureConfig;
import io.github.simplefixture.utils.ClassUtils;
import io.github.simplefixture.utils.JsonUtils;
import io.github.simplefixture.valuegenerator.*;

import java.lang.reflect.*;
import java.util.*;

public class Fixture {
    private FixtureConfig config = new FixtureConfig();
    private Field field;
    private Mode mode = Mode.NOMAL;

    public <T> T  create(String json, Class<T> clazz){
        return (T) JsonUtils.create(json, clazz);
    }

    public <T> T create(Class<T> clazz) {
        try {
            if(!clazz.isInterface()){
                if(ClassUtils.isNoneObjectType(clazz)){
                    return (T) generateValue(clazz);
                }else{
                    T instance = (T) ClassUtils.newInstance(clazz);
                    CacheContext.cache(clazz, instance);

                    Field[] fields = ClassUtils.getDeclaredAllFields(instance);

                    for(Field f : fields){
                        Class<?> type = f.getType();
                        if(!CacheContext.exist(type)){
                            setField(f, instance, generateValue(f));
                        }else{
                            setField(f, instance, CacheContext.get(type));
                        }
                    }
                    return instance;
                }
            }
        } catch (Throwable e) {
            //error ignore
        }
        return null;
    }



    private <T> void setField(Field f, T instance, Object value) throws IllegalAccessException, NoSuchFieldException {
        f.setAccessible(true);

        if(Modifier.isFinal(f.getModifiers())){
            if(f.get(f.getDeclaringClass())==null){
                Field mf = Field.class.getDeclaredField("modifiers");
                mf.setAccessible(true);
                mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);
                f.set(instance, value);
            }
        }else{
            f.set(instance, value);
        }
    }

    private Object generateValue(Field _field){
        Object value;
        CacheContext.cache(_field);

        if(_field.getType()==byte.class||_field.getType()==Byte.class){
            value= new ByteValueGenerator().field(_field).config(config).create();
        }else if(_field.getType()==boolean.class||_field.getType()==Boolean.class){
            value= new BooleanValueGenerator().field(_field).config(config).create();
        }else if(_field.getType()==long.class||_field.getType()==Long.class){ //isPrimitive
            value= new LongValueGenerator().field(_field).config(config).create();
        }else if(_field.getType()==int.class||_field.getType()==Integer.class){
            value= new IntegerValueGenerator().field(_field).config(config).create();
        }else if(_field.getType()==float.class||_field.getType()==Float.class){
            value= new FloatValueGenerator().field(_field).config(config).create();
        }else if(_field.getType()==double.class||_field.getType()==Double.class){
            value= new DoubleValueGenerator().field(_field).config(config).create();
        }else if(_field.getType()==String.class){
            value= new StringValueGenerator().field(_field).config(config).create();
        }else if(_field.getType().isArray()){
            value= new ArrayValueGenerator(_field.getType().getComponentType()).field(_field).config(config).create();
        }else if(_field.getType() == List.class){
            value= new CollectionValueGenerator(((ParameterizedType)_field.getGenericType()).getActualTypeArguments()).field(_field).config(config).create();
        }else if(_field.getType().isEnum()){
            value= new EnumValueGenerator(_field.getType()).field(_field).config(config).create();
        }else if(_field.getType()==Map.class){
            value= new MapValueGenerator(((ParameterizedType)_field.getGenericType()).getActualTypeArguments()).field(_field).config(config).create();
        }else if(_field.getType()==Date.class){
            value= new Date();
        }else{
            value= new ObjectValueGenerator(_field.getType()).field(_field).config(config).create();
        }

        return randomNullValue(value);
    }

    private Object generateValue(Type type){
        Object value;
        CacheContext.cache(field);

        if(type==byte.class||type==Byte.class){
            value= new ByteValueGenerator().field(field).config(config).create();
        }else if(type==boolean.class||type==Boolean.class){
            value= new BooleanValueGenerator().field(field).config(config).create();
        }else if(type==long.class||type==Long.class){
            value= new LongValueGenerator().field(field).config(config).create();
        }else if(type==int.class||type==Integer.class){
            value= new IntegerValueGenerator().field(field).config(config).create();
        }else if(field.getType()==float.class||field.getType()==Float.class){
            value= new FloatValueGenerator().field(field).config(config).create();
        }else if(field.getType()==double.class||field.getType()==Double.class){
            value= new DoubleValueGenerator().field(field).config(config).create();
        }else if(type==String.class){
            value= new StringValueGenerator().field(field).config(config).create();
        }else if(ClassUtils.castToClass(type).isArray()){
            value= new ArrayValueGenerator(ClassUtils.castToClass(type).getComponentType()).field(field).config(config).create();
        }else if(type == List.class){
            value= new CollectionValueGenerator(((ParameterizedType)type).getActualTypeArguments()).field(field).config(config).create();
        }else if(ClassUtils.castToClass(type).isEnum()){
            value= new EnumValueGenerator(type).field(field).config(config).create();
        }else if(type==Map.class){
            value= new MapValueGenerator(((ParameterizedType)type).getActualTypeArguments()).field(field).config(config).create();
        }else if(type==Date.class){
            value= new Date();
        }else{
            value= new ObjectValueGenerator(type).field(field).config(config).create();
        }

        return randomNullValue(value);
    }

    public Fixture mode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public Fixture config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    public Fixture field(Field field){
        this.field = field;
        return this;
    }

    public Fixture setProperty(String fieldKey, Object value){
        this.config.setProperty(fieldKey, value);
        return this;
    }

    private Object randomNullValue(Object value){
        if(Mode.CHAOS.equals(mode)){
            // 10%확율로 null 반환
        }
        return value;
    }

}
