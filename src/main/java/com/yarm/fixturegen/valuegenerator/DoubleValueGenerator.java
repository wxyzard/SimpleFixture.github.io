package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.config.FixtureConfig;

import java.lang.reflect.Field;

public final class DoubleValueGenerator implements ValueGenerator<Double> {

    private FixtureConfig config;
    private Field field;

    @Override
    public DoubleValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public DoubleValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Double create() {
        double leftLimit = 10000000L;
        double rightLimit = 100000000L;
        double generatedDouble = leftLimit + (Math.random() * (rightLimit - leftLimit));

        return config.getTheme().getRedefinedValue(field, generatedDouble);
    }
}
