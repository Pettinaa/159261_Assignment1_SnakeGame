package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static java.awt.Color.gray;

public class StartHere extends GameEngine{
    private JButton startButton;
    private ImageIcon snakeIcon;
    private Image image;
    private JLabel snakeLabel;



    public StartHere(){
        init();
        setupWindow();
    }

    @Override
    public void setupWindow(){
        mFrame.setTitle("Snake Game");
        mFrame.setSize(310, 270);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setLocationRelativeTo(null);
        mFrame.setVisible(true);

        //设置logo
        ImageIcon icon = new ImageIcon("logo3.png"); // 指定图标文件的路径
        mFrame.setIconImage(icon.getImage());

        //设置欢迎图片
        try {
            File file = new File("snake2.png");
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //开始按钮
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


    }

    @Override
    public void init() {

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
        changeBackgroundColor(255,255,204);
        clearBackground(500,500);
        drawImage(image, 50,10,200,180);
       // drawCircle(50,50,10);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //StartHere startHere = new StartHere();
                createGame(new StartHere());
                //startHere.mFrame.setVisible(true);
            }
        });
    }

}
