package softUni.development;

public enum FlourTypes {
    White(1.5),
    Wholegrain(1.0);
    double calories;

    FlourTypes(double calories) {
        this.calories = calories;
    }
}
