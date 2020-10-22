package softUni.development;

public enum BakingTechniques {
    Crispy(0.9),
    Chewy(1.1),
    Homemade(1.0);

    double calories;

    BakingTechniques(double calories) {
        this.calories = calories;
    }
}
