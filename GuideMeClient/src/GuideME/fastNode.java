package GuideME;
import Client.Client;
import Helper.BoundsF;
import Helper.Cpoint;
import Helper.PointF;
import Helper.helper;

import java.awt.Polygon;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class fastNode{
    
    public fastNode(){
        
    }
    //Polygon calculatedArrayList = new Polygon();
    
    public int Name  ;
    public String Label;
    public int Type;
    public long fileOffset  ;
    public int zoomLevel;
    public int length;
    public int roadID;
    RandomAccessFile fstream ;
    BoundsF bounds;
    public  LinkedList chachedPoints = new LinkedList();
    public boolean  isChached=false;
    
    fastNode(int pnodeName, String plabel , long pfileOffset , int ptype,int pzoomLevel,int plength , RandomAccessFile pfstream ,BoundsF pBounds ,int pRoadID){
        this.Label = plabel;
        this.fileOffset = pfileOffset;
        this.Type = ptype;
        this.Name = pnodeName;
        this.roadID=pRoadID;
        this.zoomLevel = pzoomLevel;
        this.length=plength;
        this.fstream = pfstream;
        this.bounds=pBounds;
        
    }
    
    
    
    public Polygon ppoint(Client client, double zoomLevel,PointF pcurrentFileCoordinate ) {
        
        Polygon dummyArrayList = new Polygon();
        try {
            
            
            float dummyPointX;
            float dummyPointY;
            
            
            if (!isChached){
                
                byte [] bytePoints=null;
                fstream.seek(fileOffset);
                byte [] bytezLabelLength=new byte[1];
                fstream.read(bytezLabelLength);
                byte [] byteLabel=new byte[bytezLabelLength[0]];
                fstream.read( byteLabel);
                this.Label=new String(byteLabel);
            }
            
            Iterator chachedit = chachedPoints.iterator();
            
            for(int k=0;k<length;k++){
                
                
                if (!isChached){
                    
                    
                    dummyPointX= fstream.readFloat();
                    dummyPointY= fstream.readFloat();
                    chachedPoints.add( new Cpoint(dummyPointX,dummyPointY)  );
                    
                    
                }else {
                    Cpoint o = (Cpoint) chachedit.next();
                    
                    dummyPointX=o.x;
                    dummyPointY=o.y;
                    
                    
                    
                }
                
                
                
                
                
                int[] dmyPoints =helper.coordinateToPoint( dummyPointX,dummyPointY ,zoomLevel,pcurrentFileCoordinate);
                dummyArrayList.addPoint(dmyPoints[0],dmyPoints[1]  );
                
                
                
                
                
            }
        
            isChached=true;
            
        } catch (Exception ex) {
            
        }
        
        return dummyArrayList;
        
        
    }
    
    
    public String toString() {
        return Label ;
        
    }
    
    
    
}