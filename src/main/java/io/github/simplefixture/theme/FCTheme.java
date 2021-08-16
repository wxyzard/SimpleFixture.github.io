package io.github.simplefixture.theme;

import java.lang.reflect.Field;

public class FCTheme implements Theme{
    @Override
    public <T> T getRedefinedValue(Field field, T value) {
        return value;
    }
}
