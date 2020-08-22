package lesson16Geneerics;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        testTask1();
        testTask2();
        testTask3();
    }

    private static void testTask3() {
        FruitBox<Apple> appleBox1 = new FruitBox<>();
        FruitBox<Apple> appleBox2 = new FruitBox<>();
        FruitBox<Orange> orangeBox1 = new FruitBox<>();
        FruitBox<Orange> orangeBox2 = new FruitBox<>();

        for (int i = 0; i < 4; i++) {
            appleBox1.addFruit(new Apple());
            orangeBox1.addFruit(new Orange());
        }

        for (int i = 0; i < 8; i++) {
            appleBox2.addFruit(new Apple());
        }

        for (int i = 0; i < 4; i++) {
            orangeBox2.addFruit(new Orange());
        }

        appleBox1.moveFruitsIntoAnotherBox(appleBox2);
        orangeBox1.moveFruitsIntoAnotherBox(orangeBox2);

        System.out.println(appleBox2.compareTo(orangeBox2));
        System.out.println(appleBox1.compareTo(orangeBox2));
        System.out.println(appleBox1.getWeight());
        System.out.println(appleBox2.getWeight());
        System.out.println(orangeBox1.getWeight());
        System.out.println(orangeBox2.getWeight());
    }

    private static void testTask2() {
        String[] s = new String[]{"Hello", "World", "!!!"};
        ArrayList<String> sList = convertArrayToArrayListTask2(s);
        sList.add("added");
        System.out.println(sList);
    }

    private static void testTask1() {
        Integer[] a = new Integer[]{1, 2, 3};
        changeElementsPlacesTask1(a, 0, 2);
        System.out.println(Arrays.toString(a));
    }

    private static <T> void changeElementsPlacesTask1(T[] arr, int indexA, int indexB) {
        T a = arr[indexA];
        T b = arr[indexB];
        arr[indexA] = b;
        arr[indexB] = a;
    }

    private static <T> ArrayList<T> convertArrayToArrayListTask2(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }
}
