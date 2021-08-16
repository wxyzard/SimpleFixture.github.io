package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Random;

public final class FloatValueGenerator implements ValueGenerator<Float> {

    private FixtureConfig config;
    private Field field;

    @Override
    public FloatValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public FloatValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Float create() {
        float leftLimit = 0f;
        float rightLimit = 1f;
        float generatedFloat = leftLimit + (float) (Math.random() * (rightLimit - leftLimit));
        return config.getTheme().getRedefinedValue(field, generatedFloat);
    }
}
