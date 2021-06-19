package by.training.parser.parser;

import by.training.parser.entity.*;
import by.training.parser.exception.ParserException;
import by.training.parser.handler.CandiesXmlTag;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static by.training.parser.validation.FileValidator.checkFileIsValid;

public class CandiesStaxBuilder extends AbstractCandiesBuilder {

    private static final Logger logger = LogManager.getLogger();
    private Set<Candy> candies;
    private XMLInputFactory inputFactory;
    private final static String HYPHEN = "-";
    private final static String UNDERSCORE = "_";

    public CandiesStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        candies = new HashSet<>();
    }

    @Override
    public Set<Candy> getCandies() {
        return candies;
    }

    @Override
    public void buildSetCandies(String filePath) throws ParserException {
        if (checkFileIsValid(filePath)) {
            XMLStreamReader reader;
            String name;
            try (FileInputStream inputStream = new FileInputStream(new File(filePath))) {
                reader = inputFactory.createXMLStreamReader(inputStream);
                while (reader.hasNext()) {
                    int type = reader.next();
                    if (type == XMLStreamConstants.START_ELEMENT) {
                        name = reader.getLocalName();
                        if (name.equals(CandiesXmlTag.LOLLIPOP_CANDY.getTagName())
                                || name.equals(CandiesXmlTag.CHOCOLATE_CANDY.getTagName())) {
                            Candy candy = buildCandy(reader, name);
                            candies.add(candy);
                        }
                    }
                }
            } catch (XMLStreamException e) {
                logger.log(Level.ERROR, "XML stream exception");
                throw new ParserException(e);
            } catch (IOException e) {
                logger.log(Level.ERROR, "IO exception");
                throw new ParserException(e);
            }
        }
        else {
            logger.log(Level.ERROR, "File path is not correct or = null");
            throw new ParserException(filePath + "is not found");
        }
    }

    private Candy buildCandy(XMLStreamReader reader, String tagName) throws ParserException {
        Candy candy = null;
        int type;
        String name;
        switch (tagName) {
            case "lollipop-candy":
                candy = new LollipopCandy();
                candy.setId(reader.getAttributeValue(null, "id"));
                break;
            case "chocolate-candy":
                candy = new ChocolateCandy();
                candy.setId(reader.getAttributeValue(null, "id"));
                break;
        }
        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (CandiesXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE))) {
                            case NAME:
                                candy.setName(getXmlText(reader));
                                break;
                            case ENERGY:
                                candy.setEnergy(Integer.parseInt(getXmlText(reader)));
                                break;
                            case PRODUCTION:
                                candy.setProduction(getXmlText(reader));
                                break;
                            case VALUE:
                                candy.setValue(buildValue(reader));
                                break;
                            case INGREDIENTS:
                                candy.setIngredients(buildIngredients(reader));
                                break;
                            case DATE_OF_MANUFACTURE:
                                candy.setDateOfManufacture(LocalDateTime.parse(getXmlText(reader)));
                                break;
                            case FILLING:
                                ((ChocolateCandy) candy).setFilling(Boolean.parseBoolean(getXmlText(reader)));
                                break;
                            case CHOCOLATE_TYPE:
                                String chocoType = reader.getAttributeValue(null, "name");
                                if (chocoType.equals(TypeChocolate.MILK.name())) {
                                    ((ChocolateCandy) candy).setTypeChocolate(TypeChocolate.MILK);
                                } else if (chocoType.equals(TypeChocolate.DARK.name())) {
                                    ((ChocolateCandy) candy).setTypeChocolate(TypeChocolate.DARK);
                                }

                                break;

                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (name.equals(CandiesXmlTag.CHOCOLATE_CANDY.getTagName())
                                || name.equals(CandiesXmlTag.LOLLIPOP_CANDY.getTagName())) {
                            return candy;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            logger.log(Level.ERROR, "XMLS exception");
            throw new ParserException("Unknown element in tag <ingredients>");
        }
        throw new ParserException("Unknown element in tag <ingredients>");
    }

    private Ingredients buildIngredients(XMLStreamReader reader) throws ParserException {
        Ingredients ingredients = new Ingredients();
        int type;
        String name;
        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (CandiesXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE))) {
                            case SUGAR:
                                ingredients.setSugar(Double.parseDouble(getXmlText(reader)));
                                break;
                            case WATER:
                                ingredients.setWater(Double.parseDouble(getXmlText(reader)));
                                break;
                            case COCOA:
                                ingredients.setCocoa(Double.parseDouble(getXmlText(reader)));
                                break;
                            case VANILLIN:
                                ingredients.setVanillin(Double.parseDouble(getXmlText(reader)));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (name.equals(CandiesXmlTag.INGREDIENTS.getTagName())) {
                            return ingredients;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            logger.log(Level.ERROR, "XMLS exception");
            throw new ParserException("Unknown element in tag <ingredients>");
        }
        throw new ParserException("Unknown element in tag <ingredients>");
    }

    private Value buildValue(XMLStreamReader reader) throws ParserException {
        Value value = new Value();
        int type;
        String name;
        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (CandiesXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE))) {
                            case PROTEINS:
                                value.setProteins(Double.parseDouble(getXmlText(reader)));
                                break;
                            case FATS:
                                value.setFats(Double.parseDouble(getXmlText(reader)));
                                break;
                            case CARBOHYDRATES:
                                value.setCarbohydrates(Double.parseDouble(getXmlText(reader)));
                                break;

                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (name.equals(CandiesXmlTag.VALUE.getTagName())) {
                            return value;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            logger.log(Level.ERROR, "XMLS exception");
            throw new ParserException("Unknown element in tag <value>");
        }
        throw new ParserException("Unknown element in tag <value>");
    }

    private String getXmlText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
