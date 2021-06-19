package by.training.parser.entity;

public class Value {
    private double proteins;
    private double fats;
    private double carbohydrates;

    public Value() {
    }

    public Value(double proteins, double fats, double carbohydrates) {
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Override
    public int hashCode() {
        int PRIME = 31;
        int result = 1;
        result = result * PRIME + (int) proteins;
        result = result * PRIME + (int) fats;
        result = result * PRIME + (int) carbohydrates;
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
        Value value = (Value) object;
        if (value.proteins != proteins) {
            return false;
        }
        if (value.fats != fats) {
            return false;
        }
        if (value.carbohydrates != carbohydrates) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Value: proteins = ");
        builder.append(proteins);
        builder.append(", fats = ");
        builder.append(fats);
        builder.append(", carbohydrates = ");
        builder.append(carbohydrates);
        return builder.toString();
    }
}
