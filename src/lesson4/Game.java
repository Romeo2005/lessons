package lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static final int SIZE = 4;
    public static final int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '*';
    public static final char DOT_HUMAN = 'X';
    public static final char DOT_AI = 'O';
    public static final String EMPTY = " ";
    public static final String FIRST_EMPTY_CHAR = "  ";

    public static char[][] map = new char[SIZE][SIZE];
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
        turnGame();
    }

    private static void turnGame() {
        InitMap();

        printMap();

        playGame();
    }

    private static void playGame() {
        while(true) {
            humanTurn();
            printMap();
            if(checkEnd(DOT_HUMAN))
                break;
            aiTurn();
            printMap();
            if(checkEnd(DOT_AI))
                break;
        }
    }




    private static void InitMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        printMapHeader();

        printMapRows();
    }

    private static void printMapHeader(int i) {
        System.out.print(i + 1 + EMPTY);
    }

    private static void printMapHeader() {
        System.out.print(FIRST_EMPTY_CHAR);
        for (int i = 0; i < SIZE; i++) {
            printMapHeader(i);
        }
        System.out.println();
    }

    private static void printMapRows() {
        for (int i = 0; i < SIZE; i++) {
            printMapHeader(i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + EMPTY);
            }
            System.out.println();
        }
    }

    private static void humanTurn() {
        int rowNumber, colNumber;
        do {
            System.out.println("Ход человека. Введите номер столбца и строки");
            System.out.print("Столбец = ");
            colNumber = scanner.nextInt();
            System.out.print("Строка = ");
            rowNumber = scanner.nextInt();

        }while(isCellValid(rowNumber, colNumber));

        map[rowNumber - 1][colNumber - 1] = DOT_HUMAN;
    }

    public static boolean isCellValid(int rowNumber, int colNumber) {
        rowNumber --;
        colNumber --;
        if ((rowNumber < 0 || rowNumber >= SIZE)||( colNumber < 0 || colNumber >= SIZE)) {
            System.out.println("Проверьте значения строки и столбца");
            return true;
        }
        if (map[rowNumber][colNumber] != DOT_EMPTY) {
            System.out.println("Вы выбрали занятое поле");
            return true;
        }

        return false;
    }

    private static void aiTurn() {
        int rowNumber, colNumber;
        System.out.println("Ходит машина");
        int[] danger = checkDangerousSituation(DOT_HUMAN);
        int[] win = checkDangerousSituation(DOT_AI);

        if(win != null) {
            int row = win[0];
            int col = win[1];

            if(map[row][col] == DOT_EMPTY) {
                map[row][col] = DOT_AI;
                return;
            }
        }

        if(danger != null) {
            int row = danger[0];
            int col = danger[1];

            if(map[row][col] == DOT_EMPTY) {
                map[row][col] = DOT_AI;
                return;
            }
        }

        if(map[SIZE / 2][SIZE / 2] == DOT_EMPTY) {
            map[SIZE / 2][SIZE / 2] = DOT_AI;
            return;
        }

        do {
        rowNumber = random.nextInt(SIZE) + 1;
        colNumber = random.nextInt(SIZE) + 1;

        }while(map[rowNumber - 1][colNumber - 1] != DOT_EMPTY);

        map[rowNumber - 1][colNumber - 1] = DOT_AI;
    }
    private static boolean checkEnd(char symbol) {
        if(checkWin(symbol)){
            if (symbol == DOT_HUMAN)
                System.out.println("Вы победили !");
            if (symbol == DOT_AI)
                System.out.println("Вы проиграли !");

            return true;
        }

        if(isMapFull()){
            System.out.println("ничья");
            return true;
        }
        return false;
    }

    public static boolean checkWin(char symbol) {
/*        if(map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        if(map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;*/


        return checkDiagonals(symbol)||checkVerticals(symbol);
    }

    public static boolean checkDiagonals(char symbol){
        int count = 0;
        boolean diagonal1 = true;
        boolean diagonal2 = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == j) {
                    if ((map[i][j] != symbol))
                        diagonal1 = false;
                    if ((map[i][SIZE - 1 - count] != symbol) || (map[SIZE - 1 - count][j] != symbol))
                        diagonal2 = false;
                    count++;
                }
            }
        }
        return diagonal1||diagonal2;
    }

    public static boolean checkVerticals(char symbol){
        ArrayList<Boolean> verticals = new ArrayList<>();
        boolean answer = false;
        boolean currentVal1 = true;
        boolean currentVal2 = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                currentVal1 = currentVal1 && (map[i][j] == symbol);
                currentVal2 = currentVal2 && (map[j][i] == symbol);
            }
            verticals.add(currentVal1);
            verticals.add(currentVal2);
            currentVal1 = true;
            currentVal2 = true;
        }

        for(boolean i : verticals) answer = answer || i;

        return answer;
    }

    public static int[] checkDangerousSituation(char symbol){
        if(checkDangerousDiagonals(symbol) != null)
            return checkDangerousDiagonals(symbol);

        if(checkDangerousVerticals(symbol) != null)
            return checkDangerousVerticals(symbol);

        return null;
    }

    public static int[] checkDangerousDiagonals(char symbol){
        int count = 0;
        int counter1 = 0;
        int counter2 = 0;
        int[] current1 = new int[2];
        char[][] current2 = new char[SIZE][SIZE];

        Arrays.fill(current1, -1);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == j) {

                    if(map[i][j] == symbol)
                        counter1 ++;
                    else{
                        current1[0] = i;
                        current1[1] = j;
                    }

                    if(map[i][SIZE - 1 - count] == symbol) {
                        counter2++;
                    }
                    current2[i][SIZE - 1 - count] = map[i][SIZE - 1 - count];

                    if(map[SIZE - 1 - count][j] == symbol)
                        counter2 ++;
                    current2[SIZE - 1 - count][j] = map[SIZE - 1 - count][j];
                    count++;
                }
            }
            if(current1[0] == -1 && current1[1] == -1)
                Arrays.fill(current1, SIZE - 1);

            if(counter1 == SIZE - 1)
                return current1;
            if(counter2 > SIZE){
                for (int j = 0; j < SIZE; j++) {
                    for (int k = 0; k < SIZE; k++) {
                        if(current2[j][k] == DOT_EMPTY)
                            return new int[]{j, k};
                    }
                }
            }
        }

        return null;
    }

    public static int[] checkDangerousVerticals(char symbol){
        int counter1 = 0;
        int counter2 = 0;
        int[] current1 = new int[2];
        int[] current2 = new int[2];

        Arrays.fill(current1, -1);
        Arrays.fill(current2, -1);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                if(map[i][j] == symbol)
                    counter1 ++;
                else {
                    current1[0] = i;
                    current1[1] = j;
                }

                if(map[j][i] == symbol)
                    counter2 ++;
                else{
                    current2[0] = j;
                    current2[1] = i;
                }
            }

            if(counter1 == SIZE - 1) {
                return current1;
            }
            if(counter2 == SIZE - 1) {
                return current2;
            }
            counter1 = 0;
            counter2 = 0;
        }

        return null;
    }
    private static boolean isMapFull() {
        for(char[] chars : map){
            for(char aChar : chars){
                if(aChar == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }
}
