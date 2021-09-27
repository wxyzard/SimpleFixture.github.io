package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.CacheContext;
import io.github.simplefixture.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Random;

public final class ByteValueGenerator  implements ValueGenerator<Byte>{
    private FixtureConfig config;
    private Field field;

    @Override
    public ByteValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public ByteValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Byte create() {
        byte[] randomBytes = new byte[1];
        new Random().nextBytes(randomBytes);
        MetaCache metaCache = CacheContext.get(field);

        String fieldName  = field.getName();
        Map<String, Object> values = config.getValues();

        try{
            if(values.containsKey(fieldName)){
                return (Byte) values.get(fieldName);
            }else {
                return config.getTheme().getValue(metaCache.getAssignCount(), field, randomBytes[0]);
            }
        }catch (ClassCastException e) {
            throw new ClassCastException("'" + field.getName() + "' Property's type is not match. check your property value.");
        }
    }
}
