
// <editor-fold defaultstate="collapsed" desc=" Imports ">
package GuideME;
import Helper.*;
import Helper.BoundsF;
import Helper.helper;
import RouteSearch.frmResults;
import com.sun.cdc.i18n.Helper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;

import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.awt.Graphics;
import java.lang.reflect.Array;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.Vector;
import javax.microedition.io.CommConnection;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JOptionPane;


// </editor-fold>



// <editor-fold defaultstate="collapsed" desc=" Enums ">
class   enumTypes  {
    public static int  nothing= -1;
    public static int   sea= 0x28;
    public static int  land= 0x4B;
    public static int  road=4;
    public static int  Arterial_road= 0x4;
    public static int  post_office= 0x2f05;
    
    
}



class XColor{
    public static Color GRAY=new Color(13,22,33);
    public static Color BLACK=new Color(31,22,33);
    public static Color RED=new Color(231,222,33);
    public static Color BLUE=new Color(51,72,33);
    public static Color ORANGE =new Color(41,122,33);
    public static Color WHITE=new Color(31,62,33);
    
    
    
}



class enumNodeNames{
    public static    int nothing=-1;
    public static int  polyLine=1;
    public static int polygon=2;
    public static int poi=3;
    
}


// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" drawClass ">
abstract class drawClass extends  Object{
    
    drawClass(){
        
    }
    
    abstract  void paint(Graphics g,Polygon p,int Level) ;
    
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" Mapfile objects & Methods ">
public class mapFile {
    public Image gImage=null;
    public  Hashtable DrawnStrings= new Hashtable();
    public HashMap typeLevelArray =new HashMap();
    public int showArea=240;
    public int showPixel=(int)(showArea/0.04);
    public PointF currentFileCoordinate=new PointF(0,0);
    public double minX=99999;
    public double  maxY=0;
    
    public String fileName;
    public int currentFileWest;
    public int currentFileEast ;
    public int BzoomLevelValue=showPixel;
    RandomAccessFile  indexFile;
    byte [] contentFile=null;
    public Main parent;
    float zoomFactor=0;
    long startTime ;
    int cnt=0;
    ArrayList loadedFiles = new ArrayList();
    BoundsF drawBounds;
    PointF focusPoint = new PointF(0,0);
    
    private drawClass otherDraw;
    String  drawingString="";
    public PointF startPoint=new PointF(0,0);
    public PointF endPoint=new PointF(0,0);
    
    ImageIcon startPointImage ;
    ImageIcon endPointImage ;
    ImageIcon trackPointImage ;
    ImageIcon hospitalImage ;
    ImageIcon schoolImage ;
    ImageIcon parkImage ;
    ImageIcon libraryImage ;
    ImageIcon airportImage ;
    ImageIcon hotelImage ;
    ImageIcon buildingImage ;
    ImageIcon shoppingImage ;
    drawClass RouteDraw;

    public PointF trackPoint=new PointF(0,0);;
    
//    public Image gImage2;
//    boolean swapframe=true;
    
    public mapFile( Main parent ){
        
        
        
        this.parent=parent;
        
        final mapFile mf= this;
        startPointImage  = new ImageIcon("c:\\storage card\\startPoint.jpg");
        endPointImage  = new ImageIcon("c:\\storage card\\endPoint.jpg");
        trackPointImage  = new ImageIcon("c:\\storage card\\endPoint.jpg");
        airportImage=new ImageIcon("c:\\storage card\\airport.jpg");
        hospitalImage=new ImageIcon("c:\\storage card\\hospital.jpg");
        hotelImage=new ImageIcon("c:\\storage card\\hotel.jpg");
        parkImage=new ImageIcon("c:\\storage card\\park.jpg");
        schoolImage=new ImageIcon("c:\\storage card\\school.jpg");
        libraryImage=new ImageIcon("c:\\storage card\\library.jpg");
        buildingImage=new ImageIcon("c:\\storage card\\building.jpg");
        shoppingImage=new ImageIcon("c:\\storage card\\shopping.jpg");
        
        
        RouteDraw=  (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                
                helper.drawThickPolyline(g,p.xpoints,p.ypoints,p.npoints,10,Color.RED,Color.BLACK);
                
                g.setColor(Color.BLUE);
                g.drawString(String.valueOf(p.npoints),10,10);
                
                
                for(int k=0;k < p.npoints ;k++) {
                    g.fillOval(p.xpoints[k]-5,p.ypoints[k]-5,10,10);
                    g.drawString(String.valueOf(k),p.xpoints[k]-5,p.ypoints[k]-5);
                }
                
                
                
            }
            
        });
        
        
        
