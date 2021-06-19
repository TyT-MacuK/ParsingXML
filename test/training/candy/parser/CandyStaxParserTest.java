package training.candy.parser;

import by.training.parser.exception.ParserException;
import by.training.parser.parser.CandiesStaxBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class CandyStaxParserTest {

    private static final String CORRECT_XML_PATH = "test\\resources\\candies.xml";
    private static final String NOT_CORRECT_XML_PATH = "I believe I can fly";
    private static final int ACTUAL_CANDIES_IN_FILE = 16;
    private CandiesStaxBuilder staxBuilder;

    @BeforeMethod
    public void initialize() {
        staxBuilder = new CandiesStaxBuilder();
    }

    @Test(description = "Positive test SAX parser")
    public void positiveStaxParsersTest() throws ParserException {
        staxBuilder.buildSetCandies(CORRECT_XML_PATH);
        int expected = staxBuilder.getCandies().size();
        assertEquals(ACTUAL_CANDIES_IN_FILE, expected);
    }

    @Test(description = "Negative test SAX parser with not correct path")
    public void negativeStaxParsersTest() {
        assertThrows(ParserException.class, () -> staxBuilder.buildSetCandies(NOT_CORRECT_XML_PATH));
    }

    @Test(description = "Null test SAX parser")
    public void nullStaxParsersTest() {
        assertThrows(ParserException.class, () -> staxBuilder.buildSetCandies(null));
    }

    @AfterMethod
    public void tierDown() {
        staxBuilder = null;
    }
}
