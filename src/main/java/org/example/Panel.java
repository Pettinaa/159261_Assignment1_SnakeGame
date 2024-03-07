package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.ArrayList;



public class Panel extends JPanel implements KeyListener, ActionListener {
    ImageIcon title = new ImageIcon("title.jpg");

    ImageIcon body = new ImageIcon("body.png");
    ImageIcon up = new ImageIcon("up.png");
    ImageIcon down = new ImageIcon("down.png");
    ImageIcon left = new ImageIcon("left.png");
    ImageIcon right = new ImageIcon("right.png");
    ImageIcon food = new ImageIcon("food.png");

    ImageIcon firewall = new ImageIcon("firewall.jpg");
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
    Timer timer = new Timer(100, this);
    int foodx;
    int foody;
    Random rand = new Random();
    public Panel(){
        //初始化蛇
        initSnake();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();



    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        title.paintIcon(this, g, 21, 11);
        //画黑色
        g.fillRect(21, 75, 850, 600);
        //画分数和长度
        g.setColor(Color.white);
        g.drawString("Length:" + len, 750, 35);
        g.drawString("Score:" + score, 750, 50);

        //打印墙
        for(int i = 25; i <= 850; i += 25){
            firewall.paintIcon(this, g, i, 75);
        }
        for(int i = 25; i <= 850; i += 25){
            firewall.paintIcon(this, g, i, 650);
        }
        for(int i = 75; i <= 650; i += 25){
            firewall.paintIcon(this, g, 25, i);
        }
        for(int i = 75; i <= 650; i += 25){
            firewall.paintIcon(this, g, 850, i);
        }



        //打印蛇
        //蛇头
        if(direction == "R"){
            right.paintIcon(this, g, snakex[0], snakey[0]);
        }else if(direction == "L"){
            left.paintIcon(this, g, snakex[0], snakey[0]);
        } else if (direction == "U") {
            up.paintIcon(this, g, snakex[0], snakey[0]);
        }else{
            down.paintIcon(this, g, snakex[0], snakey[0]);
        }
        //蛇身
        for(int i = 1; i < len; i++){
           body.paintIcon(this, g, snakex[i], snakey[i]);
        }

        //随机增加食物
        food.paintIcon(this, g, foodx, foody);

        if(isStarted == false){
            //游戏开始提示
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial",  Font.BOLD, 40));
            g.drawString("Press Space to Start", 250, 300);
        }

        if(isFailed == true){
            //游戏结束提示
            g.setColor(Color.RED);
            g.setFont(new Font("arial",  Font.BOLD, 40));
            g.drawString("Failed: Press Space to Restart", 150, 300);
        }

        //绘制墙
//        g.setColor(Color.PINK);
//        for(Point wall : walls){
//            g.fillRect(wall.x * TILE_SIZE, wall.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
//        }

    }

    public  void initSnake(){
        len = 3;
        snakex[0] = 100;
        snakey[0] = 125;
        snakex[1] = 75;
        snakey[1] = 125;
        snakex[2] = 50;
        snakey[2] = 125;
        foodx = 50 + 25 * rand.nextInt(32);
        foody = 100 + 25 * rand.nextInt(22);
        direction = "R";
        score = 0;
    }

//    private void initializeWalls(){
//        for(int i = 50; i < 875; i++){
//            walls.add(new Point(i, 0));
//            walls.add(new Point(i, 870 - 1));
//        }
//
//        for(int i = 50; i < 675; i++){
//            walls.add(new Point(0, i));
//            walls.add(new Point(675 - 1, i));
//        }
//    }


    @Override
    public void keyTyped(KeyEvent e) {

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
            repaint();
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

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

            //判断蛇头和身体是否重叠
            for(int i = 1; i < len; i++){
                if(snakex[i] == snakex[0] && snakey[i] == snakey[0]){
                    isFailed = true;
                }
            }

            //时钟到时调用的方法,刷新屏幕
            repaint();

        }
        timer.start();
    }
}
