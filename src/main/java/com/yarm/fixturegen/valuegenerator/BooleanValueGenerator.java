package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Random;

public final class BooleanValueGenerator implements ValueGenerator{
    private FixtureConfig config;
    private Field field;

    @Override
    public BooleanValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public BooleanValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Object create() {
        return config.getTheme().getRedefinedValue(field, new Random().nextBoolean());
    }
}
