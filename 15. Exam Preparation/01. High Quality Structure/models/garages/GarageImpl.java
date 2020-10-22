package robotService.models.garages;

import robotService.common.ExceptionMessages;
import robotService.models.garages.interfaces.Garage;
import robotService.models.robots.interfaces.Robot;

import java.util.LinkedHashMap;
import java.util.Map;

public class GarageImpl implements Garage {
    private static final int CAPACITY = 10;
    private static final int ONE_ROBOT_COUNT = 1;

    private Map<String, Robot> robots;

    public GarageImpl() {
        this.robots = new LinkedHashMap<>();
    }

    @Override
    public Map<String, Robot> getRobots() {
        return this.robots;
    }

    @Override
    public void manufacture(Robot robot) {
        if (this.getRobots().size() + ONE_ROBOT_COUNT > CAPACITY) {
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }

        if (this.getRobots().containsKey(robot.getName())) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_ROBOT, robot.getName()));
        }

        this.getRobots().put(robot.getName(), robot);
    }

    @Override
    public void sell(String robotName, String ownerName) {
        if (!this.getRobots().containsKey(robotName)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NON_EXISTING_ROBOT, robotName));
        }

        Robot robot = this.getRobots().get(robotName);
        robot.setOwner(ownerName);
        robot.setBought(true);
        this.getRobots().remove(robotName);
    }
}
