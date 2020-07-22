package lesson8.Task1;

import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("black");
        Cat cat2 = new Cat("red");

        Human human1 = new Human("Peter");
        Human human2 = new Human("Sam");

        Robot robot1 = new Robot("flying");
        Robot robot2 = new Robot("swimming");

        List <Lesson8Obj> objects = new ArrayList<>();

        objects.add(cat1);
        objects.add(cat2);
        objects.add(human1);
        objects.add(human2);
        objects.add(robot1);
        objects.add(robot2);

        for(Lesson8Obj obj : objects) {
            if (obj instanceof Human) {
                ((Human) obj).run();
                ((Human) obj).jump();
            }
            else if (obj instanceof Cat) {
                ((Cat) obj).run();
                ((Cat) obj).jump();
            }
            else if (obj instanceof Robot) {
                ((Robot) obj).run();
                ((Robot) obj).jump();
            }
        }
    }

}
