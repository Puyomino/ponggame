package src;

import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle {

    int id;  // id to identify if player 1 or player 2
    int yVelocity;  // how fast paddle will move up and down
    int speed = 10;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
       super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
       this.id=id;

    }
    public void keyPressed(KeyEvent e) {
        switch(id) { // Moving player 1's paddle
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) { // While W is pressed, move P1's paddle up
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S) { // While S is pressed, move P1's paddle down
                    setYDirection(speed);
                    move();
                }
                break;
            case 2: // Moving player 2's paddle
                if(e.getKeyCode()==KeyEvent.VK_UP) { // While Up arrow is pressed, move P2's paddle up
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) { // While Down arrow is pressed, move P2's paddle down
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        switch(id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move();
                }
                break;
        }
    }
    public void setYDirection(int yDirection) {
        // paddles will only move up and down, thus no x direction
        yVelocity = yDirection;
    }
    public void move() {
        y = y + yVelocity;
    }
    public void draw(Graphics g) {
        // Sets the colours for the paddles and background
        if(id==1)
            g.setColor(Color.blue);
        else
            g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}
