package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.CacheContext;
import io.github.simplefixture.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Random;

public final class BooleanValueGenerator extends AbstractValueGenerator implements ValueGenerator<Boolean>{
    private FixtureConfig config;
    private Field field;

    @Override
    public BooleanValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public BooleanValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Boolean create() {
        try{
            return config.getTheme().getValue(0, field, getValue());
        }catch (ClassCastException e) {
            throw new ClassCastException("'" + field.getName() + "' Property's type is not match. check your property value.");
        }
    }

    private Boolean getValue(){
        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();

        if(values.containsKey(fieldName)){
            return (Boolean)values.get(fieldName);
        }else {
            return new Random().nextBoolean();
        }
    }
}
