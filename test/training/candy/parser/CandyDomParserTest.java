package training.candy.parser;

import by.training.parser.exception.ParserException;
import by.training.parser.parser.CandiesDomBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class CandyDomParserTest {

    private static final String CORRECT_XML_PATH = "test\\resources\\candies.xml";
    private static final String NOT_CORRECT_XML_PATH = "I believe I can fly";
    private static final int ACTUAL_CANDIES_IN_FILE = 16;
    private CandiesDomBuilder domBuilder;

    @BeforeMethod
    public void initialize() throws ParserException {
        domBuilder = new CandiesDomBuilder();
    }

    @Test(description = "Positive test DOM parser")
    public void positiveDomParsersTest() throws ParserException {
        domBuilder.buildSetCandies(CORRECT_XML_PATH);
        int expected = domBuilder.getCandies().size();
        assertEquals(ACTUAL_CANDIES_IN_FILE, expected);
    }

    @Test(description = "Negative test DOM parser with not correct path")
    public void negativeDomParsersTest() {
        assertThrows(ParserException.class, () -> domBuilder.buildSetCandies(NOT_CORRECT_XML_PATH));
    }

    @Test(description = "Null test DOM parser")
    public void nullDomParsersTest() {
        assertThrows(ParserException.class, () -> domBuilder.buildSetCandies(null));
    }

    @AfterMethod
    public void tierDown() {
        domBuilder = null;
    }
}
