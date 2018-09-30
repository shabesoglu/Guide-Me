package GUI;
import Helper.PointF;
import Helper.helper;
import geom.Polygon2D;

import java.awt.Polygon;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class fastNode{
    
    
    public int Name  ;
    public String Label;
    public int Type;
    public long fileOffset  ;
    public int zoomLevel;
    public int length;
    RandomAccessFile fstream ;
    BoundsF bounds;
    float [] chachedPointsX=new float[100];
    float [] chachedPointsY=new float[100];
    boolean  isChached=false;
    public double maxX=0,maxY=0;
    public double minX=99999,minY=99999;
    
    fastNode(int pnodeName, String plabel , long pfileOffset , int ptype,int pzoomLevel,int plength , RandomAccessFile pfstream ,BoundsF pBounds){
        this.Label = plabel;
        this.fileOffset = pfileOffset;
        this.Type = ptype;
        this.Name = pnodeName;
        this.zoomLevel = pzoomLevel;
        
        this.length=plength;
        this.fstream = pfstream;
        this.bounds=pBounds;
        
    }
    
    
    
    public Polygon ppoint(double zoomLevel,PointF pcurrentFileCoordinate ) {
        
        
        Polygon dummyArrayList = new Polygon();
        
        try {
            
            byte [] bytePoints=null;
            float dummyPointX;
            float dummyPointY;
            if (!isChached){
                bytePoints = new byte[4];
                fstream.seek(fileOffset);
            }
            
            for(int k=0;k<length;k++){
                
                if (!isChached){
                    fstream.read(bytePoints);
                    dummyPointX=byteConverter.byte2Float(bytePoints);
                    if (dummyPointX>maxX) maxX=dummyPointX;
                    if (dummyPointX<minX) minX=dummyPointX;
                    
                    
                    fstream.read(bytePoints);
                    dummyPointY=byteConverter.byte2Float(bytePoints);
                    if (dummyPointY>maxY) maxY=dummyPointY;
                    if (dummyPointY<minY) minY=dummyPointY;
                    
                    
                    chachedPointsX[k]=dummyPointX;
                    chachedPointsY[k]=dummyPointY;
                    
                }else {
                    dummyPointX=chachedPointsX[k];
                    dummyPointY=chachedPointsY[k];
                    
                }
                
                
                PointF dpoint=helper.coordinateToPoint(new PointF( dummyPointX,dummyPointY) ,zoomLevel,pcurrentFileCoordinate);
                dummyArrayList.addPoint(dpoint.getiX(),dpoint.getiY() );
                
            }
            isChached=true;
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return dummyArrayList;
        
        
    }
    
    
    
    public Polygon2D.Float gpsPoints( ) {
        
        Polygon2D.Float dummyArrayList=null;
        
        
        try {
            
            byte [] bytePoints=null;
            float dummyPointX;
            float dummyPointY;
            if (!isChached){
                bytePoints = new byte[4];
                fstream.seek(fileOffset);
            }
            
            for(int k=0;k<length;k++){
                
                if (!isChached){
                    fstream.read(bytePoints);
                    dummyPointX=byteConverter.byte2Float(bytePoints);
                    if (dummyPointX>maxX) maxX=dummyPointX;
                    if (dummyPointX<minX) minX=dummyPointX;
                    
                    
                    fstream.read(bytePoints);
                    dummyPointY=byteConverter.byte2Float(bytePoints);
                    if (dummyPointY>maxY) maxY=dummyPointY;
                    if (dummyPointY<minY) minY=dummyPointY;
                    
                    
                    chachedPointsX[k]=dummyPointX;
                    chachedPointsY[k]=dummyPointY;
                    
                }else {
                    dummyPointX=chachedPointsX[k];
                    dummyPointY=chachedPointsY[k];
                    
                }
                
                
                
                
            }
            
            dummyArrayList = new Polygon2D.Float(chachedPointsX,chachedPointsY,length);
            
            isChached=true;
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return dummyArrayList;
        
        
    }
    
    
    public String toString() {
        return Label ;
        
    }
    
    
    
}