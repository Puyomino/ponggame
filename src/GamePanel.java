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
        ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
        // above we are passing the x coordinates and y coordinates for the centre of the window
    }
    public  void newPaddles() {
        // Sets the positioning of both paddles
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
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
        score.draw(g);  // draws the score
        // draws the paddles
        paddle1.draw(g);
        paddle2.draw(g);
    }
    public void move() {
        ball.move();  // helps in making the ball move smoother
        paddle1.move(); // makes the paddle movement smoother
        paddle2.move();
    }
    public void checkCollision() {
        // ball collision
        if(ball.y <= 0){  // if ball y coordinates is 0 (top)
            ball.setYDirection(-ball.yVelocity);  // go other direction
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {  // if ball hits bottom
            ball.setYDirection(-ball.yVelocity);  // go other direction
        }

        if(ball.intersects(paddle1)) { // checks if the ball has hit the paddle
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;  // increases speed when hit

            if(ball.yVelocity > 0)
                ball.yVelocity++; // increase speed when hit
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity); // Bounces the ball off the paddle
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)) { // checks if the ball has hit the paddle
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;  // increases speed when hit

            if(ball.yVelocity > 0)
                ball.yVelocity++; // increase speed when hit
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity); // Bounces the ball off the paddle
            ball.setYDirection(ball.yVelocity);
        }

        // stops the paddles at the window edges
        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y>=(GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y=GAME_HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y>=(GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y=GAME_HEIGHT-PADDLE_HEIGHT;

        // ball score
        if(ball.x <= 0) {  // if ball reaches left side
            score.player2++;  // add 1 to player 2's score
            newPaddles();  // reset paddle location
            newBall();  // reset ball location
        }
        if(ball.x >= GAME_WIDTH - BALL_DIAMETER) {  // if ball reaches right side
            score.player1++;  // add 1 to player 1's score
            newPaddles();  // reset paddle location
            newBall();  // reset ball location
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
    public class AL extends KeyAdapter{  // AL stands for Action Listener
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
