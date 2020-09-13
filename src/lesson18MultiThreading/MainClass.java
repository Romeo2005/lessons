package lesson18MultiThreading;

public class MainClass {
    private static final int CARS_COUNT = 4;
    private static boolean takesCompetitionPlace;

    public static int getCarsCount() {
        return CARS_COUNT;
    }

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (Car car : cars) {
            new Thread(car).start();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    public static synchronized void notifyAboutReadiness() {
        if (!takesCompetitionPlace) {
            takesCompetitionPlace = true;
            Car.resetBarrier();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }
    }

    public static synchronized void notifyAboutFinish() {
        if (takesCompetitionPlace) {
            takesCompetitionPlace = false;
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            System.out.println(Car.getWinner().getName() + " победил");
        }
    }
}