package lesson3;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.Math;

public class RandomNumberTask1 {

    static Scanner scan;


    public static void main(String[] args) {
        int trueAnswer = (int)(Math.random()*100);
        int currentAnswer;
        int resAnswer;
        int tryCounter = 1;
        int tryLost;
        final int MAX_TRY_AMOUNT = 3;
        while(true) {
            currentAnswer = makeQuestionGuess();
            if (currentAnswer == trueAnswer) {
                System.out.println("Вы угадали!");
                resAnswer = makeQuestionResume("Желаете продолжить?\n1 - Да      0 - Нет");
                if(resAnswer == 1)
                    trueAnswer = (int) (Math.random() * 100);
                else if(resAnswer != 0)
                    continue;
                else
                    break;

            } else if (currentAnswer > trueAnswer) {
                if(tryCounter < MAX_TRY_AMOUNT) {
                    tryLost = MAX_TRY_AMOUNT - tryCounter;
                    System.out.println("Ответ меньше\nосталось " + tryLost + " попыток");
                    tryCounter++;
                }else {
                    resAnswer = makeQuestionResume("Вы проиграли! Правильный ответ " + trueAnswer + "\nЖелаете продолжить?\n1 - Да      0 - Нет");
                    if(resAnswer == 1)
                        trueAnswer = (int) (Math.random() * 100);
                    else if(resAnswer != 0)
                        continue;
                    else
                        break;
                }
            }
            else {
                if(tryCounter < MAX_TRY_AMOUNT) {
                    tryLost = MAX_TRY_AMOUNT - tryCounter;
                    System.out.println("Ответ больше\nосталось " + tryLost + " попыток");
                    tryCounter++;
                }else {
                    resAnswer = makeQuestionResume("Вы проиграли! Правильный ответ " + trueAnswer + "\nЖелаете продолжить?\n1 - Да      0 - Нет");
                    if(resAnswer == 1)
                        trueAnswer = (int) (Math.random() * 100);
                    else if(resAnswer != 0)
                        continue;
                    else
                        break;
                }
            }
        }
        System.out.println("Вы вышли из программы");
    }

    public static int makeQuestionGuess(){
        int currentAnswer;

        for(int i = 0; true; i++) {
            if(i == 0)
                System.out.println("Попробуйте угадать число от 1 до 100");
            else
                System.out.println("Неверный формат данных. Попробуйте еще ");
            try {
                scan = new Scanner(System.in);
                currentAnswer = scan.nextInt();
                //scan.close();         !!! С єтой сторокой выдает NoSuchElementException!!!
                break;
            }catch (InputMismatchException e){
                continue;
            }
        }
        return currentAnswer;
    }

    public static int makeQuestionResume(String quest){
        int answer;
        for(int i = 0; true; i++) {
            if(i == 0)
                System.out.println(quest);
            else
                System.out.println("Неверный формат данных. Попробуйте еще\nЖелаете продолжить?\n1 - Да      0 - Нет");
            try {
                scan = new Scanner(System.in);
                answer = scan.nextInt();
                //scan.close();                                     !!! С єтой сторокой выдает NoSuchElementException при вводе любой цыфры кроме 0 и 1!!!
                if((answer == 1)||(answer == 0))
                    break;
            }catch (InputMismatchException e){
                continue;
            }
        }
        return answer;
    }
}
