import java.awt.Rectangle; 

public class FallingItem{
    private double X, Y, speed;
    private int height, width;
    Rectangle boundingBox;
    
    FallingItem(int height, int width){
        X = 300;
        Y = 0 - height;
        this.height = height;
        this.width = width;
        speed = 1;
        boundingBox.x = (int)X;
        boundingBox.y = (int)Y;
        boundingBox.height = height;
        boundingBox.width = width;
    }
    
    FallingItem(int height, int width, double X){
        this.X = X;
        Y = 0 - height;
        this.height = height;
        this.width = width;
        speed = 1;
        boundingBox.x = (int)X;
        boundingBox.y = (int)Y;
        boundingBox.height = height;
        boundingBox.width = width;
    }
    
    FallingItem(int height, int width, double X, double speed){
        this.X = X;
        Y = 0 - height;
        this.height = height;
        this.width = width;
        this.speed = speed;
        boundingBox.x = (int)X;
        boundingBox.y = (int)Y;
        boundingBox.height = height;
        boundingBox.width = width;
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
