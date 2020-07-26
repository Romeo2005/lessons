package lesson9;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TicTacToe extends JFrame {

    private static int SIZE = 3;
    static final char DOT_EMPTY = '*';
    static final char DOT_HUMAN = 'X';
    static final char DOT_AI = 'O';

    static char[][] map = new char[SIZE][SIZE];
    private static GameButton[][] mapGUI = new GameButton[SIZE][SIZE];
    private static final Random random = new Random();
    private static boolean isButtonPressed = false;
    private static TicTacToe game;
    private static GridLayout mainLayout;
    private static boolean sizePos = false;

    public TicTacToe() {
        setTitle("TicTacToe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 400);
        setVisible(true);
        setLayout(mainLayout);
        drawMap();
        setVisible(true);
    }

    private void drawMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                mapGUI[i][j] = new GameButton(i, j);
                this.add(mapGUI[i][j].getButton());
            }
        }
    }


    public static void main(String[] args) {
        enterSize();
        turnGame();
    }


    private static void turnGame() {
        initMap();

        try {
            playGame();
        } catch (InterruptedException ignored) {
        }
    }

    private static void playGame() throws InterruptedException {
        while (true) {
            humanTurn();
            if (isButtonPressed) {
                GameButton.blockField();
                Thread.sleep(500);
                if (checkEnd(DOT_HUMAN)) {
                    break;
                }
                Thread.sleep(500);
                aiTurn();
                resize();
                Thread.sleep(500);
                if (checkEnd(DOT_AI)) {
                    break;
                }
                isButtonPressed = false;
                GameButton.unblockField();
            }
        }
    }

    private static void resize() {
        if (sizePos)
            game.setSize(game.getWidth() + 1, game.getHeight() + 1);
        else game.setSize(game.getWidth() - 1, game.getHeight() - 1);
    }

    private static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }


    private static void humanTurn() {
        for (int i = 0; i < mapGUI.length; i++) {
            for (int j = 0; j < mapGUI[i].length; j++) {
                if (mapGUI[i][j].getSymbol() == DOT_HUMAN)
                    map[i][j] = DOT_HUMAN;
            }
        }
    }

    private static void aiTurn() {
        int rowNumber, colNumber;
        int[] danger = checkDangerousSituation(DOT_HUMAN);
        int[] win = checkDangerousSituation(DOT_AI);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
        if (win != null) {
            int row = win[0];
            int col = win[1];

            if (map[row][col] == DOT_EMPTY) {
                map[row][col] = DOT_AI;
                if (mapGUI[row][col].getSymbol() != DOT_AI)
                    mapGUI[row][col].aiTurn();
                return;
            }
        }

        if (danger != null) {
            int row = danger[0];
            int col = danger[1];

            if (map[row][col] == DOT_EMPTY) {
                map[row][col] = DOT_AI;
                if (mapGUI[row][col].getSymbol() != DOT_AI)
                    mapGUI[row][col].aiTurn();
                return;
            }
        }

        if (map[SIZE / 2][SIZE / 2] == DOT_EMPTY) {
            map[SIZE / 2][SIZE / 2] = DOT_AI;
            if (mapGUI[SIZE / 2][SIZE / 2].getSymbol() != DOT_AI)
                mapGUI[SIZE / 2][SIZE / 2].aiTurn();
            return;
        }

        do {
            rowNumber = random.nextInt(SIZE) + 1;
            colNumber = random.nextInt(SIZE) + 1;

        } while (map[rowNumber - 1][colNumber - 1] != DOT_EMPTY);

        map[rowNumber - 1][colNumber - 1] = DOT_AI;
        if (mapGUI[rowNumber - 1][colNumber - 1].getSymbol() != DOT_AI)
            mapGUI[rowNumber - 1][colNumber - 1].aiTurn();
    }

    private static boolean checkEnd(char symbol) {
        if (checkWin(symbol)) {
            if (symbol == DOT_HUMAN) {
                showDialog("You won !");
            }
            if (symbol == DOT_AI) {
                showDialog("You lost !");
            }
            return true;
        }

        if (isMapFull()) {
            showDialog("Draw");
            return true;
        }
        return false;
    }

    public static boolean checkWin(char symbol) {
        return checkDiagonals(symbol) || checkVerticals(symbol);
    }

    public static boolean checkDiagonals(char symbol) {
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
        return diagonal1 || diagonal2;
    }

    public static boolean checkVerticals(char symbol) {
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

        for (boolean i : verticals) answer = answer || i;

        return answer;
    }

    public static int[] checkDangerousSituation(char symbol) {
        if (checkDangerousDiagonals(symbol) != null)
            return checkDangerousDiagonals(symbol);

        if (checkDangerousVerticals(symbol) != null)
            return checkDangerousVerticals(symbol);

        return null;
    }

    public static int[] checkDangerousDiagonals(char symbol) {
        int count = 0;
        int counter1 = 0;
        int counter2 = 0;
        int[] current1 = new int[2];
        char[][] current2 = new char[SIZE][SIZE];

        Arrays.fill(current1, -1);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == j) {

                    if (map[i][j] == symbol)
                        counter1++;
                    else {
                        current1[0] = i;
                        current1[1] = j;
                    }

                    if (map[i][SIZE - 1 - count] == symbol) {
                        counter2++;
                    }
                    current2[i][SIZE - 1 - count] = map[i][SIZE - 1 - count];

                    if (map[SIZE - 1 - count][j] == symbol)
                        counter2++;
                    current2[SIZE - 1 - count][j] = map[SIZE - 1 - count][j];
                    count++;
                }
            }
            if (current1[0] == -1 && current1[1] == -1)
                Arrays.fill(current1, SIZE - 1);

            if (counter1 == SIZE - 1)
                return current1;
            if (counter2 > SIZE) {
                for (int j = 0; j < SIZE; j++) {
                    for (int k = 0; k < SIZE; k++) {
                        if (current2[j][k] == DOT_EMPTY)
                            return new int[]{j, k};
                    }
                }
            }
        }

        return null;
    }

    public static int[] checkDangerousVerticals(char symbol) {
        int counter1 = 0;
        int counter2 = 0;
        int[] current1 = new int[2];
        int[] current2 = new int[2];

        Arrays.fill(current1, -1);
        Arrays.fill(current2, -1);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                if (map[i][j] == symbol)
                    counter1++;
                else {
                    current1[0] = i;
                    current1[1] = j;
                }

                if (map[j][i] == symbol)
                    counter2++;
                else {
                    current2[0] = j;
                    current2[1] = i;
                }
            }

            if (counter1 == SIZE - 1) {
                if (map[current1[0]][current1[1]] == DOT_EMPTY)
                    return current1;
            }
            if (counter2 == SIZE - 1) {
                if (map[current2[0]][current2[1]] == DOT_EMPTY)
                    return current2;
            }
            counter1 = 0;
            counter2 = 0;
        }

        return null;
    }

    private static boolean isMapFull() {
        for (char[] chars : map) {
            for (char aChar : chars) {
                if (aChar == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }

    public static void update() {
        isButtonPressed = true;
    }

    private static void showDialog(String text) {
        JOptionPane.showMessageDialog(new Frame(), text);
    }

    private static void enterSize() {
        try {
            enterDialog();
            map = new char[SIZE][SIZE];
            mapGUI = new GameButton[SIZE][SIZE];
            mainLayout = new GridLayout(SIZE, SIZE);
            game = new TicTacToe();
        } catch (NumberFormatException e) {
            enterSize();
        }
    }

    private static void enterDialog() {
        SIZE = Integer.parseInt(JOptionPane.showInputDialog("Enter field size"));
    }
}
