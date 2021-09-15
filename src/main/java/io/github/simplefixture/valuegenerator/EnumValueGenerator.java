package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.utils.ClassUtils;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Random;

public final class EnumValueGenerator implements ValueGenerator<Enum>{

    private Type type;
    private FixtureConfig config;
    private Field field;

    public EnumValueGenerator(Type type){
        this.type = type;
    }

    @Override
    public EnumValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public EnumValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Enum create() {
        try {
            Class<?> aClass = ClassUtils.castToClass(type);

            String fieldName = field.getName();
            Map<String, Object> values = config.getValues();

            if (values.containsKey(fieldName)) {
                return (Enum) values.get(fieldName);
            } else {
                return (Enum) aClass.getEnumConstants()[new Random().nextInt(aClass.getEnumConstants().length)];
            }
        }catch (ClassCastException e) {
            throw new ClassCastException("'" + field.getName() + "' Property's type is not match. check your property value.");
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

}
