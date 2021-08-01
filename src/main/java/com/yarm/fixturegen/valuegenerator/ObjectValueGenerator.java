package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.utils.ClassUtils;
import com.yarm.fixturegen.Fixture;
import com.yarm.fixturegen.FixtureConfig;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;

@NoArgsConstructor
public class ObjectValueGenerator implements ValueGenerator{

    private Type type;
    private FixtureConfig config;

    public ObjectValueGenerator(Type type){
        this.type = type;
    }

    @Override
    public ObjectValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public Object create() {
        try{
            return new Fixture().config(config).create(ClassUtils.castToClass(type));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }


}
