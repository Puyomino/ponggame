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
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
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
        random = new Random();
        ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), (GAME_HEIGHT/2) - (BALL_DIAMETER/2), BALL_DIAMETER, BALL_DIAMETER);
        // above we are passing the x coordinates and y coordinates for the centre of the window
    }
    public  void newPaddles() {

    }
    public void paint(Graphics g) {
        // this method "paints" the graphics on the screen
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
        // 0,0 is the top left
    }
    public void draw(Graphics g) {
        ball.draw(g);  // draws the ball
    }
    public void move() {
        ball.move();  // helps in making the ball move smoother
    }
    public void checkCollision() {
        // ball collision
        if(ball.y <= 0){  // if ball y coordinates is 0 (top)
            ball.setYDirection(-ball.yVelocity);  // go other direction
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {  // if ball hits bottom
            ball.setYDirection(-ball.yVelocity);  // go other direction
        }

        if(ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;  // increases speed when hit

            if(ball.yVelocity > 0)
                ball.yVelocity++; // increase speed when hit
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

    }
    public void run() {
        // game loop
        // adapted from the Minecraft source code
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;  // sets tick rate, currently 60 tps/fps
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class AL extends  KeyAdapter{  // AL stands for Action Listener
        public void keyPressed(KeyEvent e) {

        }
        public void keyReleased(KeyEvent e) {

        }
    }
}
