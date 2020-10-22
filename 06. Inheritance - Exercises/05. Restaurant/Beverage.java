package restaurant;

import java.math.BigDecimal;

public class Beverage extends Product {
    private Double milliliters;

    public Beverage(String name, BigDecimal price, double milliliters) {
        super(name, price);
        this.milliliters = milliliters;
    }

    public Double getMilliliters() {
        return milliliters;
    }
}
