package io.github.simplefixture.exception;

import java.text.ParseException;

public class DateParseException extends RuntimeException{

    public DateParseException(ParseException e) {
        super(e);
    }
}
