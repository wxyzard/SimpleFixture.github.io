package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.NumberValueType;
import io.github.simplefixture.cache.CacheContext;
import io.github.simplefixture.cache.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Random;

public final class IntegerValueGenerator implements ValueGenerator<Integer>{
    private FixtureConfig config;
    private Field field;

    @Override
    public IntegerValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public IntegerValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Integer create() {
        int leftLimit =  pow(10, config.getLongDigitSize());
        int rightLimit = leftLimit * 10;

        int generatedInteger = 0;

        if(config.isSequenceNumberType()){
            MetaCache metaCache = CacheContext.get(field);
            generatedInteger = leftLimit;
            if(metaCache!=null){
                generatedInteger = leftLimit + metaCache.getAssignCount();
            }
        } else {
            generatedInteger = leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
        }

        return config.getTheme().getRedefinedValue(field, generatedInteger);
    }

    private Integer pow(int a, int b){
        Integer result = 1;
        for(int i=0;i<b;i++){
            result *= a;
        }
        return result;
    }
}
