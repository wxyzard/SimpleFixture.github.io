package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.config.FixtureConfig;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public interface ValueGenerator<T> {

    ValueGenerator config(FixtureConfig config);

    ValueGenerator field(Field config);

    T create();
}
