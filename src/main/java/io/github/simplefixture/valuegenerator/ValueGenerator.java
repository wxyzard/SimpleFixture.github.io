package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;

public interface ValueGenerator<T> {

    ValueGenerator config(FixtureConfig config);

    ValueGenerator field(Field config);

    T create();
}
