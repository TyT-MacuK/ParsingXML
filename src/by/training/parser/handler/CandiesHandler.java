package by.training.parser.handler;

import by.training.parser.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class CandiesHandler extends DefaultHandler {

    private static final Logger logger = LogManager.getLogger();
    private Set<Candy> candies;
    private EnumSet<CandiesXmlTag> tagsWithText;
    private Candy currentCandy;
    private CandiesXmlTag currentXmlTag;
    public static final String HYPHEN = "-";
    public static final String UNDERSCORE = "_";

    public CandiesHandler() {
        candies = new HashSet<>();
        tagsWithText = EnumSet.range(CandiesXmlTag.NAME, CandiesXmlTag.VANILLIN);
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    @Override
    public void startDocument() {
        logger.log(Level.INFO, "SAX parsing started");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "chocolate-candy":
                currentCandy = new ChocolateCandy();
                currentCandy.setId(attributes.getValue("id"));
                break;
            case "lollipop-candy":
                currentCandy = new LollipopCandy();
                currentCandy.setId(attributes.getValue("id"));
                break;
            case "ingredients":
                currentCandy.setIngredients(new Ingredients());
                break;
            case "value":
                currentCandy.setValue(new Value());
                break;
            case "chocolate-type":
                String chocoType = attributes.getValue("name");
                if (chocoType.equals(TypeChocolate.MILK.name())) {
                    ((ChocolateCandy) currentCandy).setTypeChocolate(TypeChocolate.MILK);
                } else if (chocoType.equals(TypeChocolate.DARK.name())) {
                    ((ChocolateCandy) currentCandy).setTypeChocolate(TypeChocolate.DARK);
                }
                break;
            default:
                CandiesXmlTag temp = CandiesXmlTag.valueOf(qName.replace(HYPHEN, UNDERSCORE).toUpperCase());
                if (tagsWithText.contains(temp)) {
                    currentXmlTag = temp;
                }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME:
                    currentCandy.setName(data);
                    break;
                case ENERGY:
                    currentCandy.setEnergy(Integer.parseInt(data));
                    break;
                case PRODUCTION:
                    currentCandy.setProduction(data);
                    break;
                case DATE_OF_MANUFACTURE:
                    LocalDateTime dateTime = LocalDateTime.parse(data);
                    currentCandy.setDateOfManufacture(dateTime);
                    break;
                case FILLING:
                    ((ChocolateCandy) currentCandy).setFilling(Boolean.parseBoolean(data));
                    break;
                case PROTEINS:
                    currentCandy.getValue().setProteins(Double.parseDouble(data));
                    break;
                case FATS:
                    currentCandy.getValue().setFats(Double.parseDouble(data));
                    break;
                case CARBOHYDRATES:
                    currentCandy.getValue().setCarbohydrates(Double.parseDouble(data));
                    break;
                case SUGAR:
                    currentCandy.getIngredients().setSugar(Double.parseDouble(data));
                    break;
                case WATER:
                    currentCandy.getIngredients().setWater(Double.parseDouble(data));
                    break;
                case COCOA:
                    currentCandy.getIngredients().setCocoa(Double.parseDouble(data));
                    break;
                case VANILLIN:
                    currentCandy.getIngredients().setVanillin(Double.parseDouble(data));
                    break;
                default:
                    logger.log(Level.ERROR, "Tag {} in not valid", data);
                    throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.getTagName());
            }
        }
        currentXmlTag = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("lollipop-candy".equals(qName) || "chocolate-candy".equals(qName)) {
            candies.add(currentCandy);
        }
    }

    @Override
    public void endDocument() {
        logger.log(Level.INFO, "Parsing ended");
    }
}
