package io.github.simplefixture.exception;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.text.ParseException;

public class DateParseException extends RuntimeException{

    Logger logger = LoggerFactory.getLogger(DateParseException.class);

    public DateParseException(String value, String format, ParseException e) {
        super(e);
        logger.error("This format is not supported.(support format : "+format);
    }
}
