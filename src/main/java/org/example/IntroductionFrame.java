package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroductionFrame extends JFrame {
    private JButton startButton;
    private JButton backButton;

    public IntroductionFrame(String mode, JFrame frame) {
        setTitle(mode + " Introduction");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("logo3.png"); // 指定图标文件的路径
        setIconImage(icon.getImage());


        initUI(mode, frame);
    }

    private void initUI(String mode, JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

       JTextArea introText = new JTextArea();
       // JTextPane introText = new JTextPane();

//        introText.setText("Introduction to " + mode + ":\n\n[Your introduction text goes here]");

        //introText.setContentType("text/html"); // 设置内容类型为HTML
        // 为每种模式添加玩法介绍
        if (mode.equals("Normal Mode")) {
            introText.setText(
                    "      A classic mode where you enjoy casual gameplay and progress \n through levels.\n\n" +
                    "  The game rules are as follows:\n" +
                    "  1. When the snake head touches the wall, the game ends.\n" +
                    "  2. When the snake head touches its own body, the game ends.\n" +
                    "  3. When a snake eats food, its body length and sum score are \n      increased by one.\n");
        } else if (mode.equals("Foodie Mode")) {
            introText.setText(
                    "      Satisfy your hunger while playing! Collect food items to score \n and eating junk food can make your body bigger.\n\n" +
                            "  The game rules are as follows:\n" +
                            "  1. When the snake touches the boundary of the game grid, it will \n      not die and its body will pass through from the other side.\n" +
                            "  2. When the snake head touches its own body, the game ends.\n" +
                            "  3. When a snake eats normal food, its body length and sum score \n      are increased by one.\n" +
                            "  4. When a snake eats a hamburger, its body length and score are \n      increased by three.\n"
            );
        } else{
            introText.setText(
                    "      Survive as long as you can against increasing challenges. \nCollect food and dodge obstacles to stay alive.\n\n" +
                            "  The game rules are as follows:\n" +
                            "  1. When the snake head touches the wall, the game ends.\n" +
                            "  2. When the snake head touches its own body, the game ends.\n" +
                            "  3. When a snake eats normal food, its body length and sum score \n      are increased by one.\n" +
                            "  4. When a snake touches a burning flame, its body and score are \n      reduced by one.\n" +
                            "  5. When a snake hits a bomb, its body length and score are reduced \n      by three.\n" +
                            "  6. When the length of the snake's body is equal to 0, which means \n      that the snake only has its head left, an emergency first aid kit will \n      appear. When the snake's head touches the first aid kit, the snake's body length \n      and score are increased by five.\n"
            );
           }

        introText.setEditable(false); // 禁止编辑
        introText.setBackground(new Color(173, 216, 230)); // 设置背景色为浅蓝色
      //  introText.setOpaque(false); // 设置为透明，这样背景色就能显示出来


        startButton = new JButton("Start Game");
        backButton = new JButton("Back to Menu");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start the selected game mode
                startGame(mode);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the GameMode menu
                returnToMenu(frame);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(backButton);

        panel.add(introText, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

    }

    private void startGame(String mode) {

        //判断用户选择的是哪种游戏模式
        if (mode.equals("Normal Mode")) {
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 900, 720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new NormalModePanel());
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("logo3.png");
        frame.setIconImage(icon.getImage());
        frame.setVisible(true);


        } else if (mode.equals("Foodie Mode")) {
            JFrame frame = new JFrame();
            frame.setBounds(10, 10, 900, 720);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new FoodieModePanel());
            frame.setLocationRelativeTo(null);
            ImageIcon icon = new ImageIcon("logo3.png"); // 指定图标文件的路径
            frame.setIconImage(icon.getImage());
            frame.setVisible(true);


        } else if (mode.equals("Survival Mode")) {
            // 创建 ExtremeModePanel，如果有的话
            // gamePanel = new ExtremeModePanel();
            JFrame frame = new JFrame();
            frame.setBounds(10, 10, 900, 720);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new SurvivalModePanel());
            frame.setLocationRelativeTo(null);
            ImageIcon icon = new ImageIcon("logo3.png"); // 指定图标文件的路径
            frame.setIconImage(icon.getImage());
            frame.setVisible(true);


        } else {
            // 处理其他模式
            return;
        }

    }

    private void returnToMenu(JFrame frame) {
        GameMode gameMode = new GameMode();
        gameMode.setVisible(true);
        this.dispose();  // Close the current frame (IntroductionFrame)
    }


}
