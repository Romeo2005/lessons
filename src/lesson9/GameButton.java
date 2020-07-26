package lesson9;

import javax.swing.*;
import java.awt.*;

public class GameButton extends JFrame {
    private static boolean flag = true;
    private final JButton button;
    private boolean wasFieldTurned = false;
    private boolean wasGameStarted = false;
    private boolean privateFlag;
    private Graphics2D g2d;
    private static boolean isFieldBlocked = false;
    private final int[] coordinates = new int[2];

    public GameButton(int col, int row) {
        coordinates[0] = col;
        coordinates[1] = row;
        button = new JButton() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(5));
                if (!wasFieldTurned)
                    privateFlag = flag;
                if (wasGameStarted) {
                    if (!privateFlag) {
                        paintCross(g2d);
                        TicTacToe.map[coordinates[0]][coordinates[1]] = TicTacToe.DOT_HUMAN;
                    } else {
                        paintNull(g2d);
                        TicTacToe.map[coordinates[0]][coordinates[1]] = TicTacToe.DOT_AI;
                    }
                    wasFieldTurned = true;
                }
            }
        };

        button.addActionListener(e -> {
            if (!wasGameStarted && !isFieldBlocked) {
                flag = !flag;
                TicTacToe.update();
                wasGameStarted = true;
            }

        });
    }

    private void paintNull(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.drawOval(0, 0, getButton().getWidth(), getButton().getHeight());
        update(g2d);
    }

    private void paintCross(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.drawLine(0, 0, getButton().getWidth(), button.getHeight());
        g2d.drawLine(getButton().getWidth(), 0, 0, button.getHeight());
    }

    public JButton getButton() {
        return button;
    }

    public char getSymbol() {
        if (wasGameStarted) {
            return privateFlag ? TicTacToe.DOT_AI : TicTacToe.DOT_HUMAN;
        } else return TicTacToe.DOT_EMPTY;
    }

    public void aiTurn(){
        if (!wasGameStarted) {
            privateFlag = true;
            flag = !flag;
        }
        wasGameStarted = wasFieldTurned = true;
    }


    public static void blockField() {
        isFieldBlocked = true;
    }

    public static void unblockField () {
        isFieldBlocked = false;
    }
}
