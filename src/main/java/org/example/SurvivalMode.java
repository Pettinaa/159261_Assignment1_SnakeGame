package org.example;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SurvivalMode extends GameEngine{
    Image title;

    Image body;
    Image up;
    Image down;
    Image left;
    Image right;
    Image food;
    Image wall;
    Image bomb;
    Image fire;
    Image medicine;
    int len = 3;
    int score = 0;
    int[] snakex = new int[750];
    int[] snakey = new int[750];
    //墙
    private ArrayList<Point> walls = new ArrayList<>();
    // 定义格子大小常量
    String direction = "R";//头的方向
    boolean isStarted = false;
    boolean isFailed = false;
    boolean emergency = false;
    int foodx;
    int foody;
    int bombx;
    int bomby;
    int firex;
    int firey;
    int mediciney;
    int medicinex;
    Random rand = new Random();
    public SurvivalMode() {
        initSnake();
        playBGM();
    }
    public void initSnake(){
        len = 3;
        snakex[0] = 100;
        snakey[0] = 125;
        snakex[1] = 75;
        snakey[1] = 125;
        snakex[2] = 50;
        snakey[2] = 125;
        foodx = 100 + 25 * rand.nextInt(28);
        foody = 150 + 25 * rand.nextInt(18);

        bombx = 100 + 25 * rand.nextInt(28);
        bomby = 150 + 25 * rand.nextInt(18);

        firex = 100 + 25 * rand.nextInt(28);
        firey = 150 + 25 * rand.nextInt(18);

        medicinex = 50 + 25 * rand.nextInt(33);
        mediciney = 100 + 25 * rand.nextInt(23);


        direction = "R";
        score = 0;
    }
    public void playBGM() {
        try {
            // 加载音频文件
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("bgm.wav"));

            // 获取音频格式
            AudioFormat format = audioInputStream.getFormat();

            // 创建数据行信息对象
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // 获取Clip对象
            Clip clip = (Clip) AudioSystem.getLine(info);

            // 打开音频流
            clip.open(audioInputStream);

            // 播放音频
            clip.start();

            // 如果你想让音频循环播放，可以加入以下代码
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
    public void setupWindow() {

    }

    @Override
    public void update(double dt) {
        if(isStarted && !isFailed){
            //让蛇移动
            for(int i = len - 1; i > 0; i-- ){
                snakex[i] = snakex[i - 1];
                snakey[i] = snakey[i - 1];
            }
            //确定每个图片坐标和舌头方向（左上角位置
            if(direction == "R"){
                snakex[0] = snakex[0] + 25;
                //让蛇撞墙死
                if(snakex[0] == 825){
                    isFailed = true;
                }
            }else if(direction == "L"){
                snakex[0] = snakex[0] - 25;
                //让蛇穿透墙壁
                if(snakex[0] == 50){
                    isFailed = true;
                }
            }else if(direction == "U"){
                snakey[0] = snakey[0] - 25;
                if(snakey[0] == 100){
                    isFailed = true;
                }
            }else if(direction == "D"){
                snakey[0] = snakey[0] + 25;
                if(snakey[0] == 625){
                    isFailed = true;
                }
            }


            //身体+1 & 重新生成食物
            if(snakex[0] == foodx && snakey[0] == foody){
                len++;
                score++;
                foodx = 50 + 25 * rand.nextInt(33);
                foody = 100 + 25 * rand.nextInt(23);
            }

            //身体-3 & 重新生成炸弹
            if(snakex[0] == bombx && snakey[0] == bomby){
                len-=3;
                score-=3;
                if(len < 0){
                    isFailed = true;
                }
                bombx = 50 + 25 * rand.nextInt(33);
                bomby = 100 + 25 * rand.nextInt(23);
            }

            //身体-1 & 重新生成火焰
            if(snakex[0] == firex && snakey[0] == firey){
                len-=1;
                score-=1;
                if(len < 0){
                    isFailed = true;
                }
                firex = 50 + 25 * rand.nextInt(33);
                firey = 100 + 25 * rand.nextInt(23);
            }

            //身体+5 &
            if(snakex[0] == medicinex && snakey[0] == mediciney){
                len+=5;
                score+=5;
                medicinex = 50 + 25 * rand.nextInt(33);
                mediciney = 100 + 25 * rand.nextInt(23);
            }

            //判断蛇头和身体是否重叠
            for(int i = 1; i < len; i++){
                if(snakex[i] == snakex[0] && snakey[i] == snakey[0]){
                    isFailed = true;
                }
            }

            if(len == 0){
                emergency = true;
            }

            //时钟到时调用的方法,刷新屏幕
            mFrame.repaint();

        }
    }

    @Override
    public void paintComponent() {
        try {
            File file1 = new File("title3.jpg");
            title = ImageIO.read(file1);
            File file2 = new File("t4.png");
            body = ImageIO.read(file2);
            File file3 = new File("up.png");
            up = ImageIO.read(file3);
            File file4 = new File("down.png");
            down = ImageIO.read(file4);
            File file5 = new File("left.png");
            left = ImageIO.read(file5);
            File file6 = new File("right.png");
            right = ImageIO.read(file6);
            File file7 = new File("food2.png");
            food = ImageIO.read(file7);
            File file8 = new File("wall.png");
            wall = ImageIO.read(file8);
            File file9 = new File("fire2.png");
            fire = ImageIO.read(file9);
            File file10 = new File("bomb.png");
            bomb = ImageIO.read(file10);
            File file11 = new File("medicine.png");
            medicine = ImageIO.read(file11);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //画标题
        drawImage(title, 21, 11);
        //画黑色
        drawSolidRectangle(21, 75, 850, 600);
        //画分数和长度
        changeColor(Color.white);
        drawText(750, 35, "Length:" + len);
        drawText(750, 50, "Score:" + score);

        //打印墙
        for(int i = 25; i <= 850; i += 25){
            drawImage(wall, i ,75);
        }
        for(int i = 25; i <= 850; i += 25){
            drawImage(wall, i ,650);
        }
        for(int i = 75; i <= 650; i += 25){
            drawImage(wall, 25, i);
        }
        for(int i = 75; i <= 650; i += 25){
            drawImage(wall, 850, i);
        }

        // 设置线段颜色为深灰色
        changeColor(Color.DARK_GRAY);
        //画网格
        for (int i = 0; i < 23; i++) {
            //23条横线      起点坐标                   终点坐标
            drawLine(50, 100 + i * 25, 850, 100 + i * 25 );
        }
        for (int i = 0; i < 31; i++) {
            //23条竖线      起点坐标                   终点坐标
            drawLine(75 + i * 25, 100, 75+ i * 25, 650 );

        }

        //打印蛇
        //蛇头
        if(direction == "R"){
            drawImage(right, snakex[0], snakey[0]);
        }else if(direction == "L"){
            drawImage(left, snakex[0], snakey[0]);
        } else if (direction == "U") {
            drawImage(up, snakex[0], snakey[0]);
        }else{
            drawImage(down, snakex[0], snakey[0]);
        }
        //蛇身
        for(int i = 1; i < len; i++){
            drawImage(body, snakex[i], snakey[i]);
        }

        //随机增加食物
        drawImage(food, foodx, foody);
        //随机炸弹
        drawImage(bomb, bombx, bomby);
        //随机火焰
        drawImage(fire, firex, firey);

        if(emergency){
            drawImage(medicine, medicinex, mediciney);
            emergency = false;
        }

        if(isStarted == false){
            //游戏开始提示
            changeColor(Color.WHITE);
            //setFont(new Font("arial",  Font.BOLD, 40));
            drawBoldText( 250, 300,"Press Space to Start");
        }

        if(isFailed == true){
            //游戏结束提示
            //drawStyledMessage("Failed: Press Space to Restart", 150, 300);
            changeColor(Color.WHITE);
            drawBoldText( 200, 300,"Failed: Press Space to Restart");
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE){
            //如果输了，重新开始
            if(isFailed){
                isFailed = false;
                initSnake();
            }else{
                //没输继续
                isStarted = !isStarted;
                //将开始设置为true
            }
            //从新打印画布，让字消失
            mFrame.repaint();
        }else if(keyCode == KeyEvent.VK_LEFT){
            direction = "L";
        }else if(keyCode == KeyEvent.VK_RIGHT){
            direction = "R";
        }else if(keyCode == KeyEvent.VK_UP){
            direction = "U";
        }else if(keyCode == KeyEvent.VK_DOWN){
            direction = "D";
        }
    }

}
