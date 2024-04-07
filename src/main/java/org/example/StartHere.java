package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartHere extends GameEngine{
    private JButton startButton;
    private ImageIcon snakeIcon;
    private JLabel snakeLabel;

    public StartHere(){
        mFrame.setTitle("Snake Game");
        mFrame.setSize(310, 270);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setLocationRelativeTo(null);

        setWindowSize(500, 500);

        snakeIcon = new ImageIcon("snake2.png");

        startButton = new JButton("start");
        startButton.setBounds(100, 190, 90, 30); // 设置按钮的位置和大小

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
        mPanel.add(startButton); // 将按钮添加到面板中

        snakeLabel = new JLabel(snakeIcon);
        snakeLabel.setBounds(50, 10, 200, 180); // 设置图片的位置和大小
        snakeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        snakeLabel.setOpaque(true);
        snakeLabel.setBackground(Color.WHITE);
        snakeLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 8));


        snakeLabel.setBorder((new RoundedBorder(Color.BLACK, 10)));
        mPanel.add(snakeLabel);


        initUI();
    }

    private void setButtonStyle(JButton button) {
        button.setBackground(new Color(0, 128, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        button.setBounds(100, 350, 100, 40);
    }

    private void initUI() {
       // mPanel.setBackground(Color.BLUE);
//        JLabel snakeLabel = new JLabel(snakeIcon);
//        snakeLabel.setBounds(50, -30, 200, 180); // 设置图片的位置和大小

        mPanel.setBackground(Color.GRAY);

        // 设置布局管理器为 null
        mPanel.setLayout(null);
        // 将图片添加到面板中
       // mPanel.add(snakeLabel);
        // 刷新 UI
        mPanel.revalidate();
        mPanel.repaint();

    }


    @Override
    public void update(double dt) {

    }

    @Override
    public void paintComponent() {
        changeBackgroundColor(Color.GRAY);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartHere startHere = new StartHere();
                startHere.mFrame.setVisible(true); // 设置窗口可
            }
        });
    }

}
