package lesson17MultyThreading;

public class Counter {
    private int counter = 0;

    public void increase() {
        counter ++;
    }

    public int getCounter() {
        return counter;
    }

    public void decrease() {
        counter --;
    }
}
