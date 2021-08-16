package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Random;

public final class ByteValueGenerator  implements ValueGenerator<Byte>{
    private FixtureConfig config;
    private Field field;

    @Override
    public ByteValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public ByteValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Byte create() {
        byte[] randomBytes = new byte[1];
        new Random().nextBytes(randomBytes);
        return config.getTheme().getRedefinedValue(field, randomBytes[0]);
    }
}
