package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.CacheContext;
import io.github.simplefixture.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;

import static java.lang.StrictMath.pow;

public final class DoubleValueGenerator extends AbstractValueGenerator implements ValueGenerator<Double> {

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
        try{
            return config.getTheme().getValue(getAssignCount(field), field, getValue());
        }catch (ClassCastException e) {
            throw new ClassCastException("'" + field.getName() + "' Property's type is not match. check your property value.");
        }
    }

    private Double getValue(){
        double startLimit =  pow(10, config.getDoubleDigitSize()-2);
        double endLimit = pow(10, config.getDoubleDigitSize()-1);
        double generatedDouble;

        if(config.isSequenceNumberType()){
            generatedDouble = startLimit + getAssignCount(field);
        } else {
            generatedDouble = startLimit + (long) (Math.random() * (endLimit - startLimit));
        }

        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();

        if(values.containsKey(fieldName)){
            if(values==null){
                return null;
            }
            return (Double) values.get(fieldName);
        }else {
            return generatedDouble;
        }
    }
}
