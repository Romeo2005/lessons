package lesson6;

public class Dog extends Animal{
    private final double jumpHeight = 0.5 + Math.random() * 0.6 - 0.3;
    private final int swimLength = 10 + (int)(Math.random() * 12 - 6);
    private final int runLength = 500 + (int)(Math.random() * 201 - 101);

    @Override
    public void run(double length) {
        if(length <= runLength && length > 0)
            super.run(length);
        else System.out.println("Собака так не может");
    }

    @Override
    public void jump(double height) {
        if(height <= jumpHeight && height > 0)
            super.jump(height);
        else System.out.println("Собака так не может");
    }

    @Override
    public void swim(double length) {
        if(length <= swimLength && length > 0)
            super.swim(length);
        else System.out.println("Собака так не может");
    }
}
