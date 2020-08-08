package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Eat{

    private double xPosition;
    private double yPosition;
    private int radius;
    private Dimension sSize;

    public Eat(){
        sSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = sSize.height;
        int width = sSize.width;
        radius = (int)(Math.random() * 25) + 10;
        xPosition = (int)(Math.random() * width) - radius;
        yPosition = (int)(Math.random() * height) - radius;
//        Thread thread = new Thread(this);
//        thread.start();
    }

    public void paint(Graphics2D canvas) {
        canvas.setColor(Color.GREEN);
        canvas.setPaint(Color.GREEN);
        Ellipse2D.Double ball = new Ellipse2D.Double(xPosition-radius, yPosition-radius, 2*radius, 2*radius);
        canvas.draw(ball);
        canvas.fill(ball);
    }

//    @Override
//    public void run() {
//
//    }

    public synchronized void relocation(){
        xPosition = (int)(Math.random() * 960) + 10;
        yPosition = (int)(Math.random() * 760) + 10;
        radius = (int)(Math.random() * 25) + 10;
    }

    public synchronized double getxPosition() {
        return xPosition;
    }

    public synchronized double getyPosition() {
        return yPosition;
    }

    public synchronized int getRadius() {
        return radius;
    }
}
