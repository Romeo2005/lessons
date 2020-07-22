package lesson8.Task1;

interface Running extends Jumping{
    default void run() {
        System.out.println(determinateObj((Lesson8Obj) this) + " runs");
    }
}
