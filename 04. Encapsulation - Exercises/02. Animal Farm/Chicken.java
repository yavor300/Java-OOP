package softUni.development;

public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    private String getName() {
        return name;
    }

    private int getAge() {
        return age;
    }

    private void setName(String name) {
        if (name.isEmpty() || name.contains(" ")) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    private void setAge(int age) {
        if (age > 15 || age < 0) {
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }

    public double productPerDay() {
        return calculateProductPerDay();
    }

    @Override
    public String toString() {
        return String.format("Chicken %s (age %d) can produce %.2f eggs per day.%n", name, age, this.productPerDay());
    }

    private double calculateProductPerDay () {
        double eggsPerDay = 0;
        if (this.age <=5) {
            eggsPerDay = 2;
        } else if (this.age <= 11) {
            eggsPerDay = 1;
        } else if (this.age <= 15) {
            eggsPerDay = 0.75;
        }
        return eggsPerDay;

    }
}
