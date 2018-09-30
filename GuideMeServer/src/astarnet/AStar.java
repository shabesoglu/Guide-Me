

package astarnet;

import Helper.helper;



/**
 *
 * @author sahin
 */
public class AStar {
    
    Graph _Graph;
    SortableList _Open, _Closed;
    Track _LeafToGoBackUp;
    int _NbIterations = -1;
    
    Equality SameNodesReached = new Equality();
    
    
    
    public AStar(Graph G) {
        _Graph = G;
        _Open = new SortableList();
        _Closed = new SortableList();
        
    }
    
    public boolean SearchPath(Node StartNode, Node EndNode) {
        
        Initialize(StartNode, EndNode);
        while ( NextStep() ) {}
        return getPathFound();
        
    }
    
    public Node[][] Open() {
        
        Node[][] NodesList = new Node[_Open.getCount()][];
        for ( int i=0; i<_Open.getCount(); i++ ) NodesList[i] = GoBackUpNodes((Track)_Open.getItems(i));
        return NodesList;
        
    }
    
    public Node[][] Closed() {
        
        Node[][] NodesList = new Node[_Closed.getCount()][];
        for ( int i=0; i<_Closed.getCount(); i++ ) NodesList[i] = GoBackUpNodes((Track)_Closed.getItems(i));
        return NodesList;
        
    }
    
    public void Initialize(Node StartNode, Node EndNode) {
        
        _Closed.Clear();
        _Open.Clear();
        Track.setTarget( EndNode);
        _Open.Add( new Track(StartNode) );
        _NbIterations = 0;
        _LeafToGoBackUp = null;
    }
    
    public boolean NextStep() {
        
        if ( _Open.getCount()==0 ) return false;
        _NbIterations++;
        
        int IndexMin = _Open.IndexOfMin();
        Track BestTrack = (Track)_Open.getItems(IndexMin);
        _Open.RemoveAt(IndexMin);
        if ( BestTrack.getSucceed()) {
            _LeafToGoBackUp = BestTrack;
            _Open.Clear();
        } else {
            Propagate(BestTrack);
            _Closed.Add(BestTrack);
        }
        return _Open.getCount()>0;
    }
    public boolean exitSign=false;
    private void Propagate(Track TrackToPropagate) {
        for ( Object item : TrackToPropagate.EndNode.getOutgoingArcs() ) {
            if(exitSign) return;
            Arc A = (Arc)item;
            if ( A.getPassable() && A.getEndNode().getPassable() ) {
              
                Track Successor = new Track(TrackToPropagate, A);
                int PosNF = _Closed.IndexOf(Successor, SameNodesReached);
                int PosNO = _Open.IndexOf(Successor, SameNodesReached);
                if ( PosNF>0 && Successor.getCost()>=((Track)_Closed.getItems(PosNF)).getCost() ) continue;
                if ( PosNO>0 && Successor.getCost()>=((Track)_Open.getItems(PosNO)).getCost() ) continue;
                if ( PosNF>0 ) _Closed.RemoveAt(PosNF);
                if ( PosNO>0 ) _Open.RemoveAt(PosNO);
                _Open.Add(Successor);
            }
        }
    }
    
    public boolean getInitialized() {  return _NbIterations>=0;  }
    
    public boolean getSearchStarted() {  return _NbIterations>0;  }
    
    public boolean getSearchEnded() {  return getSearchStarted() && _Open.getCount()==0; }
    
    public boolean getPathFound() { return _LeafToGoBackUp!=null; }
    
    public int getStepCounter() {  return _NbIterations; }
    
    
    
    public Node[] PathByNodes() {
        
        if ( !getPathFound() ) return null;
        return GoBackUpNodes(_LeafToGoBackUp);
        
    }
    
    private Node[] GoBackUpNodes(Track T) {
        int Nb = T.getNbArcsVisited();
        Node[] Path = new Node[Nb+1];
        for (int i=Nb; i>=0; i--, T=T.Queue)
            Path[i] = T.EndNode;
        return Path;
    }
    
    public Arc[] PathByArcs() {
        
        if ( !getPathFound() ) return null;
        int Nb = _LeafToGoBackUp.getNbArcsVisited();
        Arc[] Path = new Arc[Nb];
        Track Cur=_LeafToGoBackUp;
        for (int i=Nb-1; i>=0; i--, Cur = Cur.Queue)
            Path[i] = Cur.Queue.EndNode.ArcGoingTo(Cur.EndNode);
        return Path;
        
    }
    
    public Point3D[] PathByCoordinates() {
        
        if ( !getPathFound() ) return null;
        int Nb = _LeafToGoBackUp.getNbArcsVisited();
        Point3D[] Path = new Point3D[Nb+1];
        Track Cur=_LeafToGoBackUp;
        for (int i=Nb; i>=0; i--, Cur = Cur.Queue)
            Path[i] = Cur.EndNode.getPosition();
        return Path;
        
    }
}
