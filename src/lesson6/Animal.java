package lesson6;

public abstract class Animal {
    public void run(double length){
        System.out.println("run: true" + "\n" + '(' + length + ')');
    }

    public void jump(double height){
        System.out.println("jump: true" + "\n" + '(' + height + ')');
    }

    public void swim(double length){
        System.out.println("swim: true" + "\n" + '(' + length + ')');
    }
}
