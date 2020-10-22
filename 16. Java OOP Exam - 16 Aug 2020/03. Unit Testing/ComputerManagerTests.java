package computers;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ComputerManagerTests {
    @Test
    public void constructorShouldInitializeTheListProperly() {
        ComputerManager computerManager = new ComputerManager();
        Assert.assertEquals(0, computerManager.getComputers().size());
    }

    @Test
    public void getComputersShouldWorkProperly() {
        ComputerManager computerManager = new ComputerManager();
        Assert.assertEquals(0, computerManager.getComputers().size());
    }

    @Test
    public void getCountShouldWorkProperly() {
        ComputerManager computerManager = new ComputerManager();
        Assert.assertEquals(0, computerManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addComputerShouldThrowExceptionIfComputerIsNull() {
        ComputerManager computerManager = new ComputerManager();
        computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addComputerShouldThrowExceptionIfComputerIsAlreadyExisting() {
        ComputerManager computerManager = new ComputerManager();
        computerManager.addComputer(new Computer("ABS", "SONY", 20));
        computerManager.addComputer(new Computer("ABS", "SONY", 20));
    }

    @Test
    public void addComputerShouldWorkProperly() {
        ComputerManager computerManager = new ComputerManager();
        computerManager.addComputer(new Computer("ABS", "SONY", 20));
        Assert.assertEquals(1, computerManager.getComputers().size());
    }


    @Test(expected = IllegalArgumentException.class)
    public void removeComputerShouldThrowExceptionIfManufacturerIsNull() {
        ComputerManager computerManager = new ComputerManager();
        computerManager.addComputer(new Computer("ABS", "SONY", 20));
        computerManager.removeComputer(null, "SONY");
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeComputerShouldThrowExceptionIfModelIsNull() {
        ComputerManager computerManager = new ComputerManager();
        computerManager.addComputer(new Computer("ABS", "SONY", 20));
        computerManager.removeComputer("ABS", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeComputerShouldThrowExceptionIfComputerIsNotExisting() {
        ComputerManager computerManager = new ComputerManager();
        computerManager.addComputer(new Computer("ABS", "SONY", 20));
        computerManager.removeComputer("YAVOR", "RICH");
    }

    @Test
    public void removeComputerShouldReduceTheCollectionSize() {
        ComputerManager computerManager = new ComputerManager();
        computerManager.addComputer(new Computer("ABS", "SONY", 20));
        computerManager.removeComputer("ABS", "SONY");
        Assert.assertEquals(0, computerManager.getComputers().size());
    }

    @Test
    public void removeComputerShouldReturnTheRemovedComputer() {
        ComputerManager computerManager = new ComputerManager();
        computerManager.addComputer(new Computer("ABS", "SONY", 20));
        Computer computer = computerManager.removeComputer("ABS", "SONY");
        Assert.assertEquals("ABS", computer.getManufacturer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getComputersByManufacturerShouldThrowExceptionIfManufacturerIsNull() {
        ComputerManager computerManager = new ComputerManager();
        computerManager.addComputer(new Computer("ABS", "SONY", 20));
        computerManager.getComputersByManufacturer(null);
    }

    @Test
    public void getComputersByManufacturerShouldWork() {
        ComputerManager computerManager = new ComputerManager();
        computerManager.addComputer(new Computer("ABS", "SONY", 20));
        computerManager.addComputer(new Computer("ABS", "SONY-2", 20));
        List<Computer> abs = computerManager.getComputersByManufacturer("ABS");
        Assert.assertEquals(2, abs.size());
    }
}