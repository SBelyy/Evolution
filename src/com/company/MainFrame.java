package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;

    public MainFrame(){
        JFrame frame = new JFrame("Шар");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        JButton button = new JButton("Start");
        frame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CirclePanel panel = new CirclePanel();
                frame.add(panel);
                frame.setVisible(true);
            }
        });
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

}
