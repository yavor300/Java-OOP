package softUni.development;

class Topping {
    private String toppingType;
    private double weight;
    private static final int BASE_CALORIES_PER_GRAM = 2;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    public double calculateCalories() {
        return this.weight * BASE_CALORIES_PER_GRAM  * ToppingsTypes.valueOf(this.toppingType).calories;
    }

    private void setToppingType(String toppingType) {
        this.toppingType = String.valueOf(ToppingsTypes.valueOf(toppingType));
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(String.format(ExceptionsMessages.INVALID_TOPPING_WEIGHT, this.toppingType));
        }
        this.weight = weight;
    }
}
