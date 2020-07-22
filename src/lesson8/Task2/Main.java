package lesson8.Task2;

public class Main {
    public static void main(String[] args) {
        Member[] members = new Member[5];
        Barrier[] barriers = new Barrier[3];

        barriers[0] = new Wall(2);
        barriers[1] = new Racetrack(12);
        barriers[2] = new Wall(3);

        members[0] = new Member("Richard", 25, 5);
        members[1] = new Member("Bob", 7, 2);
        members[2] = new Member("Sam", 12, 3);
        members[3] = new Member("Bill", 17, 4);
        members[4] = new Member("Harry", 8, 3);

        ObstacleCourse course = new ObstacleCourse(barriers);

        for (Member member : members) {
            course.passCourse(member);
        }

        //new Wall(2).checkMember();
    }
}
