package com.github.jep42.formatcompare.valueasserter.impl;

import org.junit.Test;

import com.github.jep42.formatcompare.util.FormatComparatorException;
import com.github.jep42.formatcompare.valueasserter.ValueAsserterFactory;
import com.github.jep42.formatcompare.valueasserter.api.ValueAsserter;

public class BooleanAsserterImplTest {

    @Test(expected = FormatComparatorException.class)
    public void assertCondition_conditionNotSupported() throws Exception {
        ValueAsserter<Boolean> comparator = ValueAsserterFactory.getValueComparatorForBoolean();
        comparator.assertCondition(true, false, "<");
    }

    @Test
    public void assertCondition_equal() throws Exception {
        ValueAsserter<Boolean> comparator = ValueAsserterFactory.getValueComparatorForBoolean();
        comparator.assertCondition(true, true, StringAsserterImpl.CONDITION_EQUAL);
    }

    @Test
    public void assertCondition_equalWithNullMaster() throws Exception {
        ValueAsserter<Boolean> comparator = ValueAsserterFactory.getValueComparatorForBoolean();
        comparator.assertCondition(null, true, StringAsserterImpl.CONDITION_EQUAL);
    }

    @Test
    public void assertCondition_equalWithNullSlave() throws Exception {
        ValueAsserter<Boolean> comparator = ValueAsserterFactory.getValueComparatorForBoolean();
        comparator.assertCondition(false, null, StringAsserterImpl.CONDITION_EQUAL);
    }



//    @Test(expected = AssertionException.class)
//    public void assertCondition_equalFail() throws Exception {
//        ValueAsserter<String> comparator = ValueAsserterFactory.getValueComparatorForString();
//        comparator.assertCondition("peterpan", "tinkerbell", StringAsserterImpl.CONDITION_EQUAL);
//    }
//
//    @Test
//    public void assertCondition_unequal() throws Exception {
//        ValueAsserter<String> comparator = ValueAsserterFactory.getValueComparatorForString();
//        comparator.assertCondition("peterpan", "tinkerbell", StringAsserterImpl.CONDITION_UNEQUAL);
//    }
//
//    @Test(expected = AssertionException.class)
//    public void assertCondition_unequalFail() throws Exception {
//        ValueAsserter<String> comparator = ValueAsserterFactory.getValueComparatorForString();
//        comparator.assertCondition("peterpan", "peterpan", StringAsserterImpl.CONDITION_UNEQUAL);
//    }



}
