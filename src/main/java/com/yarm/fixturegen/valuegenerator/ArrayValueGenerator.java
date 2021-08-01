package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.utils.ClassUtils;
import com.yarm.fixturegen.Fixture;
import com.yarm.fixturegen.FixtureConfig;
import lombok.NoArgsConstructor;

import java.lang.reflect.*;

@NoArgsConstructor
public class ArrayValueGenerator implements ValueGenerator{

    private Type type;
    private FixtureConfig config;

    public ArrayValueGenerator(Type type){
        this.type = type;
    }

    @Override
    public ArrayValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public Object create() {
        try{
            int loopCount = config.getMaxCollectionSize();
            Class<?> aClass = ClassUtils.castToClass(type);

            if(loopCount==0){
                return Array.newInstance(aClass, 0);
            }
            Object objects = Array.newInstance(aClass, loopCount);
            for(int i=0;i<loopCount;i++){
                Array.set(objects, i, new Fixture().config(config).generateValue(aClass));
            }
            return objects;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
