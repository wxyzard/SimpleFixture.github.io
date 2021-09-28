package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.utils.ClassUtils;
import io.github.simplefixture.Fixture;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;

public final class ObjectValueGenerator extends AbstractValueGenerator implements ValueGenerator{

    private Type type;
    private FixtureConfig config;
    private Field field;

    public ObjectValueGenerator(Type type){
        this.type = type;
    }

    @Override
    public ObjectValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public ObjectValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Object create() {
        try{
            return config.getTheme().getValue(getAssignCount(field), field, getValue());
        }catch (ClassCastException e){
            throw new ClassCastException("'"+field.getName()+"' Property's type is not match. check your property value.");
        }
    }

    private Object getValue(){
        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();

        if(values.containsKey(fieldName)){
            if(values==null){
                return null;
            }
            return values.get(fieldName);
        }else{
            return new Fixture().field(field).config(config).create(ClassUtils.castToClass(type));
        }
    }
}
