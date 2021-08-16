package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.NumberValueType;
import com.yarm.fixturegen.cache.CacheContext;
import com.yarm.fixturegen.cache.MetaCache;
import com.yarm.fixturegen.config.FixtureConfig;

import java.lang.reflect.Field;

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

        if(config.getNumberValueType().equals(NumberValueType.SEQUENCE)){
            MetaCache metaCache = CacheContext.get(field);
            if(metaCache==null){
                generatedDouble = leftLimit;
            }else{
                generatedDouble = leftLimit + metaCache.getAssignCount();
            }
        } else {
            generatedDouble = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        }

        return config.getTheme().getRedefinedValue(field, generatedDouble);
    }
}
