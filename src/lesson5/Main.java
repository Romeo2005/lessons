package lesson5;

public class Main {
    public static void main(String[] args) {
        Worker[] workers = new Worker[5];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("Wasya" + (i + 1), 38 + i, "Wasya" +
                    (i + 1) + "@gmail.com", "programmer " + i, "0123456" + i, i * 1000);
        }

        for (Worker worker : workers) {
            if (worker.getAge() > 40)
                System.out.println(worker.toString());
        }
    }
}
