package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.utils.ClassUtils;
import com.yarm.fixturegen.FixtureConfig;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.Random;

@NoArgsConstructor
public class EnumValueGenerator implements ValueGenerator<Enum>{

    private Type type;
    private FixtureConfig config;

    public EnumValueGenerator(Type type){
        this.type = type;
    }

    @Override
    public ValueGenerator config(FixtureConfig config) {
        this.config = config;
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
