package lesson7;

import java.util.ArrayList;
import java.util.List;

public interface Observer {

    List<Plate> subjects = new ArrayList<>();
    void update(Plate plate);

}
