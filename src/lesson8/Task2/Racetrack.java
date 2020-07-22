package lesson8.Task2;

public class Racetrack extends Barrier {

    protected Racetrack(int parameter) {
        super(parameter);
    }

    @Override
    public String checkMember(Member member) {
        return (member.getName() + ": " + isBarrierReached(member.getDistanceLimit()));
    }
}
