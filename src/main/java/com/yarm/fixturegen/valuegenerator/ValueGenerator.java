package com.yarm.fixturegen.valuegenerator;

import com.yarm.fixturegen.FixtureConfig;

import java.lang.reflect.InvocationTargetException;

public interface ValueGenerator<T> {

    ValueGenerator config(FixtureConfig config);

    T create() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
