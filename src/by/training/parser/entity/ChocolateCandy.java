package by.training.parser.entity;

import java.time.LocalDateTime;

public class ChocolateCandy extends Candy {

    private TypeChocolate typeChocolate;
    private boolean filling;

    public ChocolateCandy() {
    }

    public ChocolateCandy(String id, String name, int energy, Ingredients ingredients, Value value, String production, LocalDateTime dateOfManufacture, TypeChocolate typeChocolate, boolean filling) {
        super(id, name, energy, ingredients, value, production, dateOfManufacture);
        this.typeChocolate = typeChocolate;
        this.filling = filling;
    }

    public TypeChocolate getTypeChocolate() {
        return typeChocolate;
    }

    public void setTypeChocolate(TypeChocolate typeChocolate) {
        this.typeChocolate = typeChocolate;
    }

    public boolean isFilling() {
        return filling;
    }

    public void setFilling(boolean filling) {
        this.filling = filling;
    }

    @Override
    public int hashCode() {
        int PRIME = 31;
        int result = super.hashCode();
        result = PRIME * result + (typeChocolate == null ? 0 : typeChocolate.hashCode());
        result = PRIME * result + (filling ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!super.equals(object)) {
            return false;
        }
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        ChocolateCandy chocolateCandy = (ChocolateCandy) object;
        if (typeChocolate != chocolateCandy.typeChocolate) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", type chocolate = ");
        builder.append(typeChocolate);
        builder.append(", filling = ");
        builder.append(filling);
        return builder.toString();
    }
}
