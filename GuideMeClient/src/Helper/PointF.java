package Helper;

public class PointF {
    private double X;
    private double Y;
    
    public PointF(double x, double y) {
        X = x;
        Y = y;
    }
    
    public double getX() {
        return X;
    }
    public int getiX() {
        return (int)  X;
    }
    
    public float getfX() {
        return (float) X;
    }
    public float getfY() {
        return (float) Y;
    }
    
    public void set(double x,double y){
        setX(x);
        setY(y);
    }
    public int getiY() {
        return (int) Y;
    }
    
    public void setX(double x) {
        X = x;
    }
    
    public double getY() {
        return Y;
    }
    
    public void setY(double y) {
        Y = y;
    }
     public String ToString(){
        return String.valueOf(getX()+ ","+getY() );
        
    }
    
    public String toString(){
        return String.valueOf(getX()+ " "+getY());
        
    }
}

