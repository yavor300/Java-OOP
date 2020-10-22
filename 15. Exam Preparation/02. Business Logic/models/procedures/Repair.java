package robotService.models.procedures;

import robotService.common.ExceptionMessages;
import robotService.models.robots.interfaces.Robot;

public class Repair extends BaseProcedure {
    private static final int REMOVED_HAPPINESS = 5;

    @Override
    public void doService(Robot robot, int procedureTime) {
        if (robot.isRepaired()) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ALREADY_REPAIRED, robot.getName()));
        }

        super.doService(robot, procedureTime);

        robot.setRepaired(true);

        robot.setHappiness(robot.getHappiness() - REMOVED_HAPPINESS);
    }
}
