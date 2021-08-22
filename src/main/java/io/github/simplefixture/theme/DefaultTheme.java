package io.github.simplefixture.theme;

import java.lang.reflect.Field;

public class DefaultTheme implements Theme{
    @Override
    public <T> T getValue(int index, Field field, T value) {
        return value;
    }
}
