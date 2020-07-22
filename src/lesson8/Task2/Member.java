package lesson8.Task2;

public class Member {
    private final double distanceLimit;
    private final double heightLimit;
    private final String name;

    public Member(String name, double distanceLimit, double heightLimit) {
        this.distanceLimit = distanceLimit;
        this.heightLimit = heightLimit;
        this.name = name;
    }

    public double getDistanceLimit() {
        return distanceLimit;
    }

    public double getHeightLimit() {
        return heightLimit;
    }

    public String getName() {
        return name;
    }
}
