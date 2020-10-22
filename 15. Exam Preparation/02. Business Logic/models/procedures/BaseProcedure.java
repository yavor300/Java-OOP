package robotService.models.procedures;

import robotService.common.ExceptionMessages;
import robotService.models.procedures.interfaces.Procedure;
import robotService.models.robots.interfaces.Robot;

import java.util.ArrayList;
import java.util.List;

public class BaseProcedure implements Procedure {
    private List<Robot> robots;

    protected BaseProcedure() {
        this.robots = new ArrayList<>();
    }

    private List<Robot> getRobots() {
        return this.robots;
    }

    @Override
    public String history() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName()).append(System.lineSeparator());

        for (Robot robot : this.getRobots()) {
            sb.append(robot.toString()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public void doService(Robot robot, int procedureTime) {
        if (robot.getProcedureTime() < procedureTime) {
            throw  new IllegalArgumentException(ExceptionMessages.INSUFFICIENT_PROCEDURE_TIME);
        }
        robot.setProcedureTime(robot.getProcedureTime() - procedureTime);
        this.getRobots().add(robot);
    }
}
