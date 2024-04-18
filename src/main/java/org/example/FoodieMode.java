package org.example;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FoodieMode extends GameEngine {
    Image titlePic;
    Image body;
    Image up;
    Image down;
    Image left;
    Image right;
    Image food1;
    Image food2;
    Image food3;
    Image hamburger;
    int len = 3;
    int score = 0;
    int[] snake_x = new int[750];
    int[] snake_y = new int[750];
    ArrayList<Point> foods = new ArrayList<>();
    Random rand = new Random();

    public FoodieMode() {
        initSnake();
        playBGM();
    }

    public void initSnake() {
        len = 3;
        snake_x[0] = 100;
        snake_y[0] = 125;
        snake_x[1] = 75;
        snake_y[1] = 125;
        snake_x[2] = 50;
        snake_y[2] = 125;
        foods.clear();
        foods.add(new Point(100 + 25 * rand.nextInt(28), 150 + 25 * rand.nextInt(18)));
        foods.add(new Point(100 + 25 * rand.nextInt(28), 150 + 25 * rand.nextInt(18)));
        foods.add(new Point(100 + 25 * rand.nextInt(28), 150 + 25 * rand.nextInt(18)));
        foods.add(new Point(100 + 25 * rand.nextInt(28), 150 + 25 * rand.nextInt(18)));
        score = 0;
    }

    public void playBGM() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Fuzzy.wav"));
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    private void drawStyledMessage(Graphics g, String message, int x, int y) {
        g.setColor(new Color(255, 0, 0, 50));
        g.fillRect(x - 20, y - 40, 630, 60);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(message, x, y);
    }

    @Override
    public void setupWindow() {

    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    Direction direction = Direction.RIGHT;
    boolean isStarted = false;
    boolean isFailed = false;

    @Override
    public void update(double dt) {
        if (isStarted && !isFailed) {
            for (int i = len - 1; i > 0; i--) {
                snake_x[i] = snake_x[i - 1];
                snake_y[i] = snake_y[i - 1];
            }

            switch (direction) {
                case RIGHT:
                    snake_x[0] += 25;
                    if (snake_x[0] > 850) {
                        snake_x[0] = 25;
                    }
                    break;
                case LEFT:
                    snake_x[0] -= 25;
                    if (snake_x[0] < 25) {
                        snake_x[0] = 850;
                    }
                    break;
                case UP:
                    snake_y[0] -= 25;
                    if (snake_y[0] < 75) {
                        snake_y[0] = 650;
                    }
                    break;
                case DOWN:
                    snake_y[0] += 25;
                    if (snake_y[0] > 650) {
                        snake_y[0] = 75;
                    }
                    break;
            }

            eatFood();
            checkCollision();
            mFrame.repaint();
        }
    }

    private void eatFood() {
        for (Point food : foods) {
            if (snake_x[0] == food.x && snake_y[0] == food.y) {
                if (food == foods.get(3)) {
                    len += 3;
                    score += 3;
                } else {
                    len++;
                    score++;
                }
                food.setLocation(50 + 25 * rand.nextInt(33), 100 + 25 * rand.nextInt(23));
            }
        }
    }

    private void checkCollision() {
        for (int i = 1; i < len; i++) {
            if (snake_x[i] == snake_x[0] && snake_y[i] == snake_y[0]) {
                isFailed = true;
                break;
            }
        }
    }

    @Override
    public void paintComponent() {
        try {
            titlePic = ImageIO.read(new File("title3.jpg"));
            body = ImageIO.read(new File("t4.png"));
            up = ImageIO.read(new File("up.png"));
            down = ImageIO.read(new File("down.png"));
            left = ImageIO.read(new File("left.png"));
            right = ImageIO.read(new File("right.png"));
            food1 = ImageIO.read(new File("food1.png"));
            food2 = ImageIO.read(new File("food2.png"));
            food3 = ImageIO.read(new File("food3.png"));
            hamburger = ImageIO.read(new File("hamburger.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        drawImage(titlePic, 21, 11);
        drawSolidRectangle(21, 75, 850, 600);
        changeColor(Color.white);
        drawText(750, 35, "Length:" + len);
        drawText(750, 50, "Score:" + score);

        changeColor(Color.DARK_GRAY);
        for (int i = 0; i < 24; i++) {
            drawLine(21, 75 + i * 25, 871, 75 + i * 25 );
        }
        for (int i = 0; i < 33; i++) {
            drawLine(50 + i * 25, 75, 50 + i * 25, 675 );
        }

        switch (direction) {
            case RIGHT:
                drawImage(right, snake_x[0], snake_y[0]);
                break;
            case LEFT:
                drawImage(left, snake_x[0], snake_y[0]);
                break;
            case UP:
                drawImage(up, snake_x[0], snake_y[0]);
                break;
            case DOWN:
                drawImage(down, snake_x[0], snake_y[0]);
                break;
        }

        for (int i = 1; i < len; i++) {
            drawImage(body, snake_x[i], snake_y[i]);
        }

        drawImage(food1, foods.get(0).x, foods.get(0).y);
        drawImage(food2, foods.get(1).x, foods.get(1).y);
        drawImage(food3, foods.get(2).x, foods.get(2).y);
        drawImage(hamburger, foods.get(3).x, foods.get(3).y);

        if (isStarted == false) {
            changeColor(Color.WHITE);
            drawBoldText(250, 300, "Press Space to Start");
        }

        if (isFailed == true) {
            changeColor(Color.WHITE);
            drawBoldText(200, 300, "Failed: Press Space to Restart");
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
            mFrame.repaint();
        } else if (keyCode == KeyEvent.VK_LEFT) {
            direction = Direction.LEFT;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            direction = Direction.RIGHT;
        } else if (keyCode == KeyEvent.VK_UP) {
            direction = Direction.UP;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            direction = Direction.DOWN;
        }
    }
}
