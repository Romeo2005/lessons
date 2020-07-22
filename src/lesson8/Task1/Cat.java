package lesson8.Task1;

class Cat extends Lesson8Obj implements Running{
    private final String color;

    public Cat(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
