

package astarnet;


public class Arc {
    Node _StartNode, _EndNode;
    double _Weight;
    boolean _Passable;
    double _Length;
    boolean _LengthUpdated;
    
    
    public Arc(Node Start, Node End) {
        setStartNode( Start);
        setEndNode( End);
        setWeight( 1);
        setLengthUpdated( false);
        setPassable( true);
    }
    
    public void setStartNode(Node value){
        
        
        if ( _StartNode!=null ) _StartNode.getOutgoingArcs().remove(this);
        _StartNode = value;
        _StartNode.getOutgoingArcs().add(this);
        
    }
    
    public Node getStartNode(){ return _StartNode;}
    
    public void setEndNode(Node value){
        
        
        if ( _EndNode!=null ) _EndNode.getIncomingArcs().remove(this);
        _EndNode = value;
        _EndNode.getIncomingArcs().add(this);
        
    }
    
    public Node getEndNode(){ return _EndNode;}
    
    public void setWeight(double i) {
        _Weight=i;
    }
    
    public double getWeight(){ return _Weight;}
    
    public void setLengthUpdated(boolean b) {
        _LengthUpdated=b;
    }
    
    public boolean  getLengthUpdated() { return _LengthUpdated; }
    
    public void setPassable(boolean b) {
        _Passable=b;
    }
    
    public boolean getPassable() { return  _Passable;   }
    
    public double getLength(){
        
        if ( getLengthUpdated()==false ) {
            _Length = CalculateLength();
            setLengthUpdated(true);
        }
        return _Length;
    }
    
    public double CalculateLength() {
        return Point3D.DistanceBetween(_StartNode.getPosition(), _EndNode.getPosition());
    }
    
    public double getCost(){ return getWeight()*getLength(); }
    
    public String ToString() {
        return _StartNode.ToString()+"-->"+_EndNode.ToString();
    }
    
    public boolean  Equals(Object O) {
        Arc A = (Arc) O;
        return _StartNode.Equals(A._StartNode) && _EndNode.Equals(A._EndNode);
    }
    
    public int GetHashCode() { return (int)getLength(); }
    
    
}
