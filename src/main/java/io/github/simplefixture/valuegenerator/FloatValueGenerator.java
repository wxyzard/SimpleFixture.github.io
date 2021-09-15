package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.cache.CacheContext;
import io.github.simplefixture.cache.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Random;

public final class FloatValueGenerator implements ValueGenerator<Float> {

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
        float startLimit =  pow(10, config.getFloatDigitSize()-2);
        float endLimit = pow(10, config.getFloatDigitSize()-1);

        float generatedFloat;
        MetaCache metaCache = CacheContext.get(field);

        if(config.isSequenceNumberType()){
            generatedFloat = startLimit;
            if(metaCache!=null){
                generatedFloat = startLimit + metaCache.getAssignCount();
            }
        } else {
            generatedFloat = startLimit + (int) (new Random().nextFloat() * (endLimit - startLimit));
        }

        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();

        try{
            if(values.containsKey(fieldName)){
                return (Float) values.get(fieldName);
            }else {
                return config.getTheme().getValue(metaCache.getAssignCount(), field, generatedFloat);
            }
        }catch (ClassCastException e) {
            throw new ClassCastException("'" + field.getName() + "' Property's type is not match. check your property value.");
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