        typeLevelArray.put("1024", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                g.setColor(Color.BLACK);
                g.drawOval(p.xpoints[0] -10,p.ypoints[0]-10,10,10);
                g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                g.setColor(Color.RED);
                
                drawString(g,   p.xpoints[0],p.ypoints[0]);
                
            }
            
        })
        );
        
        typeLevelArray.put("12036", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                g.drawImage(airportImage.getImage(),p.xpoints[0]-18,p.ypoints[0]-16,airportImage.getImageObserver());
                g.setColor(Color.BLACK);
                drawString(g,   p.xpoints[0],p.ypoints[0]);
                
            }
            
        })
        );
        
        typeLevelArray.put("75", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                //g.drawImage(airportImage.getImage(),p.xpoints[0]-18,p.ypoints[0]-16,airportImage.getImageObserver());
                //g.setColor(Color.BLACK);
                //drawString(g,   p.xpoints[0],p.ypoints[0]);
                //g.drawPolygon(p);
            }
            
        })
        );
        
        typeLevelArray.put("12290", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                g.drawImage(hospitalImage.getImage(),p.xpoints[0]-18,p.ypoints[0]-16,hospitalImage.getImageObserver());
                g.setColor(Color.BLACK);
                drawString(g,   p.xpoints[0],p.ypoints[0]);
                
            }
            
        })
        );
        
        
        typeLevelArray.put("1792", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if( Level != 2 ){
                    
                    g.drawOval(p.xpoints[0] -10,p.ypoints[0]-10,10,10);
                    g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                    g.setColor(Color.RED);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
                
            }
            
        })
        );
        
        typeLevelArray.put("11523", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if( Level == 0 ){
                    
                    g.setColor(Color.BLACK);
                    g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
                
            }
            
        })
        );
        
        typeLevelArray.put("25603", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if( Level == 0 ){
                    
                    g.setColor(Color.BLACK);
                    g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
                
            }
            
        })
        );
        
        typeLevelArray.put("11522", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if( Level == 0 ){
                    
                    g.setColor(Color.BLACK);
                    g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
                
            }
            
        })
        );
        
        typeLevelArray.put("11009", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    
                    g.drawImage(hotelImage.getImage(),p.xpoints[0]-18,p.ypoints[0]-16,hotelImage.getImageObserver());
                    g.setColor(Color.BLACK);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("11270", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    
                    g.drawImage(parkImage.getImage(),p.xpoints[0]-18,p.ypoints[0]-16,parkImage.getImageObserver());
                    g.setColor(Color.BLACK);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("11269", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    
                    g.drawImage(schoolImage.getImage(),p.xpoints[0]-18,p.ypoints[0]-16,schoolImage.getImageObserver());
                    g.setColor(Color.BLACK);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("11267", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    
                    g.drawImage(libraryImage.getImage(),p.xpoints[0]-18,p.ypoints[0]-16,libraryImage.getImageObserver());
                    g.setColor(Color.BLACK);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("25602", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    
                    g.drawImage(buildingImage.getImage(),p.xpoints[0]-18,p.ypoints[0]-14,buildingImage.getImageObserver());
                    g.setColor(Color.BLACK);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("11780", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    
                    g.drawImage(shoppingImage.getImage(),p.xpoints[0]-18,p.ypoints[0]-14,shoppingImage.getImageObserver());
                    g.setColor(Color.BLACK);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("12289", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    g.setColor(Color.BLACK);
                    g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("12291", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    g.setColor(Color.BLACK);
                    g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("25604", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    g.setColor(Color.BLACK);
                    g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("11521", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    g.setColor(Color.BLACK);
                    g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("22016", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    g.setColor(Color.ORANGE);
                    g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("12037", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    g.setColor(Color.ORANGE);
                    g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("11266", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    g.setColor(Color.ORANGE);
                    g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("25617", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    g.setColor(Color.ORANGE);
                    g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("12292", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                if(Level==0){
                    g.setColor(Color.BLACK);
                    g.fillOval(p.xpoints[0] -7,p.ypoints[0]-7,5,5);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("40", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level){
                
                
                g.setColor(new Color(50,202,228));
                
                g.fillPolygon(p.xpoints,p.ypoints,p.npoints);
                g.setColor(Color.BLACK);
                drawString(g,   p.xpoints[0],p.ypoints[0]);
            }
            
        })
        );
        
        
        
        
        typeLevelArray.put("1", (new drawClass(){
            void paint(Graphics g,Polygon p,int Level){
                
                helper.drawThickPolyline(g,p.xpoints,p.ypoints,p.npoints,11- (Level *5) ,new Color(0,0,0),new Color(248,82,235));
                drawString(g,   p.xpoints[0],p.ypoints[0]);
            }
            
        })
        );
        
        
        
        
        typeLevelArray.put("2", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level){
                
                helper.drawThickPolyline(g,p.xpoints,p.ypoints,p.npoints,9- (Level *4) ,new Color(64,0,128),new Color(206,157,255));
                drawString(g,   p.xpoints[0],p.ypoints[0]);
            }
            
        })
        );
        
        
        
        typeLevelArray.put("3", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level){
                
                helper.drawThickPolyline(g,p.xpoints,p.ypoints,p.npoints,7- (Level *3) ,new Color(60,115,176),new Color(187,208,232));
                drawString(g,   p.xpoints[0],p.ypoints[0]);
            }
            
        })
        );
        
        
        
        typeLevelArray.put("4", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level){
                
                helper.drawThickPolyline(g,p.xpoints,p.ypoints,p.npoints,5- (Level *2) ,new Color(129,113,82),new Color(187,173,149));
                drawString(g,   p.xpoints[0],p.ypoints[0]);
            }
            
        })
        );
        
        
        
        typeLevelArray.put("5", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level){
                if(Level==0){
                    helper.drawThickPolyline(g,p.xpoints,p.ypoints,p.npoints,3,new Color(35,207,96),new Color(35,207,96));
                    drawString(g,   p.xpoints[0],p.ypoints[0]);}
            }
            
        })
        );
        
        typeLevelArray.put("6", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level){
                if(Level==0){
                    helper.drawThickPolyline(g,p.xpoints,p.ypoints,p.npoints,2,new Color(91,197,46),new Color(91,197,46));
                    drawString(g,   p.xpoints[0],p.ypoints[0]);}
            }
            
        })
        );
        
        typeLevelArray.put("7", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level){
                if(Level==0){
                    g.setColor(new Color(124,174,68));
                    g.drawPolyline(p.xpoints,p.ypoints,p.npoints);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("0", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                if(Level==0){
                    g.setColor(new Color(194,143,67));
                    g.drawPolyline(p.xpoints,p.ypoints,p.npoints);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        typeLevelArray.put("10", (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                if(Level==0){
                    g.setColor(new Color(194,143,67));
                    g.drawPolyline(p.xpoints,p.ypoints,p.npoints);
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
            }
            
        })
        );
        
        
        
        otherDraw=  (new drawClass(){
            
            void paint(Graphics g,Polygon p,int Level) {
                
                g.setColor(Color.BLACK);
                g.drawPolyline(p.xpoints,p.ypoints,p.npoints);
                if(Level==0) {
                    drawString(g,   p.xpoints[0],p.ypoints[0]);
                }
                
                
            }
            
        });
        
        
        
        
        
    }
    
    
    void drawString(Graphics g, int  x,int y){
        if(DrawnStrings.get(drawingString)==null){
            DrawnStrings.put(drawingString,"");
            g.drawString(drawingString,x,y);
        }
        
    }
    
    
    public double  gettZoomAreaFactor(){
        
        return gettZoomFactor()/showArea;
    }
    
    public double  gettZoomFactor(){
        
        return showArea/ (float) BzoomLevelValue;
    }
    int zoomLevelValue = 2;
    public void setZoomLevel(int pzoomLevelValue){
        
        BzoomLevelValue += (pzoomLevelValue/gettZoomFactor()) ;
        if (BzoomLevelValue<showPixel )BzoomLevelValue=showPixel;
        
        currentFileCoordinate.set(focusPoint.getX()+gettZoomFactor()/2 , focusPoint.getY()-gettZoomFactor()/2);
        
        PointF focus = helper.coordinateToPoint(focusPoint,BzoomLevelValue,currentFileCoordinate);
        
        
        if (BzoomLevelValue<=10000)    zoomLevelValue = 2;
        if(BzoomLevelValue>10000 && BzoomLevelValue<=27000) zoomLevelValue = 1;
        if (BzoomLevelValue>27000)    zoomLevelValue = 0;
        
        
        //draw( parent.jPanel2.getGraphics());
    }
    
    public void redraw(){
        // draw(parent.jPanel2.getGraphics());
        
    }
    
    public void setPositionDelta(double dX,double dY) {
        
        if (loading) return;
        
        currentFileCoordinate.setX(currentFileCoordinate.getX()+dX);
        currentFileCoordinate.setY(currentFileCoordinate.getY()+dY);
        
        PointF c1 = new PointF(  currentFileCoordinate.getfX()-0.0001  ,currentFileCoordinate.getfY()+0.0001  );
        PointF c2 = new PointF(  currentFileCoordinate.getfX()-gettZoomFactor()+0.0002 ,currentFileCoordinate.getfY()+0.0001);
        PointF c3 = new PointF(  currentFileCoordinate.getfX()-0.0001,currentFileCoordinate.getfY() +gettZoomFactor() -0.0001);
        PointF c4 = new PointF(  currentFileCoordinate.getfX() -gettZoomFactor()+0.0002,currentFileCoordinate.getfY() +gettZoomFactor()-0.0001);
        
        markAsOld();
        Load(c1);
        Load(c2);
        Load(c3);
        Load(c4);
        
        
        
        
        
        
        clearOldFiles();
        
        focusPoint.set(currentFileCoordinate.getX()-gettZoomFactor()/2,currentFileCoordinate.getY()+gettZoomFactor()/2);
        
        
        // draw(parent.jPanel2.getGraphics());
        
        PointF f= helper.coordinateToPoint(currentFileCoordinate,BzoomLevelValue,currentFileCoordinate);
        
        //  parent.jPanel2.getGraphics().drawOval(f.getiX(),f.getiY(),5,5);
        
        
    }
    
    public void setPosition(double dX,double dY) {
        if (loading) return;
        focusPoint.set(dX,dY);
        
        currentFileCoordinate.setX(dX+gettZoomFactor()/2);
        currentFileCoordinate.setY(dY-gettZoomFactor()/2);
        
        setZoomLevel(0);
        setPositionDelta(  0,0);
        
    }
    
    public Graphics g;
    public void draw(){
//        swapframe=!swapframe;
        
//        if(swapframe)
            g=gImage.getGraphics();
//        else
//            g=gImage2.getGraphics();
//        
  synchronized (this){
            DrawnStrings.clear();
        //creme.Keyboard.hide();
        
        //   g.setClip(0,0,showArea,showArea);
        g.setColor(Color.GRAY);
        g.fillRect(0,0,240,240) ;
        
        
//        g.beginUpdate();
        
        
        drawBounds= new BoundsF(  currentFileCoordinate.getfX(),currentFileCoordinate.getfY(),currentFileCoordinate.getfX()-gettZoomFactor(),currentFileCoordinate.getfY()+gettZoomFactor());
        
        for (int fp=0;fp<loadedFiles.size()+1;fp++){
            
            loadedFilesNode nowLoading =null ;
            
            if(fp!=loadedFiles.size())
                nowLoading    = (loadedFilesNode ) loadedFiles.get(fp);
            else
                if(parent.client.Route!=null)
                    nowLoading    = parent.client.Route;
                else
                    continue;
            
            ListIterator it =nowLoading.nodesArray.listIterator();
            while (it.hasNext()){
                
                fastNode polygonItem = (fastNode) it.next();
                
                
                
                if(zoomLevelValue==polygonItem.zoomLevel ||nowLoading.fileName.equals("Route") ){
                    
                    if (!nowLoading.fileName.equals("Route"))
                        if ( !drawBounds.intersects(polygonItem.bounds) )  continue;
                    
                    
                    Polygon dummyPolygon = polygonItem.ppoint( parent.client,  BzoomLevelValue  ,currentFileCoordinate);
                    drawingString=polygonItem.Label;
                    drawClass dummyDraw= ((drawClass) typeLevelArray.get( String.valueOf(polygonItem.Type) ));
                    
                    
                    
                    if (nowLoading.fileName.equals("Route")) {
                        
                        dummyDraw=RouteDraw;
                    } else{
                        if(dummyDraw==null) dummyDraw= otherDraw;
                        
                    }
                    
                    
                    
                    
                    
                    dummyDraw.paint(g,dummyPolygon, polygonItem.zoomLevel);
                    g.setColor(Color.WHITE);
                    g.fillOval(115,115,10,10);
                    
                }
                
            }
          //  g.drawString(String.valueOf(swapframe),40,30);
            gImage.flush();
            //gImage2.flush();
        }
        }
        
        
        if(startPoint.getX()!=0)
            if (drawBounds.intersects(startPoint ) ) {
            PointF startPointToScreen=helper.coordinateToPoint(startPoint,BzoomLevelValue,currentFileCoordinate);
            g.drawImage(startPointImage.getImage(),startPointToScreen.getiX(),startPointToScreen.getiY(),null);
            }
        if(endPoint.getX()!=0)
            if (drawBounds.intersects(endPoint) ) {
            PointF endPointToScreen=helper.coordinateToPoint(endPoint,BzoomLevelValue,currentFileCoordinate);
            g.drawImage(endPointImage.getImage(),endPointToScreen.getiX(),endPointToScreen.getiY(),null);
            }
            
//        g.endPaint();
        
    }
    
    Graphics mainG;
    
    
    public PointF trackPointToMap(){
    
     PointF trackPointToScreen=helper.coordinateToPoint(trackPoint,BzoomLevelValue,currentFileCoordinate);
           return trackPointToScreen;
            
    }
    
    void Load(PointF coordinate){
        
        
        
        String fname = helper.coordinateToFileName(coordinate);
        
        int index= loadedFiles.lastIndexOf(new loadedFilesNode(fname,null));
        
        if(index<0 ){
            
            loading=true;
            if (mainG!=null){
                mainG.setColor(Color.WHITE);
                mainG.fillRect(30,60,170,100);
                mainG.setColor(Color.BLACK);
                mainG.drawRect(30,60,170,100);
                mainG.drawString("Loading...",90,120);
            } else
                mainG = parent.jPanel2.getGraphics();
            
            bufferAll( fname   );
            readGuideMeMapFormatFromMemory();
            loading=false;
        } else{
            ((loadedFilesNode) loadedFiles.get(index)).New=true;
            return ;
        }
        
        
    }
    
    public boolean loading;
    void bufferAll(String fname){
        
        fileName= fname;
        try {
            indexFile= new     RandomAccessFile  (fname + ".idx","r");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        
        FileInputStream fstream;
        try {
            
            fstream = new FileInputStream(fname+".dat");
            int size = fstream.available();
            contentFile= new byte[size];
            
            fstream.read(contentFile);
            
            fstream.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    void readGuideMeMapFormatFromMemory(){
        
        int filePos=0;
        
        minX=byteConverter.fromArrayByte2Float(contentFile,filePos+=0,4);
        maxY=byteConverter.fromArrayByte2Float(contentFile,filePos+=4,4);
        filePos+=4;
        
        LinkedList nodesArray=new LinkedList();
        
        while(filePos!=contentFile.length){
            
            short length =0;
            long fileOffset=0;
            byte zlnt;
            int type;
            int zoomLevel;
            int roadID=0;
            int nodeTypeName;
            String label ;
            
            type=byteConverter.fromArrayByte2Int(contentFile,filePos,4);
            zoomLevel = contentFile[filePos+=4] >>4;
            nodeTypeName = (contentFile[filePos]&15);
            roadID=byteConverter.fromArrayByte2Int(contentFile,filePos+=1,4);
            
            length=byteConverter.fromArrayByte2Short(contentFile,filePos+=4,2);
            fileOffset=byteConverter.fromArrayByte2Long(contentFile,filePos+=2,8);
            BoundsF dummyBounds = new BoundsF(contentFile,filePos+=8,16);
            filePos+=16;
            
            fastNode fastDummyNode= new fastNode( nodeTypeName , "",fileOffset , type,zoomLevel,length,indexFile,dummyBounds,roadID);
            nodesArray.addLast(fastDummyNode);
            
            
        }
        loadedFiles.add(new loadedFilesNode(fileName,nodesArray));
        contentFile=null;
        
    }
    
    private void clearOldFiles() {
        for (int  k=0;k<loadedFiles.size();k++)
            if (  ((loadedFilesNode) loadedFiles.get(k)).New==false)
                ((loadedFilesNode) loadedFiles.get(k)).releaseMemory(loadedFiles);
        
    }
    
    private void markAsOld() {
        for (int  k=0;k<loadedFiles.size();k++)
            ((loadedFilesNode) loadedFiles.get(k)).New=false;
        
    }
    
}

// </editor-fold>
