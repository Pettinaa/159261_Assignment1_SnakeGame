package org.example;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FoodieMode extends GameEngine{
    Image title;
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
    int[] snakex = new int[750];
    int[] snakey = new int[750];
    //墙
    private ArrayList<Point> walls = new ArrayList<>();
    // 定义格子大小常量
    String direction = "R";//头的方向
    boolean isStarted = false;
    boolean isFailed = false;
    int food1x;
    int food1y;
    int food2x;
    int food2y;
    int food3x;
    int food3y;
    int hamburgerx;
    int hamburgery;
    Random rand = new Random();
    public FoodieMode(){
        //初始化蛇
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
        food1x = 100 + 25 * rand.nextInt(28);
        food1y = 150 + 25 * rand.nextInt(18);
        food2x = 100 + 25 * rand.nextInt(28);
        food2y = 150 + 25 * rand.nextInt(18);
        food3x = 100 + 25 * rand.nextInt(28);
        food3y = 150 + 25 * rand.nextInt(18);
        hamburgerx = 100 + 25 * rand.nextInt(28);
        hamburgery = 150 + 25 * rand.nextInt(18);
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
    private void drawStyledMessage(Graphics g, String message, int x, int y) {
        // 设置背景板
        g.setColor(new Color(255, 0, 0, 50)); // 半透明红色背景
        g.fillRect(x - 20, y - 40, 630, 60);  // 背景板大小和位置

        // 设置文本样式
        g.setColor(Color.WHITE); // 白色文本
        g.setFont(new Font("Arial", Font.BOLD, 40)); // 加粗的Arial字体

        // 绘制文本
        g.drawString(message, x, y);

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
                //让蛇穿透墙壁
                if(snakex[0] > 850){
                    snakex[0] = 25;
                }
            }else if(direction == "L"){
                snakex[0] = snakex[0] - 25;
                //让蛇穿透墙壁
                if(snakex[0] < 25){
                    snakex[0] = 850;
                }
            }else if(direction == "U"){
                snakey[0] = snakey[0] - 25;
                if(snakey[0] < 75){
                    snakey[0] = 650;
                }
            }else if(direction == "D"){
                snakey[0] = snakey[0] + 25;
                if(snakey[0] > 650){
                    snakey[0] = 75;
                }
            }
            //身体+1 & 重新生成苹果
            if(snakex[0] == food1x && snakey[0] == food1y){
                len+= 1;
                score+= 1;
                food1x = 50 + 25 * rand.nextInt(33);
                food1y = 100 + 25 * rand.nextInt(23);
            }
            if(snakex[0] == food2x && snakey[0] == food2y){
                len+= 1;
                score+= 1;
                food2x = 50 + 25 * rand.nextInt(33);
                food2y = 100 + 25 * rand.nextInt(23);
            }
            if(snakex[0] == food3x && snakey[0] == food3y){
                len+= 1;
                score+= 1;
                food3x = 50 + 25 * rand.nextInt(33);
                food3y = 100 + 25 * rand.nextInt(23);
            }


            //身体+3 & 重新生成大汉堡
            if(snakex[0] == hamburgerx && snakey[0] == hamburgery){
                len+= 3;
                score+= 3;
                hamburgerx = 50 + 25 * rand.nextInt(33);
                hamburgery = 100 + 25 * rand.nextInt(23);
            }

            //判断蛇头和身体是否重叠
            for(int i = 1; i < len; i++){
                if(snakex[i] == snakex[0] && snakey[i] == snakey[0]){
                    isFailed = true;
                }
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
            File file7 = new File("food1.png");
            food1 = ImageIO.read(file7);
            File file8 = new File("food2.png");
            food2 = ImageIO.read(file8);
            File file9 = new File("food3.png");
            food3 = ImageIO.read(file9);
            File file10 = new File("hamburger.png");
            hamburger = ImageIO.read(file10);

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

        // 设置线段颜色为深灰色
        changeColor(Color.DARK_GRAY);
        //画网格
        for (int i = 0; i < 24; i++) {
            //23条横线      起点坐标                   终点坐标
            drawLine(21, 75 + i * 25, 871, 75 + i * 25 );
        }
        for (int i = 0; i < 33; i++) {
            //23条竖线      起点坐标                   终点坐标
            drawLine(50 + i * 25, 75, 50 + i * 25, 675 );

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
        drawImage(food1, food1x, food1y);
        drawImage(food2, food2x, food2y);
        drawImage(food3, food3x, food3y);
        //随机生成大汉堡
        drawImage(hamburger, hamburgerx, hamburgery);

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
