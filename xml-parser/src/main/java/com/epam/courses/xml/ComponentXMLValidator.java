package com.epam.courses.xml;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ComponentXMLValidator extends DefaultHandler {

    public static final String SCHEMA_FILE_NAME = "src/main/java/com/epam/courses/resourses/components.xsd";

    private StringBuilder errorMessage = new StringBuilder();

    private String fileName;

    public ComponentXMLValidator(String fileName) {
        this.fileName = fileName;
    }

    private StringBuilder getErrorInfo(SAXParseException e) {
        StringBuilder builder = new StringBuilder();
        builder.append('[').append(e.getLineNumber()).append(':').append(e.getColumnNumber()).append(']');
        builder.append('\n').append(e.getMessage()).append('\n');
        return builder;
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        errorMessage.append("WARNING: ");
        errorMessage.append(getErrorInfo(e));
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        errorMessage.append("ERROR: ");
        errorMessage.append(getErrorInfo(e));
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        errorMessage.append("FATAL ERROR: ");
        errorMessage.append(getErrorInfo(e));
    }

    public String getErrorMessage() {
        if (errorMessage.length() > 0) {
            return errorMessage.toString();
        } else {
            return null;
        }
    }

    public boolean validate() throws IOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = factory.newSchema(new File(SCHEMA_FILE_NAME));
            Validator validator = schema.newValidator();
            validator.setErrorHandler(this);
            validator.validate(new StreamSource(fileName));
            return getErrorMessage() == null;
        } catch (SAXException e) {
            return false;
        }
    }
}
