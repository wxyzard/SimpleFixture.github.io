package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.CacheContext;
import io.github.simplefixture.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Random;

public class ByteArrayValueGenerator extends AbstractValueGenerator  implements ValueGenerator<byte[]>{
    private FixtureConfig config;
    private Field field;

    @Override
    public ByteArrayValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public ByteArrayValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public byte[] create() {
        try{
            byte[] randomBytes = new byte[1];
            new Random().nextBytes(randomBytes);
            MetaCache metaCache = CacheContext.get(field);

            String fieldName  = field.getName();
            Map<String, Object> values = config.getValues();

            if(values.containsKey(fieldName)){
                if(values==null){
                    return null;
                }
                return (byte[]) values.get(fieldName);
            }else {
                return config.getTheme().getValue(metaCache.getAssignCount(), field, randomBytes);
            }
        }catch (ClassCastException e) {
            throw new ClassCastException("'" + field.getName() + "' Property's type is not match. check your property value.");
        }
    }
}