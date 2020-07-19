package lesson7;
public class Test {
    public static void main(String[] args) throws InterruptedException {

        Cat cat1 = new Cat("c1", 3);
        Cat cat2 = new Cat("c2", 2);
        Cat cat3 = new Cat("c3", 5);

        Plate plate1 = new Plate(2);
        Plate plate2 = new Plate(7);

        Cat[] cats = new Cat[]{cat1, cat2, cat3};
        Plate[] plates = new Plate[]{plate1, plate2};

        printInfo(cats, plates);

        Thread.sleep(11000);

        plates[0].addFood(3);
        plates[1].addFood(5);

        printInfo(cats, plates);
        System.exit(0);
    }

    private static void printInfo(Cat[] cats, Plate[] plates) {
        for (Cat cat : cats)
            cat.info();
        for (Plate plate : plates)
            plate.info();
    }
}
