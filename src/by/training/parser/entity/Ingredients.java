package by.training.parser.entity;

import by.training.parser.exception.ParserException;

public class Ingredients {

    private double sugar;
    private double cocoa;
    private double vanillin;
    private double water;

    public Ingredients() {
    }

    public Ingredients(double sugar, double cocoa, double vanillin, double water) {
        this.sugar = sugar;
        this.cocoa = cocoa;
        this.vanillin = vanillin;
        this.water = water;
    }

    public Ingredients(double sugar, double cocoa, double vanillin) {
        this.sugar = sugar;
        this.cocoa = cocoa;
        this.vanillin = vanillin;
    }

    public Ingredients(double sugar, double water) {
        this.sugar = sugar;
        this.water = water;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getCocoa() {
        return cocoa;
    }

    public void setCocoa(double cocoa) {
        this.cocoa = cocoa;
    }

    public double getVanillin() {
        return vanillin;
    }

    public void setVanillin(double vanillin) {
        this.vanillin = vanillin;

    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = result * PRIME + (int) sugar;
        result = result * PRIME + (int) cocoa;
        result = result * PRIME + (int) vanillin;
        result = result * PRIME + (int) water;
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        Ingredients ingredients = (Ingredients) object;
        if (ingredients.sugar != sugar) {
            return false;
        }
        if (ingredients.cocoa != cocoa) {
            return false;
        }
        if (ingredients.vanillin != vanillin) {
            return false;
        }
        if (ingredients.water != water) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ingredient: sugar = ");
        builder.append(sugar);
        builder.append(", cocoa = ");
        builder.append(cocoa);
        builder.append(", vanillin = ");
        builder.append(vanillin);
        builder.append(", water = ");
        builder.append(water);
        return builder.toString();
    }
}
