package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.Fixture;
import com.yarm.fixturegen.utils.ClassUtils;
import com.yarm.fixturegen.config.FixtureConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Random;

public final class EnumValueGenerator implements ValueGenerator<Enum>{

    private Type type;
    private FixtureConfig config;
    private Field field;

    public EnumValueGenerator(Type type){
        this.type = type;
    }

    @Override
    public EnumValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public EnumValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Enum create() {
        try{
            Class<?> aClass = ClassUtils.castToClass(type);
            return (Enum)aClass.getEnumConstants()[new Random().nextInt(aClass.getEnumConstants().length)];
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

}
