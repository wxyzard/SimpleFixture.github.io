package io.github.simplefixture.valuegenerator;

import io.github.simplefixture.CacheContext;
import io.github.simplefixture.MetaCache;
import io.github.simplefixture.config.FixtureConfig;

import java.lang.reflect.Field;
import java.util.Map;

public class AbstractValueGenerator {

    protected int getAssignCount(Field f){
        MetaCache metaCache = CacheContext.get(f);

        if(metaCache!=null){
            return metaCache.getAssignCount();
        }
        return 0;
    }


}
