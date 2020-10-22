package robotService.core;

import robotService.common.ExceptionMessages;
import robotService.common.OutputMessages;
import robotService.core.interfaces.Controller;
import robotService.models.garages.GarageImpl;
import robotService.models.garages.interfaces.Garage;
import robotService.models.procedures.Charge;
import robotService.models.procedures.Repair;
import robotService.models.procedures.Work;
import robotService.models.procedures.interfaces.Procedure;
import robotService.models.robots.Cleaner;
import robotService.models.robots.Housekeeper;
import robotService.models.robots.interfaces.Robot;

public class ControllerImpl implements Controller {
    private static final String ROBOT_TYPE_CLEANER = "Cleaner";
    private static final String ROBOT_TYPE_HOUSEKEEPER = "Housekeeper";

    private static final String CHARGE_PROCEDURE = "Charge";
    private static final String REPAIR_PROCEDURE = "Repair";
    private static final String WORK_PROCEDURE = "Work";


    private Garage garage;
    private Procedure charge;
    private Procedure repair;
    private Procedure work;

    public ControllerImpl() {
        this.garage = new GarageImpl();
        this.charge = new Charge();
        this.repair = new Repair();
        this.work = new Work();
    }

    @Override
    public String manufacture(String robotType, String name, int energy, int happiness, int procedureTime) {
        if (!ROBOT_TYPE_CLEANER.equals(robotType) && !ROBOT_TYPE_HOUSEKEEPER.equals(robotType)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_ROBOT_TYPE, robotType));
        }

        Robot robot;
        if (ROBOT_TYPE_CLEANER.equals(robotType)) {
            robot = new Cleaner(name, energy, happiness, procedureTime);
        } else {
            robot = new Housekeeper(name, energy, happiness, procedureTime);
        }
        this.garage.manufacture(robot);

        return String.format(OutputMessages.ROBOT_MANUFACTURED, name);
    }

    @Override
    public String repair(String robotName, int procedureTime) {
        if (!this.garage.getRobots().containsKey(robotName)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NON_EXISTING_ROBOT, robotName));
        }

        Robot robot = this.garage.getRobots().get(robotName);
        this.repair.doService(robot, procedureTime);

        return String.format(OutputMessages.REPAIR_PROCEDURE, robotName);
    }

    @Override
    public String work(String robotName, int procedureTime) {
        if (!this.garage.getRobots().containsKey(robotName)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NON_EXISTING_ROBOT, robotName));
        }

        Robot robot = this.garage.getRobots().get(robotName);
        this.work.doService(robot, procedureTime);

        return String.format(OutputMessages.WORK_PROCEDURE, robotName, procedureTime);
    }

    @Override
    public String charge(String robotName, int procedureTime) {
        if (!this.garage.getRobots().containsKey(robotName)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NON_EXISTING_ROBOT, robotName));
        }

        Robot robot = this.garage.getRobots().get(robotName);
        this.charge.doService(robot, procedureTime);

        return String.format(OutputMessages.CHARGE_PROCEDURE, robotName);
    }

    @Override
    public String sell(String robotName, String ownerName) {
        if (!this.garage.getRobots().containsKey(robotName)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NON_EXISTING_ROBOT, robotName));
        }

        this.garage.sell(robotName, ownerName);

        return String.format(OutputMessages.SELL_ROBOT, ownerName, robotName);
    }

    @Override
    public String history(String procedureType) {
        if (CHARGE_PROCEDURE.equals(procedureType)) {
            return this.charge.history();
        } else if (REPAIR_PROCEDURE.equals(procedureType)) {
            return this.repair.history();
        } else if (WORK_PROCEDURE.equals(procedureType)) {
            return this.work.history();
        }
        return null;
    }
}
