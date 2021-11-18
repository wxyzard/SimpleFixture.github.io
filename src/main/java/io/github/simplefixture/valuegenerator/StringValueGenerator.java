package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.StringValueType;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

public final class StringValueGenerator extends AbstractValueGenerator implements ValueGenerator<String>{

    private FixtureConfig config;
    private Field field;

    @Override
    public StringValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public StringValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public String create(){
        if(config.getValueType().equals(StringValueType.RANDOM)){
            return UUID.randomUUID().toString();
        }

        return generateValue();
    }

    private String generateValue() {
        try{
            return config.getTheme().getValue(getAssignCount(field), field, getValue());
        }catch (ClassCastException e){
            throw new ClassCastException("'"+field.getName()+"' Property's type is not match. check your property value.");
        }
    }

    protected String getValue(){
        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();
        Object changedValue = values.get(fieldName);

        if(values==null){
            return null;
        }else if(values.containsKey(fieldName)){
            if(changedValue==null){
                return null;
            }else if(changedValue.equals("")){
                return "";
            }
            return changedValue.toString();
        }else{
            return fieldName.toLowerCase() + getAssignCount(field);
        }
    }


}
