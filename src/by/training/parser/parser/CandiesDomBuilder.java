package by.training.parser.parser;

import by.training.parser.entity.*;
import by.training.parser.exception.ParserException;
import by.training.parser.handler.CandiesXmlTag;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;

import static by.training.parser.validation.FileValidator.checkFileIsValid;

public class CandiesDomBuilder extends AbstractCandiesBuilder {

    private static final Logger logger = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public CandiesDomBuilder() throws ParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "Parser configuration exception");
            throw new ParserException(e);
        }
    }

    @Override
    public void buildSetCandies(String filePath) throws ParserException {
        Document doc;
        try {
            if (checkFileIsValid(filePath)) {
                doc = docBuilder.parse(filePath);
                Element root = doc.getDocumentElement();
                NodeList lollipopCandiesList = root.getElementsByTagName(CandiesXmlTag.LOLLIPOP_CANDY.getTagName());
                NodeList chocolateCandiesList = root.getElementsByTagName(CandiesXmlTag.CHOCOLATE_CANDY.getTagName());
                for (int i = 0; i < lollipopCandiesList.getLength(); i++) {
                    Element candyElement = (Element) lollipopCandiesList.item(i);
                    Candy candy = buildCandy(candyElement, CandiesXmlTag.LOLLIPOP_CANDY.getTagName());
                    candies.add(candy);
                }
                for (int i = 0; i < chocolateCandiesList.getLength(); i++) {
                    Element candyElement = (Element) chocolateCandiesList.item(i);
                    Candy candy = buildCandy(candyElement, CandiesXmlTag.CHOCOLATE_CANDY.getTagName());
                    candies.add(candy);
                }
            }
            else {
                logger.log(Level.ERROR, "File path is not correct or = null");
                throw new ParserException(filePath + "is not found");
            }
        } catch (SAXException | IOException e) {
            logger.log(Level.ERROR, "SAX or IO exception");
            throw new ParserException(e);
        }
    }

    private Candy buildCandy(Element candyElement, String tagName) throws ParserException {
        Candy candy = null;
        if (tagName.equals("lollipop-candy")) {
            candy = new LollipopCandy();
        } else if (tagName.equals("chocolate-candy")) {
            candy = new ChocolateCandy();
        }
        NodeList valueList = candyElement.getElementsByTagName(CandiesXmlTag.VALUE.getTagName());
        for (int i = 0; i < valueList.getLength(); i++) {
            Element valueElement = (Element) valueList.item(i);
            Value value = buildValue(valueElement);
            candy.setValue(value);
        }

        String name = getElementTextContent(candyElement, CandiesXmlTag.NAME.getTagName());
        candy.setName(name);
        NodeList ingredientsList = candyElement.getElementsByTagName(CandiesXmlTag.INGREDIENTS.getTagName());
        for (int i = 0; i < ingredientsList.getLength(); i++) {
            Element ingredientsElement = (Element) ingredientsList.item(i);
            Ingredients ingredients = buildIngredients(ingredientsElement);
            candy.setIngredients(ingredients);
        }
        String id = candyElement.getAttribute("id");
        candy.setId(id);
        LocalDateTime dateOfManufacture = LocalDateTime
                .parse(getElementTextContent(candyElement, CandiesXmlTag.DATE_OF_MANUFACTURE.getTagName()));
        candy.setDateOfManufacture(dateOfManufacture);
        candy.setProduction(getElementTextContent(candyElement, CandiesXmlTag.PRODUCTION.getTagName()));
        Integer energy = Integer.parseInt(getElementTextContent(candyElement, CandiesXmlTag.ENERGY.getTagName()));
        candy.setEnergy(energy);
        if (candy instanceof ChocolateCandy) {
            Boolean filling = Boolean.parseBoolean(getElementTextContent(candyElement, CandiesXmlTag.FILLING.getTagName()));
            ((ChocolateCandy) candy).setFilling(filling);
            String chocolateType = getElementTextContent(candyElement, CandiesXmlTag.CHOCOLATE_TYPE.getTagName());
            for (TypeChocolate typeChocolates : TypeChocolate.values()) {
                if (typeChocolates.getTypeChocolate().equals(chocolateType)) {
                     ((ChocolateCandy) candy).setTypeChocolate(typeChocolates);
                }
            }

        }
        return candy;
    }

    private Value buildValue(Element valueElement) throws ParserException {
        Value value = new Value();
        Double proteins = Double.parseDouble(getElementTextContent(valueElement, CandiesXmlTag.PROTEINS.getTagName()));
        value.setProteins(proteins);
        Double fats = Double.parseDouble(getElementTextContent(valueElement, CandiesXmlTag.FATS.getTagName()));
        value.setFats(fats);
        Double carbohydrates = Double
                .parseDouble(getElementTextContent(valueElement, CandiesXmlTag.CARBOHYDRATES.getTagName()));
        value.setCarbohydrates(carbohydrates);
        return value;
    }

    private Ingredients buildIngredients(Element ingredientsElement) throws ParserException {
        Ingredients ingredients = new Ingredients();
        Double sugar = Double.parseDouble(getElementTextContent(ingredientsElement, CandiesXmlTag.SUGAR.getTagName()));
        ingredients.setSugar(sugar);
        if (ingredientsElement.getElementsByTagName(CandiesXmlTag.WATER.getTagName()).getLength() != 0) {
            Double water = Double.parseDouble(getElementTextContent(ingredientsElement, CandiesXmlTag.WATER.getTagName()));
            ingredients.setWater(water);
        }
        if (ingredientsElement.getElementsByTagName(CandiesXmlTag.COCOA.getTagName()).getLength() != 0) {
            Double cocoa = Double.parseDouble(getElementTextContent(ingredientsElement, CandiesXmlTag.COCOA.getTagName()));
            ingredients.setCocoa(cocoa);
        }
        if (ingredientsElement.getElementsByTagName(CandiesXmlTag.VANILLIN.getTagName()).getLength() != 0) {
            Double vanilin = Double.parseDouble(getElementTextContent(ingredientsElement, CandiesXmlTag.VANILLIN.getTagName()));
            ingredients.setVanillin(vanilin);
        }
        return ingredients;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
