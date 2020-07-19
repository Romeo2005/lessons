package lesson7;

public class Cat implements Observer, Runnable{

    protected final String name;
    private int appetite;
    private boolean satiety = false;

    public Cat(String name, int appetite) {
        this.name = name;

        if(appetite <= 25 && appetite >= 0)
            this.appetite = appetite;
        else if(appetite > 25)this.appetite = 25;
        else this.appetite = 0;

        new Thread(this).start();
        Plate.observers.add(this);
    }

    public void eat(Plate plate) {
        if(!satiety) {
            this.appetite = doEat(plate);
            if (this.appetite == 0)
                this.satiety = true;
        }
    }

    public int doEat(Plate plate){
        return appetite - plate.decreaseFood(appetite);
    }

    public void info(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", appetite=" + appetite +
                '}';
    }

    @Override
    public void update(Plate plate) {
        eat(plate);
    }
//Недавно немного поинтересовался потоками, решил попробовать применить
    @Override
    public void run() {
        long time = java.lang.System.currentTimeMillis() + 5000;
        while(true) {
            if (time == java.lang.System.currentTimeMillis()) {
                if(appetite <= 25)
                appetite++;
                time = java.lang.System.currentTimeMillis() + 5000;
            }

            if(appetite > 0) satiety = false;
        }
    }
}
