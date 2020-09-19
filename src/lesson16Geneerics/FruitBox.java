package lesson16Geneerics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FruitBox<F extends Fruit> {
    private final List<F> fruits;

    public void addFruit(F fruit) {
        fruits.add(fruit);
    }

    private void removeUpperFruit() {

    }

    public FruitBox(List<F> fruits) {
        this.fruits = fruits;
    }

    public FruitBox() {
        fruits = new ArrayList<>();
    }

    public void moveFruitsIntoAnotherBox(FruitBox<F> box) {
        for (F f : fruits)
            box.addFruit(f);
        fruits.clear();
    }

    public float getWeight() {
        float sum = 0;

        for (F f : fruits)
            sum += f.getWeight();

        return sum;
    }

    public boolean compareTo(FruitBox<?> box) {
/*
        return Math.abs(this.getWeight() - box.getWeight()) <= 0.5; - в данном случае смысла не имеет
        так как веса а не могут отличаться меньше чем на 0.5f
*/
        return this.getWeight() == box.getWeight();
    }
}
