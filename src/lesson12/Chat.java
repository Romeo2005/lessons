package lesson12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Chat extends JFrame {
    private JTextField enterTextArea = new JTextField();
    private JTextArea mainTextArea = new JTextArea();
    private JButton sendButton = new JButton("Send");

    private Chat() {
        setElementsProperties();
        initWindow();

        JPanel downPanel = formDownPanel();

        add(mainTextArea, BorderLayout.CENTER);
        add(downPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void initWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        setSize(900, 600);
        setLayout(new BorderLayout());
    }

    private void setElementsProperties(){
        Font font = new Font("hello", Font.PLAIN, 20);
        mainTextArea.setBackground(new Color(201, 255, 245));
        mainTextArea.setFont(font);
        mainTextArea.setEditable(false);
        enterTextArea.setFont(font);
        sendButton.addActionListener(e -> {
            sendMessage();
        });

        enterTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    sendMessage();
                }
            }
        });
    }

    private void sendMessage() {
        if (enterTextArea.getText() != null) {
            mainTextArea.setText(!mainTextArea.getText().equals("") ? mainTextArea.getText() + '\n' + enterTextArea.getText() : enterTextArea.getText());
            enterTextArea.setText(null);
        }
    }

    private JPanel formDownPanel() {
        JPanel downPanel = new JPanel();
        downPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.9;
        c.weighty = 1;

        downPanel.add(enterTextArea, c);

        c.weightx = 0.1;
        downPanel.add(sendButton, c);
        return downPanel;
    }

    public static void main(String[] args) {
        new Chat();
    }
}
