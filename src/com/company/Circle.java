package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Circle implements Runnable{

    private double x;
    private double y;
    private int radius;

    private double XEatPosition;
    private double YEatPosition;

    private ArrayList<Eat> eats;

    private double speed = 5;
    private double speedX;
    private double speedY;
    Eat eat;

    public Circle(double x, double y, int radius, ArrayList<Eat> eats){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.eats = new ArrayList<Eat>(eats);
        this.eat = eat;
        speedX = speed;
        speedY = speed;
        Thread thisThread = new Thread(this);
        thisThread.start();
    }

    public void paint(Graphics2D canvas) {
        canvas.setColor(Color.ORANGE);
        canvas.setPaint(Color.ORANGE);
        Ellipse2D.Double ball = new Ellipse2D.Double(x-radius, y-radius, 2*radius, 2*radius);
        canvas.draw(ball);
        canvas.fill(ball);
    }

    @Override
    public void run() {
        move();
    }

    private void move(){
        while (true) {
            double xLong = Math.abs(x - eat.getxPosition());
            double yLong = Math.abs(y - eat.getyPosition());

            speedX = speed;
            speedY = speed;
            if(xLong > yLong){
                float time = (float)(xLong/speedX);
                speedY = yLong / time;
            }else {
                float time = (float)(yLong/speedY);
                speedX = xLong / time;
            }

            if (x <= eat.getxPosition() + eat.getRadius() && x >= eat.getxPosition() - eat.getRadius() &&
                    y <= eat.getyPosition() + eat.getRadius() && y >= eat.getyPosition() - eat.getRadius()){
                speedY = speed;
                speedX = speed;
                eat.relocation();
            }

            try {
                if (x <= eat.getxPosition() && y >= eat.getyPosition()) {
                    x += speedX;
                    y -= speedY;
                } else if (x >= eat.getxPosition() && y >= eat.getyPosition()) {
                    x -= speedX;
                    y -= speedY;
                } else if (x >= eat.getxPosition() && y <= eat.getyPosition()) {
                    x -= speedX;
                    y += speedY;
                } else if (x <= eat.getxPosition() && y <= eat.getyPosition()) {
                    x += speedX;
                    y += speedY;
                } else if (x <= eat.getxPosition()) {
                    x += speedX;
                } else if (x >= eat.getxPosition()) {
                    x -= speedX;
                } else if (y <= eat.getyPosition()) {
                    y += speedY;
                } else if (y >= eat.getyPosition()) {
                    y -= speedY;
                }
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
