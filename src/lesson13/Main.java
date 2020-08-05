package lesson13;

import java.util.Arrays;

public class Main {
    static int SIZE = 10000000;
    static double[] array = new double[SIZE];

    public static void main(String[] args) throws InterruptedException {
        Arrays.fill(array, 1);
        long oneThreadTime = fillUsing1Thread();
        Arrays.fill(array, 1);
        long twoThreadsTime = fillUsing2Threads();
        System.out.println("One thread: " + oneThreadTime);
        System.out.println("Two threads: " + twoThreadsTime);
        System.out.println("Difference: " + (oneThreadTime - twoThreadsTime));
    }

    private static long fillUsing2Threads() {
        long time1;
        long time2;

        time1 = System.currentTimeMillis();
        double[] array1= new double[SIZE/2];
        double[] array2 = new double[SIZE/2];

        System.arraycopy(array, 0, array1, 0, SIZE/2);
        System.arraycopy(array, SIZE/2, array2, 0, SIZE/2);

        new newThread(array1);
        fillWithNewValue(array2);
        System.arraycopy(array1, 0, array, 0, array1.length - 1);
        System.arraycopy(array2, 0, array, array1.length - 1, array2.length - 1);
        time2 = System.currentTimeMillis();
        return time2 - time1;
    }

    private static long fillUsing1Thread() {
        long time1;
        long time2;

        time1 = System.currentTimeMillis();
        fillWithNewValue(array);
        time2 = System.currentTimeMillis();

        return time2 - time1;
    }

    static void fillWithNewValue(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0);
        }
    }
}

