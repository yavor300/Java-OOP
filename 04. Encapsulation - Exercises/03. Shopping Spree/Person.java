package softUni.development;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    public void buyProduct(Product product) {
        this.setMoney(this.getMoney() - product.getCost());
        this.products.add(product);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private double getMoney() {
        return money;
    }

    private void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    private void setProducts(List<Product> products) {
        this.products = products;
    }

    boolean hasEnoughMoney(Product product) {
        return this.getMoney() >= product.getCost();
    }

    String getSuccessfulMessage(Product product) {
        return String.format("%s bought %s", this.getName(), product.getName());
    }

    public String getNotEnoughMoneyMessage(Product product) {
        return String.format("%s can't afford %s%n", this.getName(), product.getName());
    }
}
