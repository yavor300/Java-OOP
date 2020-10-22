package robotService.models.robots;

import robotService.common.ExceptionMessages;
import robotService.models.robots.interfaces.Robot;

public class BaseRobot implements Robot {
    private static final String DEFAULT_OWNER = "Service";

    private String name;
    private int happiness;
    private int energy;
    private int procedureTime;
    private String owner;
    private boolean isBought;
    private boolean isRepaired;

    protected BaseRobot(String name, int energy, int happiness, int procedureTime) {
        this.name = name;
        this.setEnergy(energy);
        this.setHappiness(happiness);
        this.setProcedureTime(procedureTime);
        this.setOwner(DEFAULT_OWNER);
        this.setBought(false);
        this.setRepaired(false);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getHappiness() {
        return this.happiness;
    }

    @Override
    public void setHappiness(int happiness) {
        if (happiness < 0 || happiness > 100) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_HAPPINESS);
        }
        this.happiness = happiness;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public void setEnergy(int energy) {
        if (energy < 0 || energy > 100) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ENERGY);
        }
        this.energy = energy;
    }

    @Override
    public int getProcedureTime() {
        return this.procedureTime;
    }

    @Override
    public void setProcedureTime(int procedureTime) {
        this.procedureTime = procedureTime;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public void setBought(boolean bought) {
        this.isBought = bought;
    }

    @Override
    public boolean isRepaired() {
        return this.isRepaired;
    }

    @Override
    public void setRepaired(boolean repaired) {
        this.isRepaired = repaired;
    }

    @Override
    public String toString() {
        return String.format(" Robot type: %s - %s - Happiness: %d - Energy: %d", this.getClass().getSimpleName(), this.getName(), this.getHappiness(), this.getEnergy());
    }
}
