package softUni.development;

class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;
    private static final int BASE_CALORIES_PER_GRAM = 2;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    public double calculateCalories() {
        return this.weight * BASE_CALORIES_PER_GRAM * FlourTypes.valueOf(this.flourType).calories * BakingTechniques.valueOf(this.bakingTechnique).calories;
    }

    private void setFlourType(String flourType) {
        try {
            this.flourType = String.valueOf(FlourTypes.valueOf(flourType));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ExceptionsMessages.INVALID_FLOUR_TYPE_OR_BAKING_TECHNIQUE);
        }

    }

    private void setBakingTechnique(String bakingTechnique) {
        try {
            this.bakingTechnique = String.valueOf(BakingTechniques.valueOf(bakingTechnique));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ExceptionsMessages.INVALID_FLOUR_TYPE_OR_BAKING_TECHNIQUE);
        }
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException(ExceptionsMessages.INVALID_DOUGH_WEIGHT);
        }
        this.weight = weight;
    }
}
