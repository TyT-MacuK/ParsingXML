package by.training.parser.entity;

import java.time.LocalDateTime;

public class LollipopCandy extends Candy {

    public LollipopCandy() {
    }

    public LollipopCandy(String id, String name, int energy, Ingredients ingredients, Value value, String production, LocalDateTime dateOfManufacture) {
        super(id, name, energy, ingredients, value, production, dateOfManufacture);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = PRIME * result;
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
        LollipopCandy lollipopCandy = (LollipopCandy) object;
        return lollipopCandy.equals(object);
    }
    @Override
    public String toString() {
       return super.toString();
    }
}
