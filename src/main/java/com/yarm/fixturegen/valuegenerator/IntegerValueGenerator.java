package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.config.FixtureConfig;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public final class IntegerValueGenerator implements ValueGenerator<Integer>{
    private FixtureConfig config;
    private Field field;

    @Override
    public IntegerValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public IntegerValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Integer create() {
        int leftLimit = 100000;
        int rightLimit = 100000000;
        int generatedInteger = leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
        return config.getTheme().getRedefinedValue(field, generatedInteger);
    }
}
