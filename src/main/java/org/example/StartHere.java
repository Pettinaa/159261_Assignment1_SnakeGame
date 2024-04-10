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
        setupWindow();
    }

    @Override
    public void setupWindow(){
        //设置欢迎图片
        try {
            File file = new File("snake2.png");
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void update(double dt) {

    }

    @Override
    public void paintComponent() {
        changeBackgroundColor(255,255,204);
        clearBackground(500,500);
        drawImage(image, 50,10,200,180);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGame(new StartHere());
            }
        });
    }

}
