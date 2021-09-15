package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.cache.CacheContext;
import io.github.simplefixture.cache.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;

public final class DoubleValueGenerator implements ValueGenerator<Double> {

    private FixtureConfig config;
    private Field field;

    @Override
    public DoubleValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public DoubleValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Double create() {
        double leftLimit = Math.pow(10L, config.getDoubleDigitSize());
        double rightLimit = leftLimit * 10L;
        double generatedDouble = 0;
        MetaCache metaCache = CacheContext.get(field);

        if(config.isSequenceNumberType()){
            generatedDouble = leftLimit;
            if(metaCache!=null){
                generatedDouble = leftLimit + metaCache.getAssignCount();
            }
        } else {
            generatedDouble = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        }

        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();

        try{
            if(values.containsKey(fieldName)){
                return (Double) values.get(fieldName);
            }else {
                return config.getTheme().getValue(metaCache.getAssignCount(), field, generatedDouble);
            }
        }catch (ClassCastException e) {
            throw new ClassCastException("'" + field.getName() + "' Property's type is not match. check your property value.");
        }

    }
}
