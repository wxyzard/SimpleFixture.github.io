package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.StringValueType;
import io.github.simplefixture.cache.CacheContext;
import io.github.simplefixture.cache.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

public final class StringValueGenerator extends abstractValueGenerator implements ValueGenerator<String>{

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
        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();
        try{
            if(values.containsKey(fieldName)){
                return (String)values.get(fieldName) + (getAssignCount()==0||getAssignCount()==1?"":getAssignCount());
            }else{
                return config.getTheme().getValue(getAssignCount(), field, fieldName.toLowerCase() +  getAssignCount());
            }
        }catch (ClassCastException e){
            throw new ClassCastException("'"+field.getName()+"' Property's type is not match. check your property value.");
        }
    }

    private int getAssignCount(){
        MetaCache metaCache = CacheContext.get(field);

        if(metaCache!=null){
            return metaCache.getAssignCount();
        }
        return 0;
    }
}
