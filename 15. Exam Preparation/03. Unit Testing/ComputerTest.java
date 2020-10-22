package computers;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ComputerTest {
    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowExceptionIfNameIsNull() {
        Computer computer = new Computer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowExceptionIfNameIsEmptyString() {
        Computer computer = new Computer("");
    }

    @Test
    public void constructorShouldSetTheNameValueProperly() {
        Computer computer = new Computer("Apple");
        String expected = "Apple";
        String actual = computer.getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void constructorShouldInitializeTheListProperly() {
        Computer computer = new Computer("Apple");
        Assert.assertEquals(0, computer.getParts().size());
    }

    @Test
    public void getNameShouldWorkProperly() {
        Computer computer = new Computer("Apple");
        Assert.assertEquals("Apple", computer.getName());
    }

    @Test
    public void getPartsShouldWorkProperly() {
        Computer computer = new Computer("Apple");
        Assert.assertEquals(0, computer.getParts().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPartMethodShouldThrowAnExceptionIfPartIsNull() {
        Computer computer = new Computer("Apple");
        computer.addPart(null);
    }

    @Test
    public void addPartMethodShouldAddPartSuccessfully() {
        Computer computer = new Computer("Apple");
        computer.addPart(new Part("Part", 2.0));
        Assert.assertEquals(1, computer.getParts().size());
    }

    @Test
    public void removePartMethodShouldRemovePartSuccessfully() {
        Computer computer = new Computer("Apple");
        Part part = new Part("Part", 2.0);
        computer.addPart(part);
        Assert.assertTrue(computer.removePart(part) && computer.getParts().size() == 0);
    }

    @Test
    public void getTotalPriceMethodShouldReturnTheCorrectSum() {
        Computer computer = new Computer("Apple");
        Part part1 = new Part("Part", 2.0);
        Part part2 = new Part("Part", 5.1);
        computer.addPart(part1);
        computer.addPart(part2);
        Assert.assertEquals(7.1, computer.getTotalPrice(), 0.1);
    }

    @Test
    public void getPartByNameMethodShouldReturnTheCorrectPart() {
        Computer computer = new Computer("Apple");

        Part part1 = new Part("Apple", 2.0);
        Part part2 = new Part("HTC", 5.1);

        computer.addPart(part1);
        computer.addPart(part2);

        Assert.assertEquals("HTC", computer.getPart("HTC").getName());
    }

    @Test
    public void getPartByNameMethodShouldReturnNullIfThePartIsNotExisting() {
        Computer computer = new Computer("Apple");

        Part part1 = new Part("Apple", 2.0);
        Part part2 = new Part("HTC", 5.1);

        computer.addPart(part1);
        computer.addPart(part2);

        Assert.assertEquals("HTC", computer.getPart("HTC").getName());
    }
}