package lesson9;

import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    static int[] pos = new int[2];
    static class MyWindow extends JFrame {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Point p = new Point(100, 100);
        }

        public MyWindow(){
            Pane pane = new Pane();
            JButton button = new JButton();
            BorderLayout layout = new BorderLayout();
            setTitle("Hello");
            setSize(700, 400);
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLayout(layout);
            add(button, BorderLayout.CENTER);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println(e.getX() + " " + e.getY());
                    pos[0] = e.getX();
                    pos[1] = e.getY();
                }
            });
        }
    }

    public static void main(String[] args) {
        new MyWindow();
    }
}
