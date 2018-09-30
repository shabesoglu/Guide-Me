

package astarnet;

import java.util.ArrayList;

/**
 *
 * @author sahin
 */
public class Node {
    
    Point3D _Position;
    boolean _Passable;
    ArrayList _IncomingArcs, _OutgoingArcs;
    public  String _Name;
    public  int _roadID;
    
    public Node(int pRoadID, String Name, double PositionX, double PositionY, double PositionZ) {
        _roadID=pRoadID;
         _Name = Name;
        _Position = new Point3D(PositionX, PositionY, PositionZ);
        _Passable = true;
        _IncomingArcs = new ArrayList();
        _OutgoingArcs = new ArrayList();
        
    }
    
    public ArrayList getIncomingArcs() {  return _IncomingArcs;  }
    public ArrayList getOutgoingArcs() {  return _OutgoingArcs;  }
    
    public boolean getPassable(){return _Passable;}
    
    public Point3D getPosition(){return _Position;}
    
    public void setPassable(boolean value) {
        
        
        for(Object item  : _IncomingArcs) {
            Arc A= (Arc) item;
            A.setPassable( value);
        }
        for(Object item : _OutgoingArcs) {
            Arc A= (Arc) item;
            A.setPassable( value);
        }
        _Passable = value;
        
        
    }
    
    public void ChangeXYZ(double PositionX, double PositionY, double PositionZ) {
        _Position = new Point3D(PositionX, PositionY, PositionZ);
    }
    
    public void setPosition(Point3D value) {
        
        for(Object item : _IncomingArcs) {
            Arc A= (Arc) item;
            
            A.setLengthUpdated(false);
        }
        for(Object item : _OutgoingArcs) {
            Arc A= (Arc) item;
            
            A.setLengthUpdated( false);
        }
        _Position = value;
        
    }
    
    public Node[] getAccessibleNodes(){
        
        Node[] Table = new Node[_OutgoingArcs.size()];
        int i=0;
        for(Object item : getOutgoingArcs()) {
            Arc A= (Arc) item;
            Table[i++] = A.getEndNode();
        }
        return Table;
        
    }
    
    public Node[] getAccessingNodes() {
        Node[] Table = new Node[_IncomingArcs.size()];
        int i=0;
        for(Object item : getIncomingArcs()) {
            Arc A= (Arc) item;
            Table[i++] = A.getStartNode();
        }
        return Table;
        
    }
    
    public Node[] getMolecule() {
        
        int NbNodes = 1+_OutgoingArcs.size()+_IncomingArcs.size();
        Node[] Table = new Node[NbNodes];
        Table[0] = this;
        int i=1;
        for(Object item : getOutgoingArcs()) {
            Arc A= (Arc) item;
            Table[i++] = A.getEndNode();
        }
        
        for(Object item : getIncomingArcs()) {
            Arc A= (Arc) item;
            Table[i++] = A.getStartNode();
        }
        return Table;
        
    }
    
    public void Isolate() {
        UntieIncomingArcs();
        UntieOutgoingArcs();
    }
    
    public void UntieIncomingArcs() {
        for(Object item : getIncomingArcs()) {
            Arc A= (Arc) item;
            
            A.getStartNode().getOutgoingArcs().remove(A);
        }
        _IncomingArcs.clear();
    }
    
    
    public void UntieOutgoingArcs() {
        for(Object item : getOutgoingArcs()) {
            Arc A= (Arc) item;
            A.getEndNode().getIncomingArcs().remove(A);
        }
        _OutgoingArcs.clear();
    }
    
    public Arc ArcGoingTo(Node N) {
        
        for(Object item : getOutgoingArcs()) {
            Arc A= (Arc) item;
            
            if (A.getEndNode() == N) return A;
        }
        return null;
    }
    
    public Arc ArcComingFrom(Node N) {
        
        for(Object item : getIncomingArcs()) {
            Arc A= (Arc) item;
            
            
            if (A.getStartNode() == N) return A;}
        return null;
    }
    
    void Invalidate() {
        for(Object item : getIncomingArcs()) {
            Arc A= (Arc) item;
            
            A.setLengthUpdated(false);
        }
        
        for(Object item : getOutgoingArcs()) {
            Arc A= (Arc) item;
            
            A.setLengthUpdated(false) ;
        }
    }
    
    public  String ToString() { return getPosition().ToString(); }
    
    public boolean Equals(Object O) {
        Node N = (Node)O;
        
        return getPosition().Equals(N.getPosition());
    }
    
    public double getX() {  return getPosition().getX(); }
    public double getY() { return getPosition().getY(); }
    public double getZ() { return getPosition().getZ(); }
    
    public Object Clone() {
        Node N = new Node(_roadID, _Name, getX(), getY(), getZ());
        N._Passable = _Passable;
        return N;
    }
    
    public int GetHashCode() { return getPosition().GetHashCode(); }
    
    public static double EuclidianDistance(Node N1, Node N2) {
        return Math.sqrt( SquareEuclidianDistance(N1, N2) );
    }
    
    public static double SquareEuclidianDistance(Node N1, Node N2) {
        
        double DX = N1.getPosition().getX() - N2.getPosition().getX();
        double DY = N1.getPosition().getY() - N2.getPosition().getY();
        double DZ = N1.getPosition().getZ() - N2.getPosition().getZ();
        return DX*DX+DY*DY+DZ*DZ;
    }
    
    public static double ManhattanDistance(Node N1, Node N2) {
        
        double DX = N1.getPosition().getX() - N2.getPosition().getX();
        double DY = N1.getPosition().getY() - N2.getPosition().getY();
        double DZ = N1.getPosition().getZ() - N2.getPosition().getZ();
        return Math.abs(DX)+Math.abs(DY)+Math.abs(DZ);
    }
    
    public static double MaxDistanceAlongAxis(Node N1, Node N2) {
        
        double DX = Math.abs(N1.getPosition().getX() - N2.getPosition().getX());
        double DY = Math.abs(N1.getPosition().getY() - N2.getPosition().getY());
        double DZ = Math.abs(N1.getPosition().getZ() - N2.getPosition().getZ());
        return Math.max(DX, Math.max(DY, DZ));
    }
    
    static public void BoundingBox(ArrayList NodesGroup,  double[] MinPoint, double[] MaxPoint) {
        Node N1 =(Node) NodesGroup.get(0) ;
        
        
        int Dim = 3;
        MinPoint = new double[Dim];
        MaxPoint = new double[Dim];
        for (int i=0; i<Dim; i++) MinPoint[i]=MaxPoint[i]=N1.getPosition().Coordinates[i];
        for ( Object item : NodesGroup ) {
            Node N =(Node) item;
            for ( int i=0; i<Dim; i++ ) {
                if ( MinPoint[i]>N.getPosition().Coordinates[i] ) MinPoint[i]=N.getPosition().Coordinates[i];
                if ( MaxPoint[i]<N.getPosition().Coordinates[i] ) MaxPoint[i]=N.getPosition().Coordinates[i];
            }
        }
    }
    
    
}
