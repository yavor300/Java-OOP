package softUni.development;

public enum ToppingsTypes {
    Meat(1.2),
    Veggies(0.8),
    Cheese(1.1),
    Sauce(0.9);

    double calories;
    ToppingsTypes(double calories) {
        this.calories = calories;
    }
}
