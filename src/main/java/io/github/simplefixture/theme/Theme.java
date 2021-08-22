package io.github.simplefixture.theme;

import java.lang.reflect.Field;

public interface Theme {

    <T> T getValue(int index, Field field, T value);
}
