package lesson8.Task2;

public class ObstacleCourse {
    Barrier[] course;

    public ObstacleCourse(Barrier... course){
        this.course = course;
    }

    public void passCourse(Member member) {
        String finalString = null;
        for(Barrier barrier : course) {
            finalString = barrier.checkMember(member);

            if (finalString.endsWith("No")) {
                System.out.println(member.getName() + ": Barrier not reached !");
                return;
            }
        }
        System.out.println(finalString);
    }
}
