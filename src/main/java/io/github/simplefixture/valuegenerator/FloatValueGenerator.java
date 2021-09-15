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
        float leftLimit =  pow(10, config.getFloatDigitSize()-1);
        float rightLimit = leftLimit * 10;

        float generatedFloat = 0f;
        MetaCache metaCache = CacheContext.get(field);

        if(config.isSequenceNumberType()){
            generatedFloat = leftLimit;
            if(metaCache!=null){
                generatedFloat = leftLimit + metaCache.getAssignCount();
            }
        } else {
            generatedFloat = leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
        }

        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();

        if(values.containsKey(fieldName)){
            return (Float) values.get(fieldName);
        }else {
            return config.getTheme().getValue(metaCache.getAssignCount(), field, generatedFloat);
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
