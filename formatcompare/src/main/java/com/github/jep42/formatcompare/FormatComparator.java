package com.github.jep42.formatcompare;

import java.util.Iterator;

import com.github.jep42.formatcompare.elementcomparator.ElementComparatorFactory;
import com.github.jep42.formatcompare.elementcomparator.api.ElementComparator;
import com.github.jep42.formatcompare.fieldmapper.FieldMapperFactory;
import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.util.FormatComparatorException;

public final class FormatComparator {

    private FormatComparator() {
        super();
    }

    public static FormatComparator createComparator() {
        return new FormatComparator();
    }

    /**
     * Compares the formats represented by the given FormatHandlers via the given map file.
     *
     * @param mapFilePath
     * @param masterFormatHandler
     * @param slaveFormatHandler
     * @throws FormatComparatorException
     */
    public void compare(String mapFilePath, FormatHandler masterFormatHandler, FormatHandler slaveFormatHandler) throws FormatComparatorException {
        Iterator<FieldMapping> mappingIterator = FieldMapperFactory.getFieldMapper(mapFilePath).getFieldMappings().iterator();
        while (mappingIterator.hasNext()) {
            FieldMapping mapping = mappingIterator.next();
            ElementComparator dataElementVerifier = ElementComparatorFactory.getElementComparatorFor(mapping, masterFormatHandler, slaveFormatHandler);
            dataElementVerifier.compare();
        }
    }
}
