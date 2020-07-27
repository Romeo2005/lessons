package lesson10;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[][] s = new String[4][4];
        for (int i = 0; i < s.length; i++) {
            Arrays.fill(s[i], String.valueOf(i));
        }

        try {
            myException(s);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    private static int[][] myException(String[][] s) throws MyArraySizeException, MyArrayDataException {

        if (s.length != 4 || s[0].length != 4) {
            throw new MyArraySizeException();
        }

        int current;
        int[][] result = new int[4][4];

        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                try {
                    current = Integer.parseInt(s[i][j]);
                    result[i][j] = current;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return result;
    }
}
