package io.github.simplefixture;

public class FixtureGenException extends RuntimeException {
    public FixtureGenException(ReflectiveOperationException e) {
        super(e);
    }
}
