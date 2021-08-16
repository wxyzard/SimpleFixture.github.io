package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.utils.ClassUtils;
import io.github.simplefixture.Fixture;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;


public final class MapValueGenerator implements ValueGenerator<Map>{

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
            int loopCount = config.getMaxCollectionSize();
            Map m = new HashMap();
            if(loopCount==0){
                return m;
            }
            for(int i=0;i<loopCount;i++){
                m.put(new Fixture().config(config).field(field).create(ClassUtils.castToClass(types[0])),
                        new Fixture().config(config).field(field).create(ClassUtils.castToClass(types[1])));
            }
            return m;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
