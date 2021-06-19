package by.training.parser.handler;

public enum CandiesXmlTag {
    CANDIES,
    CHOCOLATE_CANDY,
    LOLLIPOP_CANDY,
    NAME,
    ENERGY,
    PRODUCTION,
    VALUE,
    INGREDIENTS,
    DATE_OF_MANUFACTURE,
    FILLING,
    CHOCOLATE_TYPE,
    PROTEINS,
    FATS,
    CARBOHYDRATES,
    SUGAR,
    WATER,
    COCOA,
    VANILLIN;

    public String getTagName() {
        return name().replace("_", "-").toLowerCase();
    }
}
