package src;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle {

    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 2;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);  // choose number 0 or 1
        if (randomXDirection == 0)  // if number 0 send right
            randomXDirection--;
        setXDirection(randomXDirection * initialSpeed);
        // don't need to consider about 1 because it is one or the other

        int randomYDirection = random.nextInt(2); // choose number 0 or 1
        if (randomYDirection == 0) // if number 0 send down
            randomYDirection--;
        setYDirection(randomYDirection * initialSpeed);
        // all of the above randomly sends the ball left or right at the start of the game

    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;

    }
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;

    }
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, height, width);
    }
}
