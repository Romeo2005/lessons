package lesson6;

public class Main {
    public static void main(String[] args) {

        Animal[] animals = new Animal[4];

        animals[0] = new Dog();
        animals[1] = new Dog();

        animals[2] = new Cat();
        animals[3] = new Cat();

        for (Animal animal : animals) {
            animal.run(600);
            animal.jump(-0.2);
            animal.swim(50);
        }
    }
}
