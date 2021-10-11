package io.github.simplefixture.utils;

import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldUtils {

    public static <T> void setField(Field f, T instance, Object value) throws IllegalAccessException, NoSuchFieldException {
        f.setAccessible(true);

        if(Modifier.isStatic(f.getModifiers())&&Modifier.isFinal(f.getModifiers())){
            if(f.get(instance)==null){
                Field mf = Field.class.getDeclaredField("modifiers");
                mf.setAccessible(true);
                mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);
                f.set(instance, value);
            }
        }else{
            f.set(instance, value);
        }
    }

}
