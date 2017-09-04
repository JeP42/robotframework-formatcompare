package com.github.jep42.formatcompare.formathandler.api;

import java.util.TimeZone;

import com.github.jep42.formatcompare.util.UserContext;

public abstract class AbstractFormatHandler implements FormatHandler {

    protected static final String PARSING_ERROR_MESSAGE = "Error while parsing value '%s' with pattern %s in %s. Message: %s";

    private UserContext userContext;

    protected AbstractFormatHandler(TimeZone timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
        this(new UserContext(timezone, dateTimeFormat, dateFormat, numberFormat));
    }

    protected AbstractFormatHandler(UserContext userContext) {
        this.userContext = userContext;
    }

    @Override
    public UserContext getUserContext() {
        return this.userContext;
    }

}
