package training.candy.parser;

import by.training.parser.exception.ParserException;
import by.training.parser.parser.CandiesSaxBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class CandySaxParserTest {

    private static final String CORRECT_XML_PATH = "test\\resources\\candies.xml";
    private static final String NOT_CORRECT_XML_PATH = "I believe I can fly";
    private static final int ACTUAL_CANDIES_IN_FILE = 16;
    private CandiesSaxBuilder saxBuilder;

    @BeforeMethod
    public void initialize() throws ParserException {
        saxBuilder = new CandiesSaxBuilder();
    }

    @Test(description = "Positive test SAX parser")
    public void positiveSaxParsersTest() throws ParserException {
        saxBuilder.buildSetCandies(CORRECT_XML_PATH);
        int expected = saxBuilder.getCandies().size();
        assertEquals(ACTUAL_CANDIES_IN_FILE, expected);
    }

    @Test(description = "Negative test SAX parser with not correct path")
    public void negativeSaxParsersTest() {
        assertThrows(ParserException.class, () -> saxBuilder.buildSetCandies(NOT_CORRECT_XML_PATH));
    }

    @Test(description = "Null test SAX parser")
    public void nullSaxParsersTest() {
        assertThrows(ParserException.class, () -> saxBuilder.buildSetCandies(null));
    }

    @AfterMethod
    public void tierDown() {
        saxBuilder = null;
    }
}
