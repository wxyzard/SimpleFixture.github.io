package com.yarm.fixturegen;

public class FixtureConfig {

    public FixtureConfig(){}

    private int maxCollectionSize = 1;

    public FixtureConfig(int maxCollectionSize){
        if(maxCollectionSize<0){
            throw new IllegalArgumentException();
        }
        this.maxCollectionSize = maxCollectionSize;
    }

    public int getMaxCollectionSize() {
        return maxCollectionSize;
    }
}
