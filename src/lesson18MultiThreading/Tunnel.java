package lesson18MultiThreading;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private static final int carsSimultaneously = MainClass.getCarsCount() / 2;
    private static final Semaphore runSimultaneously = new Semaphore(carsSimultaneously);

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                runSimultaneously.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
                runSimultaneously.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}