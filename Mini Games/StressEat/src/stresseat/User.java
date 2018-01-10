import java.awt.Rectangle; 

public class User {
    private double X, Y;
    private int height, width;
    Rectangle boundingBox;
    
    User(int height, int width){
        X = 300;
        Y = 400 - height;
        this.height = height;
        this.width = width;
        boundingBox.x = (int)X;
        boundingBox.y = (int)Y;
        boundingBox.height = height;
        boundingBox.width = width;
    }
    
    User(int height, int width, double X){
        this.X = X;
        Y = 400 - height;
        this.height = height;
        this.width = width;
        boundingBox.x = (int)X;
        boundingBox.y = (int)Y;
        boundingBox.height = height;
        boundingBox.width = width;
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
    
    public void update(double elapsedTime){
        X += elapsedTime * 100;
        boundingBox.x = (int)X;
    }
}
