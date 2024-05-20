package src;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {
// makes the window for the game

    GamePanel panel;
    // when panel is called, make new instance of GamePanel
    GameFrame(){
        panel = new GamePanel();
        this.add(panel);  // makes window
        this.setTitle("Pong Game");  // titles window Pong Game
        this.setResizable(false);  // can't be resized
        this.setBackground(Color.black);  // inside the window is colour black
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // pressing the X in the top right closes game
        this.pack();  // sets up GamePanel so everything is relative to the window
        this.setVisible(true);  // ensures that window is here
        this.setLocationRelativeTo(null);  // moves the window to the middle of the computer screen

    }
}
