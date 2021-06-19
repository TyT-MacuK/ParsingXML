package by.training.parser.parser;

import by.training.parser.exception.ParserException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CandiesBuilderFactory {

    private static final Logger logger = LogManager.getLogger();

    private CandiesBuilderFactory() {
    }

    public static AbstractCandiesBuilder createCandiesBuilder(String typeParser) throws ParserException {
        AbstractCandiesBuilder builder = null;
        switch (typeParser.trim().toUpperCase()) {
            case "DOM":
                builder = new CandiesDomBuilder();
                break;
            case "SAX":
                builder = new CandiesStaxBuilder();
                break;
            case "STAX":
                builder = new CandiesSaxBuilder();
                break;
            default:
                logger.log(Level.ERROR, typeParser + " in not correct");
                throw new ParserException("parser " + typeParser + "is not found");
        }
        logger.log(Level.INFO, builder.getClass().getName() + " will be initialize");
        return builder;
    }
}
