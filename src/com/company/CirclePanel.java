package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CirclePanel extends JPanel {

    Circle circle;
    Circle circle2;
    ArrayList<Eat> eats = new ArrayList<Eat>();

    private Timer repaintTimer = new Timer(7, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            repaint();
        }
    });

    public CirclePanel(){
        setBackground(Color.WHITE);
        initEat();
        circle = new Circle(250, 250, 50, eats);
        circle2 = new Circle(350, 350, 50, eats);
        repaintTimer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        for(Eat eat : eats){
            eat.paint(canvas);
        }
        circle.paint(canvas);
        circle2.paint(canvas);
    }

    private void initEat(){
        for(int i = 0; i < 10; i++){
            Eat eat = new Eat();
            eats.add(i, eat);
        }
    }

    private void initCircle(){

    }

}
