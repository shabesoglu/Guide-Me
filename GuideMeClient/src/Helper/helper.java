package Helper;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;




public final  class helper {
    
    private static PointF startCoordinate  = new PointF(42.00000d+0.04d,27.80000d-0.04d);
    
    private static int startFileWest  = 5194;
    private static int startFileEast = 1050;
    
    static public double toDecimal(String Pos){

        double PosDb = Double.valueOf(Pos).doubleValue();
        double Deg = Math.floor((double)(PosDb / 100));
        double DecPos = Deg + ((PosDb - (Deg * 100)) / 60);
        
        return DecPos;
        
    }
    

    
    public     static void copyUsingClone( int[] aArray , int startIndex, int stopIndex) {
        for ( int idx = 0 ; idx < stopIndex; ++idx ) {
            
            int[] copy = (int[])aArray.clone();
            
        }
    }
    
    public static void cpyArray( byte[] source,byte[] destination, int startIndex, int length) {
        
 
        System.arraycopy(source,startIndex,destination,0,length);
        
    }
    
    static void   msgbox(String msg){
        JOptionPane.showMessageDialog(null,msg);
    }
    
    static void msgbox(int msg){
        JOptionPane.showMessageDialog(null,String.valueOf(msg));
    }
    
    static void msgbox(Object msg){
        JOptionPane.showMessageDialog(null,String.valueOf(msg));
    }
    
    static void msgbox(long msg){
        JOptionPane.showMessageDialog(null,String.valueOf(msg));
    }
    
    public static PointF coordinateToPoint(PointF pPoint, double zoomLevel,PointF currentFileCoordinate ) {
        
        
        double px=  currentFileCoordinate.getX()-pPoint.getX()  ;
        double py= currentFileCoordinate.getY() - pPoint.getY() ;
        
        return new PointF(- (int)( py *zoomLevel) , (int)(px * zoomLevel) );
        
    }
    
       public static int []  coordinateToPoint(double x,double y, double zoomLevel,PointF currentFileCoordinate ) {
        
        
        double px=  currentFileCoordinate.getX()-x ;
        double py= currentFileCoordinate.getY() - y ;
        int []  tmp= { -(int) ( py *zoomLevel) , (int)(px * zoomLevel) };
        return tmp; 
        
    }
    
    public static String coordinateToFileName(PointF pPoint) {
        
        
        
        double px= (  ((startCoordinate.getX()*100000 ) -  (pPoint.getX()*100000) )  *25d )/100000 ;
        double py= ( ((startCoordinate.getY()*100000 ) -  (pPoint.getY()*100000)  ) *25d ) /100000;
        
        
        
       return "c:\\Storage Card\\mps2\\"+ Integer.toString( (int) (startFileEast- (px) ) +1 )     + "-" + Integer.toString( (int) (startFileWest- (py) ) )   ;
//       return "C:\\vmaps\\2\\"+ Integer.toString( (int)Math.round(startFileEast- (px/40))-2 )    + "-" + Integer.toString(  (int)Math.round(startFileWest+ (py/40))  ) ;
        
    }
    
    public static PointF coordinateToFileStartPosition(PointF pPoint) {
        
        
        double px=  startCoordinate.getX()-pPoint.getX()  ;
        double py= startCoordinate.getY() - pPoint.getY() ;
        
        
        
        int ix =((int) ( (px/0.04) ) )  ;
        int iy=((int) ( (py/0.04) ) )  ;
        
        return new PointF(   startCoordinate.getX()+ ix*0.04 ,startCoordinate.getY()+iy*0.04)  ;
        
        
        
    }
       public static PointF coordinateToFileName2(PointF pPoint) {
        
        
        double px=  startCoordinate.getX()-pPoint.getX()  ;
        double py= startCoordinate.getY() - pPoint.getY() ;
        
        
        
        int ix =((int) ( (px/0.04) ) )  ;
        int iy=((int) ( (py/0.04) ) )  ;
        
        return new PointF(   startCoordinate.getX()+ ix*0.04 ,startCoordinate.getY()+iy*0.04)  ;
        
        
        
    }
    
    public static void msgbox(boolean b) {
        JOptionPane.showMessageDialog(null,String.valueOf(b));
    }
    
    public static String ts(long o){
        return String.valueOf(o);
        
    }
    
    public      static String ts(double o){
        return String.valueOf(o);
        
    }
    
    public  static String ts(float o){
        return String.valueOf(o);
        
    }
    
    public static String ts(int o){
        return String.valueOf(o);
        
    }
    
    public static void drawThickPolyline(Graphics g, int[] xpoints, int[] ypoints, int npoints,int thickness, Color fillColor, Color borderColor) {
        
        for(int k=1;k<npoints;k++){
            drawThickLine(g,xpoints[k-1],ypoints[k-1],xpoints[k],ypoints[k],thickness,fillColor,borderColor);
            
        }
        
    }
    
    
    public static void drawThickLine(Graphics g, int x1, int y1, int x2, int y2, int lineWidth, Color fillColor, Color borderColor) {
        //if(true)
        if(lineWidth==1)
        {
            g.setColor(fillColor);
            g.drawLine(x1,y1,x2,y2);
        return;
        }
        
        for(int i=0 ;i<lineWidth;i++)
        {
         if (i==0 ) g.setColor(borderColor);
         if (i==1 ) g.setColor(fillColor);
         if (i==lineWidth-1 ) g.setColor(borderColor);
         g.drawLine(x1+i ,y1,x2+i,y2);
        }
     
       
        
        
    }
    
    
}
