package lesson8.Task1;

interface Jumping {
    default String determinateObj(Lesson8Obj obj){

        String className;
        if (obj instanceof Human)
            className = "Human " + ((Human)obj).getName();
        else if (obj instanceof Cat)
            className = "Cat " + ((Cat)obj).getColor();
        else if (obj instanceof Robot)
            className = "Robot " + ((Robot)obj).getType();
        else className = null;

        return className;
    }

    default void jump() {
        System.out.println(determinateObj((Lesson8Obj) this) + " jumps");
    }
}
