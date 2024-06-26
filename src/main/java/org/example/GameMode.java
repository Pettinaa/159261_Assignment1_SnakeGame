package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMode extends JFrame {
    private JButton normalModeButton;
    private JButton explosionModeButton;
    private JButton extremeModeButton;

    public GameMode() {
        setTitle("Game Modes");
        setSize(350, 310);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("logo3.png"); // 指定图标文件的路径
        setIconImage(icon.getImage());


        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        normalModeButton = new JButton("Normal Mode");
        explosionModeButton = new JButton("Foodie Mode");
        extremeModeButton = new JButton("Survival Mode");

        normalModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showIntroduction("Normal Mode", GameMode.this); // 传递GameMode实例
            }
        });

        explosionModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showIntroduction("Foodie Mode", GameMode.this);
            }
        });

        extremeModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showIntroduction("Survival Mode", GameMode.this);
            }
        });

        panel.add(normalModeButton);
        panel.add(explosionModeButton);
        panel.add(extremeModeButton);

        add(panel);
    }

    private void showIntroduction(String mode, JFrame frame) {
        IntroductionFrame introFrame = new IntroductionFrame(mode, frame);
        introFrame.setVisible(true);
        this.dispose();
    }

}
