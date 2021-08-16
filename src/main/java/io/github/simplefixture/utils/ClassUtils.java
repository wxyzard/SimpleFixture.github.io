package io.github.simplefixture.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ClassUtils {
    private static final List<String> WrapperTypes = Arrays.asList("java.lang.Integer", "java.lang.Long", "java.lang.Boolean", "java.lang.String", "java.lang.Double", "java.lang.Float", "java.lang.Byte", "java.lang.Character");


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

    public static boolean isNoneObjectType(Class<?> c){
        return WrapperTypes.contains(c.getTypeName())||c.isPrimitive();
    }
}
