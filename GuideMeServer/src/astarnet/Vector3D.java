

package astarnet;

/**
 *
 * @author sahin
 */
public class Vector3D {
    
    double[] Coordinates = new double[3];
    double DX,DY,DZ;
    
    public Vector3D(double[] Coordinates) {
        DX = Coordinates[0];
        DY = Coordinates[1];
        DZ = Coordinates[2];
    }
    
    
    
    public Vector3D(Point3D P1, Point3D P2) {
        DX = P2.getX()-P1.getX();
        DY = P2.getY()-P1.getY();
        DZ = P2.getZ()-P1.getZ();
    }
    
    
    
    
}

