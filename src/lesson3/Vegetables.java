package lesson3;

import java.util.Arrays;
import java.lang.Math;
import java.util.Scanner;

public class Vegetables {
    static Scanner scan;

    public static void main(String[] args) {
         String[] words = new String[]{"apple", "orange", "lemon", "banana", "apricot", "avocado",
                 "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango",
                  "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
         char[] guessedChars = new char[15];
         int trueVegIndex = (int)(Math.random() * (words.length - 1));
         Arrays.fill(guessedChars, '0');
         while (true){
             String trueVeg = words[trueVegIndex];
             String currentVeg;
             System.out.println("Попробуйте угадать овощ");
             scan = new Scanner(System.in);
             currentVeg = getVeg();

             if(currentVeg.equals(trueVeg))
                 break;
             else {
                 for (int i = 0; i < 15; i++) {
                     try {
                         if ((trueVeg.charAt(i) == currentVeg.charAt(i))||(guessedChars[i] == trueVeg.charAt(i))) {
                             System.out.print(trueVeg.charAt(i));
                             guessedChars[i] = trueVeg.charAt(i);
                         } else
                             System.out.print('#');
                     }catch (IndexOutOfBoundsException e){
                         try {
                             if (guessedChars[i] == trueVeg.charAt(i))
                                 System.out.print(trueVeg.charAt(i));
                             else
                                 System.out.print('#');
                         }catch(IndexOutOfBoundsException e1){
                             System.out.print('#');
                         }
                     }
                 }
                 System.out.print(' ');
             }
         }
        System.out.println("Вы угадали !");
    }

    public static String getVeg(){
        String currentAnswer;
        while(true) {
            try {
                currentAnswer = scan.next();
                break;
            }catch (Exception e){
                continue;
            }
        }
        return currentAnswer;
    }
}
