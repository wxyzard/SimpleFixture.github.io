package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.cache.CacheContext;
import io.github.simplefixture.cache.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Random;

public final class BooleanValueGenerator implements ValueGenerator{
    private FixtureConfig config;
    private Field field;

    @Override
    public BooleanValueGenerator config(FixtureConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public BooleanValueGenerator field(Field field) {
        this.field = field;
        return this;
    }

    @Override
    public Object create() {
        MetaCache metaCache = CacheContext.get(field);
        return config.getTheme().getValue(metaCache.getAssignCount(), field, new Random().nextBoolean());
    }
}
