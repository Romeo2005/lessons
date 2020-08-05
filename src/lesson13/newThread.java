package lesson13;

public class newThread extends Thread{
    private final double[] arr;

    newThread(double[] array){
        arr = array;
        this.start();
    }

    @Override
    public void run() {
        Main.fillWithNewValue(arr);
    }
}
