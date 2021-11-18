package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.utils.ClassUtils;
import io.github.simplefixture.Fixture;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.*;
import java.util.Map;

public final class ArrayValueGenerator extends AbstractValueGenerator implements ValueGenerator{

    private Type type;
    private FixtureConfig config;
    private Field field;

    public ArrayValueGenerator(Type type){
        this.type = type;
    }

    @Override
    public ArrayValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public ArrayValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Object create() {
        try{
            return config.getTheme().getValue(0, field, getValue());
        }catch (ClassCastException e) {
            throw new ClassCastException("'" + field.getName() + "' Property's type is not match. check your property value.");
        }
    }

    private Object getValue(){
        int loopCount = config.getMaxCollectionSize();
        Class<?> aClass = ClassUtils.castToClass(type);

        if(loopCount==0){
            return Array.newInstance(aClass, 0);
        }
        Object objs = Array.newInstance(aClass, loopCount);
        for(int i=0;i<loopCount;i++){
            Array.set(objs, i, new Fixture(config, field).create(aClass));
        }

        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();

        if(values==null){
            return null;
        }else if(values.containsKey(fieldName)){
            return values.get(fieldName);
        }else{
            return objs;
        }
    }

}
