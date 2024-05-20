package src;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
// contains the game and draws it up

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    // static to share same GAME_WIDTH between multiple instances
    // final to makes it unchangeable
    // GAME_HEIGHT uses that formula as it is the ratio of an actual pong table
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETRE = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    GamePanel() {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);  // passing width and height so align score placement
        this.setFocusable((true));  // focuses window
        this.addKeyListener(new AL());  // allows computer to read keyboard inputs
        this.setPreferredSize(SCREEN_SIZE);  // game resolution

        gameThread = new Thread(this);
        gameThread.start();

    }
    public void newBall() {

    }
    public  void newPaddles() {

    }
    public void paint(Graphics g) {

    }
    public void draw(Graphics g) {

    }
    public void move() {

    }
    public void checkCollision() {

    }
    public void run() {

    }
    public class AL extends  KeyAdapter{  // AL stands for Action Listener
        public void keyPressed(KeyEvent e) {

        }
        public void keyReleased(KeyEvent e) {

        }
    }
}
