package onlineShop.core;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
    List<Computer> computers;
    List<Peripheral> peripherals;
    List<Component> components;

    public ControllerImpl() {
        this.computers = new ArrayList<>();
        this.peripherals = new ArrayList<>();
        this.components = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        this.computers.forEach(computer -> {
            if (computer.getId() == id) {
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
            }
        });

        if (!computerType.equals("DesktopComputer") && !computerType.equals("Laptop")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }

        Computer computer;
        if (computerType.equals("DesktopComputer")) {
            computer = new DesktopComputer(id, manufacturer, model, price);
        } else {
            computer = new Laptop(id, manufacturer, model, price);
        }

        this.computers.add(computer);

        return String.format(OutputMessages.ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        Computer computer = this.computers.stream().filter(c -> c.getId() == computerId).findFirst().orElse(null);

        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        this.peripherals.forEach(peripheral -> {
            if (peripheral.getId() == id) {
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
            }
        });

        if (!peripheralType.equals(Headset.class.getSimpleName()) &&
        !peripheralType.equals(Keyboard.class.getSimpleName()) && !peripheralType.equals(Monitor.class.getSimpleName()) &&
        !peripheralType.equals(Mouse.class.getSimpleName())) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }

        Peripheral peripheral;
        if (peripheralType.equals(Headset.class.getSimpleName())) {
            peripheral = new Headset(id,manufacturer, model, price, overallPerformance, connectionType);
        } else if (peripheralType.equals(Keyboard.class.getSimpleName())) {
            peripheral = new Keyboard(id,manufacturer, model, price, overallPerformance, connectionType);
        } else if (peripheralType.equals(Monitor.class.getSimpleName())) {
            peripheral = new Monitor(id,manufacturer, model, price, overallPerformance, connectionType);
        } else {
            peripheral = new Mouse(id,manufacturer, model, price, overallPerformance, connectionType);

        }

        computer.addPeripheral(peripheral);
        this.peripherals.add(peripheral);

        return String.format(OutputMessages.ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        Computer computer = this.computers.stream().filter(c -> c.getId() == computerId).findFirst().orElse(null);

        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        Peripheral peripheral = computer.removePeripheral(peripheralType);

        for (int i = 0; i < this.peripherals.size(); i++) {
            if (this.peripherals.get(i).getId() == peripheral.getId()) {
                this.peripherals.remove(i);
                break;
            }
        }

        return String.format(OutputMessages.REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        Computer computer = this.computers.stream().filter(c -> c.getId() == computerId).findFirst().orElse(null);

        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        this.components.forEach(component -> {
            if (component.getId() == id) {
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
            }
        });

        if (!componentType.equals(CentralProcessingUnit.class.getSimpleName()) &&
                !componentType.equals(Motherboard.class.getSimpleName()) && !componentType.equals(PowerSupply.class.getSimpleName()) &&
                !componentType.equals(RandomAccessMemory.class.getSimpleName()) && !componentType.equals(SolidStateDrive.class.getSimpleName())
        && !componentType.equals(VideoCard.class.getSimpleName())) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }

        Component component;
        if (componentType.equals(CentralProcessingUnit.class.getSimpleName())) {
            component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals(Motherboard.class.getSimpleName())) {
            component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals(PowerSupply.class.getSimpleName())) {
            component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals(RandomAccessMemory.class.getSimpleName())) {
            component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals(SolidStateDrive.class.getSimpleName())) {
            component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
        } else {
            component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
        }

        computer.addComponent(component);
        this.components.add(component);

        return String.format(OutputMessages.ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        Computer computer = this.computers.stream().filter(c -> c.getId() == computerId).findFirst().orElse(null);

        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        Component component = computer.removeComponent(componentType);

        for (int i = 0; i < this.components.size(); i++) {
            if (this.components.get(i).getId() == component.getId()) {
                this.components.remove(i);
                break;
            }
        }

        return String.format(OutputMessages.REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String buyComputer(int id) {
        Computer computer = this.computers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);

        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        for (int i = 0; i < this.computers.size(); i++) {
            if (this.computers.get(i).getId() == computer.getId()) {
                this.computers.remove(i);
                break;
            }
        }

        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        Computer boughtComputer = this.computers.stream().sorted((f, s) -> Double.compare(s.getOverallPerformance(), f.getOverallPerformance()))
                .filter(c -> c.getPrice() <= budget)
                .findFirst()
                .orElse(null);

        if (boughtComputer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER, budget));
        }

        for (int i = 0; i < this.computers.size(); i++) {
            if (this.computers.get(i).getId() == boughtComputer.getId()) {
                this.computers.remove(i);
                break;
            }
        }

        return boughtComputer.toString();
    }

    @Override
    public String getComputerData(int id) {
        Computer computer = this.computers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);

        if (computer == null) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        return computer.toString();
    }
}
