package lesson18MultiThreading;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static final CyclicBarrier carsBarrier;
    private static Car winner;

    static {
        CARS_COUNT = 0;
        carsBarrier = new CyclicBarrier(MainClass.getCarsCount());
    }

    private final Race race;
    private final int speed;
    private final String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public static Car getWinner() {
        return winner;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            carsBarrier.await();
            MainClass.notifyAboutReadiness();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            if (winner == null)
                winner = this;

            carsBarrier.await();

            MainClass.notifyAboutFinish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resetBarrier() {
        carsBarrier.reset();
    }
}