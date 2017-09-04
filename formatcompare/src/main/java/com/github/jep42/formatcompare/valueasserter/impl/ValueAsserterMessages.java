package com.github.jep42.formatcompare.valueasserter.impl;

public final class ValueAsserterMessages {

    public static final String ASSERTION_FAILED_MESSAGE = "Condition not true: %s %s %s";

    public static final String ASSERTION_NOT_POSSIBLE = "Unable to assert values. masterValue: %s, slaveValue: %s";

    public static final String COMPARATOR_NOT_SUPPORTED_ERROR_MESSAGE = "The value comparator for %s does not support the condition: %s";

    private ValueAsserterMessages() {
        super();
    }


}
