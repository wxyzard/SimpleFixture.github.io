package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.utils.ClassUtils;
import com.yarm.fixturegen.Fixture;
import com.yarm.fixturegen.config.FixtureConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public final class ObjectValueGenerator implements ValueGenerator{

    private Type type;
    private FixtureConfig config;
    private Field field;

    public ObjectValueGenerator(Type type){
        this.type = type;
    }

    @Override
    public ObjectValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public ObjectValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Object create() {
        try{
            return new Fixture().field(field).config(config).create(ClassUtils.castToClass(type));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
