package lesson2;

import java.util.ArrayList;
import java.util.Arrays;

public class Task_2 {
    public static void main(String[] args) {
        int[] array = new int[]{-9, 5, -25, 21};
        shiftArrTask7(array, 3);
        System.out.println(Arrays.toString(array));
        System.out.println(sumPartsTask6(array));
        findMaxMinTask5(array);
        diagonalTask4(25);
    }

    public static void shiftArrTask7(int[] arr, int n){
        int lastElement, firstElement;
        final int LAST_ELEMENT_INDEX = arr.length - 1;

        if(n >= 0) {
            for (int i = 0; i < n; i++) {
                lastElement = arr[LAST_ELEMENT_INDEX];
                for (int j = LAST_ELEMENT_INDEX; true; j--) {
                    try {
                        arr[j] = arr[j - 1];
                    } catch (IndexOutOfBoundsException e) {
                        break;
                    }
                }
                arr[0] = lastElement;
            }
        }else{
            n *= -1;
            for (int i = 0; i < n; i++) {
                firstElement = arr[0];
                for (int j = 0; true; j++) {
                    try {
                        arr[j] = arr[j + 1];
                    } catch (Exception e) {
                        break;
                    }
                }
                arr[LAST_ELEMENT_INDEX] = firstElement;
            }
        }
    }

    public static boolean sumPartsTask6(int[] arr){
       boolean returnTF = false;
       if(arr.length == 1){
           System.out.println("Невозможно разделить массив из одного єлемента");
           return false;
       }
        for(int i = 0; i < arr.length; i++)
        {
            if(sumElements(Arrays.copyOfRange(arr, 0, i)) == sumElements(Arrays.copyOfRange(arr, i, arr.length))) {
                returnTF = true;
                break;
            }
        }
        return returnTF;
    }

    public static void findMaxMinTask5(int[] arr){
        int min = arr[0];
        int max = arr[0];

        for (int i : arr) {
            if(i > max)
                max = i;
            if(i < min)
                min = i;
        }
        System.out.println("max element: " + max + "\nmin element: " + min);
    }

    public static void diagonalTask4(int cols){
        int count = 0;
        int[][] arr = new int[cols][cols];

        for(int i = 0; i < cols; i++){
            for(int j = 0; j < cols; j++)
                if((i == j)) {
                    arr[i][j] = 1;
                    arr[cols - 1 - count][j] = 1;
                    arr[i][cols - 1 - count] = 1;
                    count++;
                }
                else arr[i][j] = 0;
        }

        for(int[] i : arr){
            System.out.println();
            for(int j : i)
                System.out.printf("%2d", j);
        }
    }

//Вспомогательная для решения задач
    public static int sumElements(int[] arr){
        int sum = 0;
        for(int i : arr)
            sum += i;
        return sum;
    }


}
