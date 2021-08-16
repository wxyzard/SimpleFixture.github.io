package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.NumberValueType;
import io.github.simplefixture.cache.CacheContext;
import io.github.simplefixture.cache.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

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

        if(config.isSequenceNumberType()){
            MetaCache metaCache = CacheContext.get(field);
            generatedDouble = leftLimit;
            if(metaCache!=null){
                generatedDouble = leftLimit + metaCache.getAssignCount();
            }
        } else {
            generatedDouble = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        }

        return config.getTheme().getRedefinedValue(field, generatedDouble);
    }
}
