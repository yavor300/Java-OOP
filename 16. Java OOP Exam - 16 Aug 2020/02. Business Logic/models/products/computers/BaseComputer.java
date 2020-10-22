package onlineShop.models.products.computers;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

public class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        if (this.components.isEmpty()) {
            return super.getOverallPerformance();
        } else {
            return super.getOverallPerformance() +
                    this.getComponents().stream().mapToDouble(Product::getOverallPerformance).average().orElse(0.00);
        }
    }

    @Override
    public double getPrice() {
        return super.getPrice() +
                this.getComponents().stream().mapToDouble(Product::getPrice).sum() +
                this.getPeripherals().stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        for (Component componentToCheck : this.getComponents()) {
            if (componentToCheck.getClass().getSimpleName().equals(component.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT, component.getClass().getSimpleName(),
                        this.getClass().getSimpleName(),
                        this.getId()));
            }
        }
        this.getComponents().add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        if (this.getComponents().isEmpty()) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT,
                    componentType,
                    this.getClass().getSimpleName(),
                    this.getId()));
        }

        boolean isExisting = false;
        Component component = null;
        int indexToRemove = 0;

        for (int i = 0; i < this.getComponents().size(); i++) {
            if (this.getComponents().get(i).getClass().getSimpleName().equals(componentType)) {
                component = this.getComponents().get(i);
                indexToRemove = i;
                isExisting = true;
                break;
            }
        }

//        for (Component componentToCheck : this.getComponents()) {
//            if (componentToCheck.getClass().getSimpleName().equals(componentType)) {
//                component = componentToCheck;
//
//                isExisting = true;
//                break;
//            }
//        }

        if(!isExisting) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT,
                    componentType,
                    this.getClass().getSimpleName(),
                    this.getId()));
        }

        this.getComponents().remove(indexToRemove);

        return component;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        for (Peripheral peripheralToCheck : this.getPeripherals()) {
            if (peripheralToCheck.getClass().getSimpleName().equals(peripheral.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_PERIPHERAL,
                        peripheral.getClass().getSimpleName(),
                        this.getClass().getSimpleName(),
                        this.getId()));
            }
        }
        this.getPeripherals().add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        if (this.getPeripherals().isEmpty()) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL,
                    peripheralType,
                    this.getClass().getSimpleName(),
                    this.getId()));
        }

        boolean isExisting = false;
        Peripheral peripheral = null;
        int indexToRemove = 0;

        for (int i = 0; i < this.getPeripherals().size(); i++) {
            if (this.getPeripherals().get(i).getClass().getSimpleName().equals(peripheralType)) {
                peripheral = this.getPeripherals().get(i);
                indexToRemove = i;
                isExisting = true;
                break;
            }
        }

//        for (Component componentToCheck : this.getComponents()) {
//            if (componentToCheck.getClass().getSimpleName().equals(componentType)) {
//                component = componentToCheck;
//
//                isExisting = true;
//                break;
//            }
//        }

        if(!isExisting) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL,
                    peripheralType,
                    this.getClass().getSimpleName(),
                    this.getId()));
        }

        this.getPeripherals().remove(indexToRemove);

        return peripheral;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append(System.lineSeparator())
                .append(" ")
                .append(String.format(OutputMessages.COMPUTER_COMPONENTS_TO_STRING, this.getComponents().size()))
                .append(System.lineSeparator());
        for (Component component : this.getComponents()) {
            sb.append("  ")
                    .append(component.toString())
                    .append(System.lineSeparator());
        }
        //HOPE IT WORKS...
        sb.append(" ")
                .append(String.format(OutputMessages.COMPUTER_PERIPHERALS_TO_STRING, this.getPeripherals().size(),
                this.getPeripherals().stream().mapToDouble(Product::getOverallPerformance).average().orElse(0.00)))
                .append(System.lineSeparator());
        for (Peripheral peripheral : this.getPeripherals()) {
            sb.append("  ")
                    .append(peripheral.toString())
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
