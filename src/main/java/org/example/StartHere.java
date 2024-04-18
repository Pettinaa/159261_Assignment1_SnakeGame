package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartHere extends JFrame {
    private JButton startButton;
    private ImageIcon snakeIcon;
    JPanel mPanel = new JPanel();

    public StartHere() {
        this.setTitle("Snake Game");
        this.setSize(310, 255);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        snakeIcon = new ImageIcon("snake2.png");

        ImageIcon icon = new ImageIcon("logo3.png");
        this.setIconImage(icon.getImage());

        startButton = new JButton("start");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        GameMode gameMode = new GameMode();
                        gameMode.setVisible(true);
                    }
                });
            }
        });

        initUI();
    }

    private void initUI() {
        mPanel.setBackground(new Color(255, 255, 153));  // 浅浅的黄色
        mPanel.setLayout(null);

        JLabel snakeLabel = new JLabel(snakeIcon);
        snakeLabel.setBounds(50, 0, 200, 180);

        startButton.setBounds(100, 180, 100, 40);

        mPanel.add(snakeLabel);
        mPanel.add(startButton);

        this.add(mPanel);
    }



    public static void main(String[] args) {
        StartHere startHere = new StartHere();
        startHere.setVisible(true);
    }
}
