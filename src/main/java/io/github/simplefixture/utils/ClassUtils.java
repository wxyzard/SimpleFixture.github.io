package io.github.simplefixture.utils;

import io.github.simplefixture.Fixture;
import io.github.simplefixture.FixtureGenException;

import java.lang.reflect.*;
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

    public static Object newInstance(Class<?> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return  clazz.getDeclaredConstructor().newInstance();

    }

    public static Field[] getDeclaredAllFields(Object clazz){
        return Stream.of(getDeclaredFieldsInSuper(clazz, new Field[0]), getDeclaredFields(clazz)).flatMap(Stream::of).toArray(Field[]::new);
    }

    public static Field[] getDeclaredFieldsInSuper(Object clazz, Field[] fields){
        Class<?> superclass = clazz.getClass().getSuperclass();

        Field[] f = Stream.of(fields, superclass.getDeclaredFields()).flatMap(Stream::of).toArray(Field[]::new);

        if(superclass.equals(Object.class)){
            return f;
        }

        return getDeclaredFieldsInSuper(superclass, f);
    }

    private static Field[] getDeclaredFields(Object clazz){
        Field[] fields = clazz.getClass().getDeclaredFields();
        return fields;
    }

}
