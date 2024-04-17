package org.example;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NormalMode extends GameEngine {
    Image title;
    Image body;
    Image up;
    Image down;
    Image left;
    Image right;
    Image food;
    Image wall;

    int len = 3;
    int score = 0;
    int[] snakex = new int[750];
    int[] snakey = new int[750];
    int foodx;
    int foody;
    Random rand = new Random();

    boolean isStarted = false;
    boolean isFailed = false;
    String direction = "R";

    public NormalMode() {
        initSnake();
        playBGM();
    }

    @Override
    public void setupWindow() {

    }

    @Override
    public void update(double dt) {
        if (isStarted && !isFailed) {
            moveSnake();
            checkCollision();
            mPanel.repaint();
        }
    }

    public void moveSnake() {
        for (int i = len - 1; i > 0; i--) {
            snakex[i] = snakex[i - 1];
            snakey[i] = snakey[i - 1];
        }

        switch (direction) {
            case "R":
                snakex[0] += 25;
                if (snakex[0] == 825) {
                    isFailed = true;
                }
                break;
            case "L":
                snakex[0] -= 25;
                if (snakex[0] == 50) {
                    isFailed = true;
                }
                break;
            case "U":
                snakey[0] -= 25;
                if (snakey[0] == 100) {
                    isFailed = true;
                }
                break;
            case "D":
                snakey[0] += 25;
                if (snakey[0] == 625) {
                    isFailed = true;
                }
                break;
        }

        if (snakex[0] == foodx && snakey[0] == foody) {
            len++;
            score++;
            foodx = 50 + 25 * rand.nextInt(33);
            foody = 100 + 25 * rand.nextInt(23);
        }

        for (int i = 1; i < len; i++) {
            if (snakex[i] == snakex[0] && snakey[i] == snakey[0]) {
                isFailed = true;
                break;
            }
        }
    }

    public void checkCollision() {
        if (snakex[0] == foodx && snakey[0] == foody) {
            len++;
            score++;
            foodx = 50 + 25 * rand.nextInt(33);
            foody = 100 + 25 * rand.nextInt(23);
        }

        for (int i = 1; i < len; i++) {
            if (snakex[i] == snakex[0] && snakey[i] == snakey[0]) {
                isFailed = true;
                break;
            }
        }
    }

    public void initSnake() {
        len = 3;
        snakex[0] = 100;
        snakey[0] = 125;
        snakex[1] = 75;
        snakey[1] = 125;
        snakex[2] = 50;
        snakey[2] = 125;
        foodx = 100 + 25 * rand.nextInt(28);
        foody = 150 + 25 * rand.nextInt(18);
        direction = "R";
        score = 0;
    }

    @Override
    public void paintComponent() {
        try {
            title = ImageIO.read(new File("title3.jpg"));
            body = ImageIO.read(new File("t4.png"));
            up = ImageIO.read(new File("up.png"));
            down = ImageIO.read(new File("down.png"));
            left = ImageIO.read(new File("left.png"));
            right = ImageIO.read(new File("right.png"));
            food = ImageIO.read(new File("food2.png"));
            wall = ImageIO.read(new File("wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        drawImage(title, 21, 11);
        drawSolidRectangle(21, 75, 850, 600);
        changeColor(Color.white);
        drawText(750, 35, "Length:" + len);
        drawText(750, 50, "Score:" + score);

        for (int i = 25; i <= 850; i += 25) {
            drawImage(wall, i, 75);
            drawImage(wall, i, 650);
        }
        for (int i = 75; i <= 650; i += 25) {
            drawImage(wall, 25, i);
            drawImage(wall, 850, i);
        }

        changeColor(Color.DARK_GRAY);
        for (int i = 0; i < 23; i++) {
            drawLine(50, 100 + i * 25, 850, 100 + i * 25);
        }
        for (int i = 0; i < 33; i++) {
            drawLine(75 + i * 25, 100, 75 + i * 25, 650);
        }

        if (direction.equals("R")) {
            drawImage(right, snakex[0], snakey[0]);
        } else if (direction.equals("L")) {
            drawImage(left, snakex[0], snakey[0]);
        } else if (direction.equals("U")) {
            drawImage(up, snakex[0], snakey[0]);
        } else {
            drawImage(down, snakex[0], snakey[0]);
        }

        for (int i = 1; i < len; i++) {
            drawImage(body, snakex[i], snakey[i]);
        }

        drawImage(food, foodx, foody);

        if (!isStarted) {
            changeColor(Color.WHITE);
            drawBoldText(250, 300, "Press Space to Start");
        }

        if (isFailed) {
            changeColor(Color.WHITE);
            drawBoldText(200, 300, "Failed: Press Space to Restart");
        }
    }

    public void playBGM() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("bgm.wav"));
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException e) {
            System.out.println("Unsupported audio format: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.out.println("Line unavailable: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO exception: " + e.getMessage());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFailed) {
                isFailed = false;
                initSnake();
            } else {
                isStarted = !isStarted;
            }
            mPanel.repaint();
        } else if (keyCode == KeyEvent.VK_LEFT) {
            direction = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            direction = "R";
        } else if (keyCode == KeyEvent.VK_UP) {
            direction = "U";
        } else if (keyCode == KeyEvent.VK_DOWN) {
            direction = "D";
        }
    }
}
