public class Car extends Vehicle {
    private static final double CONSUMPTION_INCREASE = 0.9;

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + CONSUMPTION_INCREASE, tankCapacity);
    }
}
