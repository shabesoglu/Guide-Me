

package astarnet;


/**
 *
 * @author sahin
 */
public class Track {
    
    private static  Node _Target = null;
    private static double _Coeff = 0.01;
    public Node EndNode;
    public Track Queue;
    private double _Cost;
    private int _NbArcsVisited;
    
    
    
    public static void setTarget(Node value) { _Target = value; }
     
    public int getNbArcsVisited() { return _NbArcsVisited; }
    
    
    public double getCost() {  return _Cost; }
    
    public double Evaluation() {
        return _Coeff * _Cost + (1 - _Coeff) * EuclidianDistance(EndNode, _Target);
    }
    
    public static double EuclidianDistance(Node N1, Node N2) {
        return Math.sqrt( SquareEuclidianDistance(N1, N2) );
    }
    
    public static double SquareEuclidianDistance(Node N1, Node N2) {
        
        double DX = N1.getPosition().getX() - N2.getPosition().getX();
        double DY = N1.getPosition().getY() - N2.getPosition().getY();
        double DZ = N1.getPosition().getZ() - N2.getPosition().getZ();
        return DX*DX+DY*DY+DZ*DZ;
    }
    
    public boolean  getSucceed() { return EndNode == _Target; }
    
    
    public Track(Node GraphNode) {
        
        _Cost = 0;
        _NbArcsVisited = 0;
        Queue = null;
        EndNode = GraphNode;
    }
    
    
    
    public Track(Track PreviousTrack, Arc Transition) {
        
        Queue = PreviousTrack;
        _Cost = Queue.getCost() + Transition.getCost();
        _NbArcsVisited = Queue._NbArcsVisited + 1;
        EndNode = Transition.getEndNode();
    }
    
    
}
