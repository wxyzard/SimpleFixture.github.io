package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.config.FixtureConfig;
import java.lang.reflect.Field;

public final class LongValueGenerator implements ValueGenerator<Long> {

    private FixtureConfig config;
    private Field field;

    @Override
    public LongValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public LongValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Long create() {
        long leftLimit = 10000000L;
        long rightLimit = 100000000L;
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

        return config.getTheme().getRedefinedValue(field, generatedLong);
    }
}
