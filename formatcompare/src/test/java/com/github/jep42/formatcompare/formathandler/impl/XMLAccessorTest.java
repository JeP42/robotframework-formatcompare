package com.github.jep42.formatcompare.formathandler.impl;

import static org.junit.Assert.assertTrue;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

import com.github.jep42.formatcompare.UnitTestUtil;
import com.github.jep42.formatcompare.formathandler.api.FormatHandlerException;

public class XMLAccessorTest {

	@Test
	public void getXMLValue_nodesNotFound() {

		String xPathExpression = "Order/Positions";
		String xmlAsString = UnitTestUtil.getFile("com/github/jep42/formathandler/formathandler_test.xml");
		Document xmlDocument = XMLAccessor.getDOMFromXML(xmlAsString);

		try {
			XMLAccessor.getXMLValue(xmlDocument, xPathExpression);
		} catch (FormatHandlerException e) {
			assertTrue(e.getMessage().startsWith("XML element not found"));
		}
	}

	@Test
	public void getXMLValue_invalidExpression() {

		String xPathExpression = "";
		String xmlAsString = UnitTestUtil.getFile("com/github/jep42/formathandler/formathandler_test.xml");
		Document xmlDocument = XMLAccessor.getDOMFromXML(xmlAsString);

		try {
			XMLAccessor.getXMLValue(xmlDocument, xPathExpression);
		} catch (FormatHandlerException e) {
			assertTrue(e.getCause() instanceof XPathExpressionException);
			assertTrue(e.getMessage().startsWith("An unexpected exception occured"));
		}
	}

	@Test
	public void getDOMFromXML_nodesNotFound() {
		try {
			XMLAccessor.getDOMFromXML("");
		} catch (FormatHandlerException e) {
			assertTrue(e.getCause() instanceof SAXParseException);
			assertTrue(e.getMessage().startsWith("An unexpected exception occured"));
		}
	}


}
