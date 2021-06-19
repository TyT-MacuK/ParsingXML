package by.training.parser.entity;

import java.time.LocalDateTime;

public abstract class Candy {
    private String id;
    private String name;
    private int energy;
    private Ingredients ingredients;
    private Value value;
    private String production;
    private LocalDateTime dateOfManufacture;

    public Candy() {
    }

    public Candy(String id, String name, int energy, Ingredients ingredients, Value value, String production, LocalDateTime dateOfManufacture) {
        this.id = id;
        this.name = name;
        this.energy = energy;
        this.ingredients = ingredients;
        this.value = value;
        this.production = production;
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public LocalDateTime getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(LocalDateTime dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (name == null ? 0 : name.hashCode());
        result = PRIME * result + energy;
        result = PRIME * result + (ingredients == null ? 0 : ingredients.hashCode());
        result = PRIME * result + (value == null ? 0 : value.hashCode());
        result = PRIME * result + (production == null ? 0 : production.hashCode());
        result = PRIME * result + (dateOfManufacture == null ? 0 : dateOfManufacture.hashCode());
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
        Candy candy = (Candy) object;
        if (id == null && candy.id != null) {
            return false;
        }
        if (name == null && candy.name != null) {
            return false;
        } else if (!name.equals(candy.name)) {
            return false;
        }
        if (energy != candy.energy) {
            return false;
        }
        if (ingredients == null && candy.ingredients != null) {
            return false;
        } else if (!ingredients.equals(candy.ingredients)) {
            return false;
        }
        if (value == null && candy.value != null) {
            return false;
        } else if (!value.equals(candy.value)) {
            return false;
        }
        if (production == null && candy.production != null) {
            return false;
        } else if (!production.equals(candy.production)) {
            return false;
        }
        if (dateOfManufacture == null && candy.dateOfManufacture != null) {
            return false;
        } else if (!dateOfManufacture.equals(candy.dateOfManufacture)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Candy: id = ");
        builder.append(id);
        builder.append(", name = ");
        builder.append(name);
        builder.append(", energy = ");
        builder.append(energy);
        builder.append(", production = ");
        builder.append(production);
        builder.append(", value = ");
        builder.append(value);
        builder.append(", ingredient = ");
        builder.append(ingredients);
        builder.append(", date of manufacture = ");
        builder.append(dateOfManufacture);
        return builder.toString();
    }
}