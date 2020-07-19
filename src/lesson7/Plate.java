package lesson7;

public class Plate implements Observed{
    private int food;

    public Plate() {
        this(0);
    }

    public Plate(int food) {
        this.food = food;
        Cat.subjects.add(this);
        if(food >= 0)
            notifyAllObservers(this);
    }

    public void addFood(int food){
        this.food += food;
        if(food >= 0)
            notifyAllObservers(this);
    }

    public void info(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Plate{" +
                "food=" + food +
                '}';
    }

    public int getFood() {
        return food;
    }

    public int decreaseFood(int foodCount) {
        int firstFoodValue = this.food;
        for (int i = 0; i < foodCount; i++) {
            if(this.food > 0)
                this.food--;
            else break;
        }
        return firstFoodValue - this.food;
    }
}
