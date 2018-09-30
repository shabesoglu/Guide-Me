
package astarnet;

/**
 *
 * @author sahin
 */
public class Point3D {
    
    private  double X,Y,Z;
    
    void setX(double value){ X=value;Coordinates[0] = value;}
    void setY(double value){ Y=value;Coordinates[1] = value;}
    void setZ(double value){ Z=value;Coordinates[2] = value;}
    
    double getX(){ return Coordinates[0];}
    double getY(){return Coordinates[1];}
    double getZ(){return Coordinates[2];}
    
    
    double[] Coordinates = new double[3];
    
    public Point3D(double[] Coordinates) {
        setX( Coordinates[0]); setY(Coordinates[1]); setZ(Coordinates[2]);
    }
    
    public Point3D(double CoordinateX, double CoordinateY, double CoordinateZ) {
        setX( CoordinateX);   setY(CoordinateY);   setZ( CoordinateZ);
    }
    
    public static double DistanceBetween(Point3D P1, Point3D P2) {
        return Math.sqrt((P1.X-P2.X)*(P1.X-P2.X)+(P1.Y-P2.Y)*(P1.Y-P2.Y));
        
        
    }
    
    
    
    public  boolean  Equals(Object Point) {
        Point3D P = (Point3D)Point;
        
        boolean  Resultat = true;
        for (int i=0; i<3; i++) Resultat &= (P.Coordinates[i]==this.Coordinates[i]);
        return Resultat;
    }
    
    public  int GetHashCode() {
        double HashCode = 0;
        for (int i=0; i<3; i++) HashCode += this.Coordinates[i];
        return (int)HashCode;
    }
    
    
    public  String ToString() {
        String Deb = "";
        String Sep = ",";
        String Fin = "";
        String Resultat = Deb;
        int Dimension = 3;
        for (int i=0; i<Dimension; i++)
            Resultat += String.valueOf(Coordinates[i])+ (i!=Dimension-1 ? Sep : Fin);
        return Resultat;
    }
    
}
