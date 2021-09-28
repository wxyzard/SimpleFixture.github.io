package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.CacheContext;
import io.github.simplefixture.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Random;

public final class FloatValueGenerator extends AbstractValueGenerator implements ValueGenerator<Float> {

    private FixtureConfig config;
    private Field field;

    @Override
    public FloatValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public FloatValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Float create() {
        try{
            return config.getTheme().getValue(getAssignCount(field), field, getValue());
        }catch (ClassCastException e) {
            throw new ClassCastException("'" + field.getName() + "' Property's type is not match. check your property value.");
        }
    }

    private Float getValue(){
        float startLimit =  pow(10, config.getFloatDigitSize()-2);
        float endLimit = pow(10, config.getFloatDigitSize()-1);
        float generatedFloat;

        if(config.isSequenceNumberType()){
            generatedFloat = startLimit + getAssignCount(field);
        } else {
            generatedFloat = startLimit + (int) (new Random().nextFloat() * (endLimit - startLimit));
        }

        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();

        if(values.containsKey(fieldName)){
            if(values==null){
                return null;
            }
            return (Float) values.get(fieldName);
        }else {
            return generatedFloat;
        }
    }

    private Integer pow(int a, int b){
        Integer result = 1;
        for(int i=0;i<b;i++){
            result *= a;
        }
        return result;
    }
}
