package com.github.jep42.formatcompare.valueasserter.impl;

import com.github.jep42.formatcompare.util.FormatComparatorException;
import com.github.jep42.formatcompare.valueasserter.api.AssertionException;
import com.github.jep42.formatcompare.valueasserter.api.ValueAsserter;

public class BooleanAsserterImpl implements ValueAsserter<Boolean> {

    public static final String CONDITION_EQUAL = "=";

    public static final String CONDITION_UNEQUAL = "!=";

    @Override
    public void assertCondition(Boolean masterValue, Boolean slaveValue, String condition) throws AssertionException {
        if (CONDITION_EQUAL.equals(condition)) {
            checkEqual(masterValue, slaveValue, condition);
        } else if (CONDITION_UNEQUAL.equals(condition)) {
            checkUnequal(masterValue, slaveValue, condition);
        } else {
            throw new FormatComparatorException(String.format(ValueAsserterMessages.COMPARATOR_NOT_SUPPORTED_ERROR_MESSAGE, "BOOLEAN", condition));
        }
    }

    private void checkUnequal(Boolean masterValue, Boolean slaveValue, String condition) throws AssertionException {
        if (masterValue.equals(slaveValue)) {
            throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
        }
    }

    private void checkEqual(Boolean masterValue, Boolean slaveValue, String condition) throws AssertionException {
        if (!masterValue.equals(slaveValue)) {
            throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
        }
    }

}
