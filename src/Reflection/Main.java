package Reflection;

import java.lang.reflect.InvocationTargetException;

public class Main {

    @BeforeSuite
    private void start() {
        System.out.println("Start");
    }

    @AfterSuite
    private void after() {
        System.out.println("End");
    }

    @Test(priority = 1)
    private void test1() {
        System.out.println(1);
    }

    @Test(priority = 2)
    private void test2() {
        System.out.println(2);
    }

    @Test(priority = 3)
    private void test3() {
        System.out.println(3);
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        TestMaker.start(Main.class);
    }
}
