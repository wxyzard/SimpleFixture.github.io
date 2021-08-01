package com.yarm.fixturegen;

public class FixtureGenException extends RuntimeException {
    public FixtureGenException(ReflectiveOperationException e) {
        super(e);
    }
}
