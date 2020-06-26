package lesson1;

public class Main {
    public static void main(String[] args) {

        byte varByte = 127;
        short varShort = 32767;
        int varInt = 2147483647;
        long varLong = 9223372036854775807L;

        float varFloat = 25.45943f;
        double varDouble = 456.0903921;

        char varChar = 'A';
        String varStr = "Hello World !!!";

        boolean varBool = true;
        System.out.println(checkLeapTask8(2020));
    }

    public static double findResultTask3(double valA, double valB, double valC, double valD){
        return valA * (valB + (valC / valD));
    }

    public static boolean checkSumTask4(double valA, double valB){
        return (valA + valB >= 10) && (valA + valB <= 20);
    }

    public static String checkPositivenessTask5(int val){
        if(val >= 0) return "positive";
        else return "negative";
    }

    public static boolean checkNegativenessTask6(int val){
        return val < 0;
    }

    public static String WriteNameTask7(String name){
        return "Привет, указанно имя " + name + " !";
    }

    public static boolean checkLeapTask8(int year){
        boolean cond1, cond2, cond3;
        cond1 = year % 4 == 0;
        cond2 = year % 400 == 0;
        cond3 = year % 100 != 0;

        return (cond1&&cond3)||cond2;
    }

}