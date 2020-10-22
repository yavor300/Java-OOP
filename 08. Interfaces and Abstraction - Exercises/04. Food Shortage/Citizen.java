public class Citizen implements Identifiable, Buyer, Birthable, Person {
    private String name;
    private int age;
    private int food;
    private String id;
    private String birthDate;

    public Citizen(String name, int age, String id, String birthDate) {
        this.name = name;
        this.age = age;
        this.food = 0;
        this.id = id;
        this.birthDate = birthDate;
    }

    private void setFood(int food) {
        this.food = food;
    }

    @Override
    public String getId() {
        return this.id;
    }


    @Override
    public void buyFood() {
        this.setFood(this.getFood() + 10);
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

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
