package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.CacheContext;
import io.github.simplefixture.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Random;

public final class IntegerValueGenerator extends AbstractValueGenerator implements ValueGenerator<Integer>{
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
        try{
            return config.getTheme().getValue(getAssignCount(field), field, getValue());
        }catch (ClassCastException e) {
            throw new ClassCastException("'" + field.getName() + "' Property's type is not match. check your property value.");
        }
    }

    private Integer getValue(){
        int startLimit =  pow(10, config.getIntegerDigitSize()-2);
        int endLimit = pow(10, config.getIntegerDigitSize()-1);
        int generatedInteger;

        if(config.isSequenceNumberType()){
            generatedInteger = startLimit + getAssignCount(field);;
        } else {
            generatedInteger = endLimit + (int) (new Random().nextFloat() * (endLimit - startLimit));
        }

        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();

        if(values==null){
            return null;
        }else if(values.containsKey(fieldName)){
            return (Integer) values.get(fieldName);
        }else{
            return generatedInteger;
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
