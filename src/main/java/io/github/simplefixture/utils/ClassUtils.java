package io.github.simplefixture.utils;

import io.github.simplefixture.CacheContext;
import io.github.simplefixture.Fixture;
import io.github.simplefixture.FixtureGenException;
import io.github.simplefixture.MetaCache;
import io.github.simplefixture.valuegenerator.*;

import java.io.ByteArrayInputStream;
import java.lang.reflect.*;
import java.time.ZonedDateTime;
import java.util.*;
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

    public static Object newInstance(Class<?> clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Object instance = null;
        try{
            instance = clazz.getDeclaredConstructor().newInstance();
        }catch (NoSuchMethodException | IllegalAccessException e){
            Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
            for(Constructor c : declaredConstructors){
                if (Modifier.isPrivate(c.getModifiers())) {
                    c.setAccessible(true);
                }
                instance = c.newInstance(getConstructArgs(c.getParameters()));
                break;
            }
        }
        return instance;
    }

    private static Object[] getConstructArgs(Parameter[] parameters) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object[] args = new Object[parameters.length];

        for(int i=0;i<parameters.length;i++){
            Class<?> t = parameters[i].getType();
            Class<?> c = ClassUtils.castToClass(t);

            if (t == long.class || t == int.class || t == float.class || t == double.class) {
                args[i] = 0;
            } else if (t == boolean.class) {
                args[i] = true;
            } else if (t == byte.class) {
                byte[] randomBytes = new byte[1];
                args[i] = randomBytes[0];
            } else if (t == byte[].class) {
                byte[] randomBytes = new byte[1];
                args[i] = randomBytes;
            } else if (t == String.class) {
                args[i] = "agr0";
            } else if (c.isArray()) {
                args[i] = Array.newInstance(t, 0);;
            } else if (t == List.class) {
                args[i] = new ArrayList<>();
            } else if (c.isEnum()) {
                args[i] = c.getEnumConstants()[new Random().nextInt(c.getEnumConstants().length)];
            } else if (t == Map.class) {
                args[i] = new HashMap<>();
            } else if (t == Date.class) {
                args[i] = new Date();
            }else if(t == ZonedDateTime.class){
                args[i] = ZonedDateTime.now();
            } else {
                args[i] = newInstance(c);
            }
        }

        return args;
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
