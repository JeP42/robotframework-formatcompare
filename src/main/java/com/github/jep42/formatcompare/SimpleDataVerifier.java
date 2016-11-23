package com.github.jep42.formatcompare;

import java.util.Iterator;

import com.github.jep42.formatcompare.fieldmapper.FieldMapperFactory;
import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.formatcomparator.DataElementVerifierFactory;
import com.github.jep42.formatcompare.formatcomparator.api.DataElementVerifier;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.util.DataVerifierException;

public final class SimpleDataVerifier {

    private SimpleDataVerifier() {
        super();
    }

    public static SimpleDataVerifier createVerifier() {
        return new SimpleDataVerifier();
    }

    public void verify(String mapFilePath, FormatHandler masterFormatHandler, FormatHandler slaveFormatHandler) throws DataVerifierException {
        Iterator<FieldMapping> mappingIterator = FieldMapperFactory.getFieldMapper(mapFilePath).getFieldMappings().iterator();
        while (mappingIterator.hasNext()) {
            FieldMapping mapping = mappingIterator.next();
            DataElementVerifier dataElementVerifier = DataElementVerifierFactory.getDataElementVerifierFor(mapping, masterFormatHandler, slaveFormatHandler);
            dataElementVerifier.verify();
        }
    }


}
