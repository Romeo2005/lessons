package lesson7;

import java.util.ArrayList;
import java.util.List;

public interface Observed {
    List <Cat> observers = new ArrayList<>();
    default void notifyAllObservers(Plate plate){
        for (Cat cat : observers) {
            cat.update(plate);
        }
    }
}
