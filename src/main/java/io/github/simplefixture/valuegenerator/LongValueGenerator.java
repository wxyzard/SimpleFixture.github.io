package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.NumberValueType;
import io.github.simplefixture.cache.CacheContext;
import io.github.simplefixture.cache.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;

public final class LongValueGenerator implements ValueGenerator<Long> {

    private FixtureConfig config;
    private Field field;

    @Override
    public LongValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public LongValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Long create() {
        long leftLimit =  pow(10L, config.getLongDigitSize());
        long rightLimit = leftLimit * 10L;

        long generatedLong;

        if(config.getNumberValueType().equals(NumberValueType.SEQUENCE)){
            MetaCache metaCache = CacheContext.get(field);
            if(metaCache==null){
                generatedLong = leftLimit;
            }else{
                generatedLong = leftLimit + metaCache.getAssignCount();
            }
        } else {
            generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        }

        return config.getTheme().getRedefinedValue(field, generatedLong);
    }

    private Long pow(long a, long b){
        Long result = 1L;
        for(int i=0;i<b;i++){
            result *= a;
        }
        return result;
    }
}
