package lesson8.Task2;

public abstract class Barrier {
    protected final double parameter;

    protected Barrier(double parameter){
        this.parameter = parameter;
    }

    protected String isBarrierReached(double valueGot){
        if (valueGot >= parameter)
            return "Barrier reached !";
        else return "No";
    }

    public abstract String checkMember(Member member);
}
