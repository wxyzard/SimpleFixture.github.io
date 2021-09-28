package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.CacheContext;
import io.github.simplefixture.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;

public final class LongValueGenerator extends AbstractValueGenerator implements ValueGenerator<Long> {

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
        try{
            return config.getTheme().getValue(getAssignCount(field), field, getValue());
        }catch (ClassCastException e) {
            throw new ClassCastException("'" + field.getName() + "' Property's type is not match. check your property value.");
        }
    }

    public Long getValue(){
        long startLimit =  pow(10, config.getLongDigitSize()-2);
        long endLimit = pow(10, config.getLongDigitSize()-1);

        long generatedLong;

        if(config.isSequenceNumberType()){
            generatedLong = startLimit + getAssignCount(field);
        } else {
            generatedLong = startLimit + (long) (Math.random() * (endLimit - startLimit));
        }

        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();


        if(values.containsKey(fieldName)){
            if(values==null){
                return null;
            }
            return (Long)values.get(fieldName);
        }else{
            return generatedLong;
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
