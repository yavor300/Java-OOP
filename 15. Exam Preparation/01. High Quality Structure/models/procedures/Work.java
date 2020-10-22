package robotService.models.procedures;

import robotService.models.robots.interfaces.Robot;

public class Work extends BaseProcedure {
    private static final int REMOVED_HAPPINESS = 12;
    private static final int REMOVED_ENERGY = 6;

    @Override
    public void doService(Robot robot, int procedureTime) {
        robot.setHappiness(robot.getHappiness() - REMOVED_HAPPINESS);

        robot.setEnergy(robot.getEnergy() - REMOVED_ENERGY);

        super.doService(robot, procedureTime);
    }
}
