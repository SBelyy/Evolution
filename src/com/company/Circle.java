package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Circle implements Runnable{

    private double x;
    private double y;
    private int radius;

    private double xEatPosition;
    private double yEatPosition;
    private int eatIndex;

    private ArrayList<Eat> eats;

    private double speed = 5;
    private double speedX;
    private double speedY;

    public Circle(double x, double y, int radius, ArrayList<Eat> eats){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.eats = new ArrayList<Eat>(eats);
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
        while (true){
            finding();
            speedDetermination();
            move();
            relocation();
        }
    }

    private void move(){
            try {
                if (x <= xEatPosition && y >= yEatPosition) {
                    x += speedX;
                    y -= speedY;
                } else if (x >= xEatPosition && y >= yEatPosition) {
                    x -= speedX;
                    y -= speedY;
                } else if (x >= xEatPosition && y <= yEatPosition) {
                    x -= speedX;
                    y += speedY;
                } else if (x <= xEatPosition && y <= yEatPosition) {
                    x += speedX;
                    y += speedY;
                } else if (x <= xEatPosition) {
                    x += speedX;
                } else if (x >= xEatPosition) {
                    x -= speedX;
                } else if (y <= yEatPosition) {
                    y += speedY;
                } else if (y >= yEatPosition) {
                    y -= speedY;
                }
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    private synchronized void relocation(){
        double eatRadius = eats.get(eatIndex).getRadius();

        if (x <= xEatPosition + eatRadius && x >= xEatPosition - eatRadius &&
                y <= yEatPosition + eatRadius && y >= yEatPosition - eatRadius){
            speedY = speed;
            speedX = speed;
            eats.get(eatIndex).relocation();
        }

    }

    private void finding(){
        xEatPosition = eats.get(0).getxPosition();
        yEatPosition = eats.get(0).getyPosition();

        eatIndex = 0;

        double hypotenuse = calculate(xEatPosition, yEatPosition);

        for(int i = 0; i < eats.size(); i++){
            if(hypotenuse > calculate(eats.get(i).getxPosition(), eats.get(i).getyPosition())){
                hypotenuse = calculate(eats.get(i).getxPosition(), eats.get(i).getyPosition());
                xEatPosition = eats.get(i).getxPosition();
                yEatPosition = eats.get(i).getyPosition();
                eatIndex = i;
            }
        }
    }

    private void speedDetermination(){
        double xLong = Math.abs(x - xEatPosition);
        double yLong = Math.abs(y - yEatPosition);

        speedX = speed;
        speedY = speed;
        if(xLong > yLong){
            float time = (float)(xLong/speedX);
            speedY = yLong / time;
        }else {
            float time = (float)(yLong/speedY);
            speedX = xLong / time;
        }

    }

    private double calculate(double a, double b){
        return Math.sqrt((x - a)*(x - a) + (y - b)*(y - b));
    }

}
