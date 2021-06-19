package by.training.parser.entity;

public enum TypeChocolate {
    DARK,
    MILK;

    public String getTypeChocolate() {
        return name().toLowerCase();
    }
}
