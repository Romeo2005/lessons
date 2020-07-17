package lesson6;

public class Cat extends Animal{

    private final double jumpHeight = 2 + Math.random() * 0.6 - 0.3;
    private final int runLength = 200 + (int)(Math.random() * 201 - 101);

    @Override
    public void run(double length) {
        if(length <= runLength && length > 0)
            super.run(length);
        else System.out.println("Кот так не может");
    }

    @Override
    public void jump(double height) {
        if(height <= jumpHeight && height > 0)
            super.jump(height);
        else System.out.println("Кот так не может");
    }

    @Override
    public void swim(double length) {
        System.out.println("Кот так не может");
    }
}
