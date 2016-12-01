package com.github.jep42.formatcompare.formathandler.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.github.jep42.formatcompare.formathandler.api.FormatHandlerException;

public final class XMLAccessor {

	private static final String UNEXPECTED_EXCEPTION_MESSAGE = "An unexpected exception occured while processing XML. message: %s";

	private static final String EXCEPTION_ELEMENT_NOT_FOUND = "XML element not found: %s";


	private XMLAccessor() {
		super();
	}


	public static String getXMLValue(Document xmlDocument, String xmlElementXPath) {
		try {
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList)xPath.evaluate(xmlElementXPath, xmlDocument.getDocumentElement(), XPathConstants.NODESET);
			if (nodes == null || nodes.getLength() == 0) {
				throw new FormatHandlerException(String.format(EXCEPTION_ELEMENT_NOT_FOUND, xmlElementXPath));
			}

			return nodes.item(0).getFirstChild().getNodeValue();
		} catch (XPathExpressionException e) {
			throw new FormatHandlerException(String.format(UNEXPECTED_EXCEPTION_MESSAGE, e.getMessage()), e);
		}
	}


	public static Document getDOMFromXML(String xmlString) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
		    return builder.parse(stream);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new FormatHandlerException(String.format(UNEXPECTED_EXCEPTION_MESSAGE, e.getMessage()), e);
		}
	}

}
