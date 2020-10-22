public class Truck extends Vehicle {
    private static final double CONSUMPTION_INCREASE = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + CONSUMPTION_INCREASE, tankCapacity);
    }

    @Override
    protected void refuel(double liters) {
        if (liters <= 0) {
            System.out.println("Fuel must be a positive number");
        } else if (liters + this.getFuelQuantity() > this.getTankCapacity()) {
            System.out.println("Cannot fit fuel in tank");
        } else {
            this.setFuelQuantity(this.getFuelQuantity() + (0.95 *liters));
        }
    }
}
