package onlineShop.models.products.peripherals;

import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.BaseProduct;

public class BasePeripheral extends BaseProduct implements Peripheral {
    private String connectionType;

    protected BasePeripheral(int id, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        super(id, manufacturer, model, price, overallPerformance);
        this.connectionType = connectionType;
    }

    @Override
    public String getConnectionType() {
        return this.connectionType;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(OutputMessages.PERIPHERAL_TO_STRING, this.getConnectionType());
    }
}
