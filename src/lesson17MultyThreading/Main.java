package lesson17MultyThreading;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Object mon = new Object();
        AtomicInteger currentNumber = new AtomicInteger(0);
        final int loopIterationsNumber = 5;

        Thread threadA = new Thread(() -> {
            char thisChar = 'A';

            for (int i = 0; i < loopIterationsNumber; i++) {
                synchronized (mon) {
                    while (currentNumber.get() != 0) {
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.print(thisChar);
                    currentNumber.set(1);
                    mon.notifyAll();
                }
            }

        });

        Thread threadB = new Thread(() -> {
            char thisChar = 'B';
            for (int i = 0; i < loopIterationsNumber; i++) {
                synchronized (mon) {
                    while (currentNumber.get() != 1) {
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.print(thisChar);
                    currentNumber.set(2);
                    mon.notifyAll();

                }
            }
        });

        Thread threadC = new Thread(() -> {
            char thisChar = 'C';
            for (int i = 0; i < loopIterationsNumber; i++) {
                synchronized (mon) {
                    while (currentNumber.get() != 2) {
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.print(thisChar);
                    currentNumber.set(0);
                    mon.notifyAll();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
