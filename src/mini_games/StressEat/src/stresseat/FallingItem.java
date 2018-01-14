package stresseat;

import java.awt.Rectangle;

public class FallingItem{
    private double X, Y, speed;
    private int height, width;
    Rectangle boundingBox;
    
    FallingItem(){
        X = 300;
        Y = -16;
        this.height = 16;
        this.width = 16;
        speed = 1;
        boundingBox = new Rectangle((int)X, (int)Y, width, height);
    }
    
    FallingItem(double X){
        this.X = X;
        Y = -16;
        this.height = 16;
        this.width = 16;
        speed = 1;
        boundingBox = new Rectangle((int)X, (int)Y, width, height);
    }
    
    FallingItem(double X, double speed){
        this.X = X;
        Y = -16;
        this.height = 16;
        this.width = 16;
        this.speed = speed;
        boundingBox = new Rectangle((int)X, (int)Y, width, height);
    }
    
    public void changeSpeed(double speed){
        this.speed += speed;
    }
    
    public double getX(){
        return X;
    }
    
    public double getY(){
        return Y;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }
    
    public double getSpeed(){
        return speed;
    }
    
    public void update(double elapsedTime){
        Y += speed * elapsedTime * 100;
        boundingBox.y = (int)Y;
    }
}
