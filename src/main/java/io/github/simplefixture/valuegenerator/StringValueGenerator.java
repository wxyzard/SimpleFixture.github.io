package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.StringValueType;
import io.github.simplefixture.cache.CacheContext;
import io.github.simplefixture.cache.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.UUID;

public final class StringValueGenerator implements ValueGenerator<String>{

    private FixtureConfig config;
    private Field field;

    @Override
    public StringValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public StringValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public String create(){
        if(config.getValueType().equals(StringValueType.RANDOM)){
            return UUID.randomUUID().toString();
        }
        MetaCache metaCache = CacheContext.get(field);
        if(metaCache==null){
            return field.getName().toLowerCase();
        }
        return config.getTheme().getValue(metaCache.getAssignCount(), field, field.getName().toLowerCase() + (metaCache.getAssignCount()==0?"":metaCache.getAssignCount()));
    }
}
