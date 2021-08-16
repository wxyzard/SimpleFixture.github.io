package com.yarm.fixturegen.theme;

import java.lang.reflect.Field;

public interface Theme {

    <T> T getRedefinedValue(Field field, T value);
}
