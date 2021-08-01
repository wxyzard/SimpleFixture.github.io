package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.utils.ClassUtils;
import com.yarm.fixturegen.Fixture;
import com.yarm.fixturegen.FixtureConfig;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.*;

@NoArgsConstructor
public class MapValueGenerator implements ValueGenerator<Map>{

    private Type[] types;
    private FixtureConfig config;

    public MapValueGenerator(Type[] types){
        this.types = types;
    }

    @Override
    public MapValueGenerator config(FixtureConfig config) {
        this.config = config;
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
                m.put(new Fixture().config(config).generateValue(ClassUtils.castToClass(types[0])),
                        new Fixture().generateValue(ClassUtils.castToClass(types[1])));
            }
            return m;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
