package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.cache.CacheContext;
import io.github.simplefixture.cache.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;

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
        MetaCache metaCache = CacheContext.get(field);

        if(config.isSequenceNumberType()){
            generatedLong = leftLimit;
            if(metaCache!=null){
                generatedLong = leftLimit + metaCache.getAssignCount();
            }

        } else {
            generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        }

        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();

        try{
            if(values.containsKey(fieldName)){
                return (Long)values.get(fieldName);
            }else{
                return config.getTheme().getValue(metaCache.getAssignCount(), field, generatedLong);
            }
        }catch (ClassCastException e) {
            throw new ClassCastException("'" + field.getName() + "' Property's type is not match. check your property value.");
        }
    }

    private Long pow(long a, long b){
        Long result = 1L;
        for(int i=0;i<b;i++){
            result *= a;
        }
        return result;
    }
}
