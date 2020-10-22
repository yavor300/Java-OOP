package softUni.development;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;
    private int numberOfTopings;

    public Pizza(String name, int numberOfToppings) {
        this.setName(name);
        this.setToppings(numberOfToppings);
    }

    public String getName() {
        return name;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public void addTopping(Topping topping) {
        if (numberOfTopings >= this.toppings.size() + 1) {
            this.toppings.add(topping);
        } else {
            throw new IllegalArgumentException(ExceptionsMessages.INVALID_NUMBER_OF_TOPPINGS);
        }

    }

    public double getOverallCalories() {
        double resultCalories = 0;
        resultCalories += this.dough.calculateCalories();
        for (Topping topping : this.toppings) {
            resultCalories += topping.calculateCalories();
        }
        return resultCalories;
    }

    private void setName(String name) {
        if (name.trim().isEmpty() || name.length() > 15) {
            throw new IllegalArgumentException(ExceptionsMessages.INVALID_PIZZA_NAME);
        }
        this.name = name;
    }

    private void setToppings(int numberOfToppings) {
        if (numberOfToppings < 0 || numberOfToppings > 10) {
            throw new IllegalArgumentException(ExceptionsMessages.INVALID_NUMBER_OF_TOPPINGS);
        }
        this.toppings = new ArrayList<>();
        this.numberOfTopings = numberOfToppings;
    }
}
