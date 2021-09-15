package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.cache.CacheContext;
import io.github.simplefixture.cache.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;
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
        int startLimit =  pow(10, config.getIntegerDigitSize()-2);
        int endLimit = pow(10, config.getIntegerDigitSize()-1);

        int generatedInteger;
        MetaCache metaCache = CacheContext.get(field);

        if(config.isSequenceNumberType()){
            generatedInteger = startLimit;
            if(metaCache!=null){
                generatedInteger = startLimit + metaCache.getAssignCount();
            }
        } else {
            generatedInteger = endLimit + (int) (new Random().nextFloat() * (endLimit - startLimit));
        }

        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();

        try{
            if(values.containsKey(fieldName)){
                return (Integer) values.get(fieldName);
            }else{
                return config.getTheme().getValue(metaCache.getAssignCount(), field, generatedInteger);
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
