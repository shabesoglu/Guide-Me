
package astarnet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sahin
 */
public class Graph {
    
    ArrayList LN;
    ArrayList LA;
    
    public Graph() {
        
        LN = new ArrayList();
        LA = new ArrayList();
    }
    
    public ArrayList getNodes()  {  return LN;  }
    public ArrayList  getArcs() { return LA;  }
    
    public void Clear() {
        LN.clear();
        LA.clear();
    }
    
    public boolean AddNode(Node NewNode) {
        
        if ( NewNode==null  ) return false;
        LN.add(NewNode);
        return true;
    }
    
    public Node AddNode(int roadID , String name,float x, float y, float z) {
        Node NewNode = new Node(roadID,name,x, y, z);
        return AddNode(NewNode) ? NewNode : null;
    }
    
    public boolean AddArc(Arc NewArc) {
        if ( NewArc==null  ) return false;
        LA.add(NewArc);
        return true;
    }
    
    public Arc AddArc(Node StartNode, Node EndNode, float Weight) {
        Arc NewArc = new Arc(StartNode, EndNode);
        NewArc.setWeight(Weight);
        return AddArc(NewArc) ? NewArc : null;
    }
    
    public boolean RemoveNode(Node NodeToRemove) {
        if ( NodeToRemove==null ) return false;
        try {
            for( Object item : NodeToRemove.getIncomingArcs()) {
                Arc A= (Arc) item;
                A.getStartNode().getOutgoingArcs().remove(A);
                LA.remove(A);
            }
            for( Object item : NodeToRemove.getOutgoingArcs()) {
                Arc A = (Arc) item;
                A.getEndNode().getIncomingArcs().remove(A);
                LA.remove(A);
            }
            LN.remove(NodeToRemove);
        } catch(Exception E) { return false; }
        return true;
    }
    
    public boolean RemoveArc(Arc ArcToRemove) {
        if ( ArcToRemove==null ) return false;
        try {
            LA.remove(ArcToRemove);
            ArcToRemove.getStartNode().getOutgoingArcs().remove(ArcToRemove);
            ArcToRemove.getEndNode().getIncomingArcs().remove(ArcToRemove);
        } catch(Exception E) { return false; }
        return true;
    }
    
    public void BoundingBox( double[] MinPoint,  double[] MaxPoint) {
        try {
            Node.BoundingBox(getNodes(),  MinPoint,  MaxPoint);
        } catch(Exception e) {         }
    }
    
    public Node ClosestNode(double PtX, double PtY, double PtZ) {
        Node NodeMin = null;
        double DistanceMin = -1;
        Point3D P = new Point3D(PtX, PtY, PtZ);
        for( Object  item : LN ) {
            Node N = (Node) item;
            if (  N.getPassable()==false ) continue;
            double DistanceTemp = Point3D.DistanceBetween(N.getPosition(), P);
            if ( DistanceMin==-1 || DistanceMin>DistanceTemp ) {
                DistanceMin = DistanceTemp;
                NodeMin = N;
            }
        }
        
        return NodeMin;
    }
    
    
    public Node getNodeByCoordinate(double PtX, double PtY) {
        
        for (Object  item: LN) {
            Node N = (Node) item;
            if (N.getPosition().getX() == PtX && N.getPosition().getY() == PtY) {
                
                return N;
            }
        }
        return null;
        
    }
    
    
    
    
}
