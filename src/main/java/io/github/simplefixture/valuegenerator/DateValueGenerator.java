package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.StringValueType;
import io.github.simplefixture.config.FixtureConfig;
import io.github.simplefixture.exception.DateParseException;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public final class DateValueGenerator extends AbstractValueGenerator implements ValueGenerator<Date>{

    private FixtureConfig config;
    private Field field;

    @Override
    public DateValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public DateValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Date create(){
        if(config.getValueType().equals(StringValueType.RANDOM)){
            return new Date();
        }

        return generateValue();
    }

    private Date generateValue() {
        try{
            return config.getTheme().getValue(getAssignCount(field), field, getValue());
        }catch (ClassCastException e){
            throw new ClassCastException("'"+field.getName()+"' Property's type is not match. check your property value.");
        }
    }

    protected Date getValue()  {
        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();
        Object changedValue = values.get(fieldName);

        if(values==null){
            return null;
        }else if(values.containsKey(fieldName)){
            if(changedValue==null){
                return null;
            }
            if(changedValue instanceof String){
                return toDate((String)changedValue);
            }
            return (Date)changedValue;
        }else{
            return new Date();
        }
    }

    private Date toDate(String d){
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return transFormat.parse(d);
        } catch (ParseException e) {
            throw new DateParseException(e);
        }

    }


}
