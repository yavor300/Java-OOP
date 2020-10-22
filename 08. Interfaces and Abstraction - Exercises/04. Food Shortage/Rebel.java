public class Rebel implements Buyer, Person {
    private String name;
    private int age;
    private int food;
    private String group;

    public Rebel(String name, int age, String group) {
        this.name = name;
        this.age = age;
        this.food = 0;
        this.group = group;
    }

    private void setFood(int food) {
        this.food = food;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public void buyFood() {
        this.setFood(this.getFood() + 5);
    }

    @Override
    public int getFood() {
        return this.food;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }
}
