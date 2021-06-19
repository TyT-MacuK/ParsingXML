package by.training.parser.handler;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;

public class CandyErrorHandler implements ErrorHandler {

    private static final Logger logger = LogManager.getLogger();
    private static final String MESSAGE = "{} : {} - {} ";

    @Override
    public void warning(SAXParseException e) throws SAXException {
        logger.log(Level.WARN, MESSAGE, e.getLineNumber(), e.getColumnNumber(), e.getMessage());

        throw new SAXException();
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        logger.log(Level.ERROR, MESSAGE, e.getLineNumber(), e.getColumnNumber(), e.getMessage());
        throw new SAXException();
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        logger.log(Level.FATAL, MESSAGE, e.getLineNumber(), e.getColumnNumber(), e.getMessage());
        throw new SAXException();
    }
}
