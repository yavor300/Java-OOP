public class Truck extends Vehicle {
    private static final double CONSUMPTION_INCREASE = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + CONSUMPTION_INCREASE);
    }

    @Override
    protected void refuel(double liters) {
        this.setFuelQuantity(this.getFuelQuantity() + (0.95 * liters));
    }
}
