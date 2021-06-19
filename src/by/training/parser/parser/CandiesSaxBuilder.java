package by.training.parser.parser;

import by.training.parser.entity.Candy;
import by.training.parser.exception.ParserException;
import by.training.parser.handler.CandiesHandler;
import by.training.parser.handler.CandyErrorHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

import static by.training.parser.validation.FileValidator.checkFileIsValid;

public class CandiesSaxBuilder extends AbstractCandiesBuilder {

    private static final Logger logger = LogManager.getLogger();
    private CandiesHandler handler = new CandiesHandler();
    private XMLReader reader;

    public CandiesSaxBuilder() throws ParserException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.log(Level.ERROR, "Parser configuration in not valid");
            throw new ParserException("Parser exception");
        }
        reader.setErrorHandler(new CandyErrorHandler());
        reader.setContentHandler(handler);
    }

    public CandiesSaxBuilder(Set<Candy> candies) {
        super(candies);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.log(Level.ERROR, "Parser configuration in not valid");
        }
        reader.setErrorHandler(new CandyErrorHandler());
        reader.setContentHandler(handler);
    }

    @Override
    public void buildSetCandies(String filePath) throws ParserException {
        if (checkFileIsValid(filePath)) {
            try {
                reader.parse(filePath);
            } catch (IOException | SAXException e) {
                logger.log(Level.ERROR, "IO or SAX exception");
                throw new ParserException("IO or SAX exception");
            }
        } else {
            logger.log(Level.ERROR, filePath + " is not found");
            throw new ParserException(filePath + " is not found");
        }
        candies = handler.getCandies();
    }
}
