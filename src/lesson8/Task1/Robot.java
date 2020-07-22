package lesson8.Task1;

class Robot extends Lesson8Obj implements Running{
    private final String type;

    public Robot(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
