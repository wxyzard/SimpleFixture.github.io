package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.utils.ClassUtils;
import io.github.simplefixture.Fixture;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;


public final class MapValueGenerator extends AbstractValueGenerator implements ValueGenerator<Map>{

    private Type[] types;
    private FixtureConfig config;
    private Field field;

    public MapValueGenerator(Type[] types){
        this.types = types;
    }

    @Override
    public MapValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public MapValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Map create() {
        try{
            return config.getTheme().getValue(getAssignCount(field), field, getValue());
        }catch (ClassCastException e){
            throw new ClassCastException("'"+field.getName()+"' Property's type is not match. check your property value.");
        }
    }

    private Map getValue(){
        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();

        if(values==null){
            return null;
        }else if(values.containsKey(fieldName)){
            return (Map)values.get(fieldName);
        }else{
            int loopCount = config.getMaxCollectionSize();
            Map m = new HashMap();
            if(loopCount==0){
                return m;
            }
            for(int i=0;i<loopCount;i++){
                m.put(new Fixture(config, field).create(ClassUtils.castToClass(types[0])),
                        new Fixture(config, field).create(ClassUtils.castToClass(types[1])));
            }
            return m;
        }
    }

}
