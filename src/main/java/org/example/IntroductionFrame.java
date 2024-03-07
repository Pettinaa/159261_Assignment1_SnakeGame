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
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI(mode, frame);
    }

    private void initUI(String mode, JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea introText = new JTextArea();
        introText.setText("Introduction to " + mode + ":\n\n[Your introduction text goes here]");

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
        frame.setVisible(true);

        } else if (mode.equals("Explosion Mode")) {
            // 创建 ExplosionModePanel，如果有的话
            // gamePanel = new ExplosionModePanel();
        } else if (mode.equals("Extreme Mode")) {
            // 创建 ExtremeModePanel，如果有的话
            // gamePanel = new ExtremeModePanel();
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

    // ... (Add methods to start different game modes if needed)

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                IntroductionFrame introFrame = new IntroductionFrame("Normal Mode", new JFrame());
                introFrame.setVisible(true);
            }
        });
    }
}
