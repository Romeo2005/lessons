package lesson8.Task2;

public class Wall extends Barrier{

    protected Wall(double parameter) {
        super(parameter);
    }

    @Override
    public String checkMember(Member member){
        return (member.getName() + ": " + isBarrierReached(member.getHeightLimit()));
    }
}
