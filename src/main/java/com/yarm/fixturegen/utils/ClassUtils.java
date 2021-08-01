package com.yarm.fixturegen.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.stream.Stream;

public class ClassUtils {

    public static Class<?> castToClass(Type type) {
        if (type instanceof WildcardType) {
            return (Class<?>) Stream.of(((WildcardType) type).getUpperBounds())
                    .findFirst()
                    .orElse(Stream.of(((WildcardType) type).getLowerBounds())
                            .findFirst()
                            .orElse(Object.class));
        }

        if (isParameterized(type)) {
            return (Class<?>) ((ParameterizedType) type).getRawType();
        }

        return (Class<?>) type;
    }

    public static boolean isParameterized(final Type type) {
        return type instanceof ParameterizedType && ((ParameterizedType) type).getActualTypeArguments().length > 0;
    }
}
