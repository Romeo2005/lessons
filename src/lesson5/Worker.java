package lesson5;

public class Worker {
    private String fullName;
    private int age;
    private String email;
    private String position;
    private String telephone;
    private int salary;

    public int getAge() {
        return age;
    }

    public Worker(String fullName, int age, String email, String position, String telephone, int salary) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.position = position;
        this.telephone = telephone;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return  "fullName = '" + fullName + '\'' +
                ", age = " + age +
                ", email = '" + email + '\'' +
                ", position = '" + position + '\'' +
                ", telephone = '" + telephone + '\'' +
                ", salary = '" + salary + '\'';
    }
}
