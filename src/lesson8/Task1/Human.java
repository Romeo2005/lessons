package lesson8.Task1;

class Human extends Lesson8Obj implements Running {
    private final String name;

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
