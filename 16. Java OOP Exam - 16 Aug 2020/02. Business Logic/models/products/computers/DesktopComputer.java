package onlineShop.models.products.computers;

import onlineShop.models.products.components.BaseComponent;

public class DesktopComputer extends BaseComputer {
    public DesktopComputer(int id, String manufacturer, String model, double price) {
        super(id, manufacturer, model, price, 15);
    }
}
