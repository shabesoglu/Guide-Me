package GUI;



import Helper.PointF;
import Helper.helper;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import org.omg.CORBA.Bounds;

enum enumNodeNames{
    nothing,
    polyLine,
    polygon,
    poi;

    
}

public class SlowNode{
    
    
    public enumNodeNames pName  ;
    public String pLabel;
    public int pRoadId;
    public int  pType;
    public polyCollection pData ;
    public int zoomValue;
    public BoundsF pbounds;
    
    SlowNode(enumNodeNames pnodeName, String label , polyCollection data , int type,BoundsF bounds,int RoadId){
        this.pLabel = label;
        this.pData = data;
        this.pType = type;
        this.pName = pnodeName;
        this.pbounds=bounds;
        this.pRoadId=RoadId;
    }
    
    
    
    public ArrayList ppoint() {
        
        
        ArrayList dummyArrayList = new ArrayList();
        
        for(Object item: pData.toArray()) {
            dummyArrayList.add(new PointF(Double.parseDouble(item.toString().split(",")[0]), Double.parseDouble(item.toString().split(",")[1])));
        }
        return dummyArrayList;
        
        
    }
    
    
    public String toString() {
       if (pLabel.equals("")) return "Noname";
        return pLabel;
        
    }

    Polygon ppoint(double zoomLevel,PointF pcurrentFileCoordinate) {
     Polygon dummyArrayList = new Polygon();
        
      
      
             for(Object item: pData.toArray()) {
            
        
               PointF dpoint=helper.coordinateToPoint(new PointF(Double.parseDouble(item.toString().split(",")[0]), Double.parseDouble(item.toString().split(",")[1])),zoomLevel,pcurrentFileCoordinate);
                dummyArrayList.addPoint(dpoint.getiX(),dpoint.getiY() );
             } 
            
         
            
    
        
        return dummyArrayList;
    }
    
    
}


class polyCollection
        extends   ArrayList{
    private int _zoomLevel;
    
    int getZoomLevel(){
        
        return _zoomLevel;
        
    }
    void setZoomLevel(int value){
        
        _zoomLevel=value;
    }
}




