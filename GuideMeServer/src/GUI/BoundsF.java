package GUI;
import java.awt.Polygon;
import java.awt.Rectangle;


public class BoundsF {
    public double x1,y1,x2,y2;
    
    public BoundsF(double x1,double y1,double x2,double y2) {
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    }
    
    
    
    
    public boolean intersects(BoundsF bf) {
        
        
        if(  bf.x1 <=x1 && bf.y1 >=y1 && bf.y1 <=y2 ){
            if( (bf.x2>=x1 && bf.x2<=x2 && bf.y2>=y1 && bf.y2<=y2)
            || (bf.x2>=x2 && bf.y2>=y2)
            || (bf.x2>=x1 && bf.x2<=x2 && bf.y2>=y2)
            || (bf.x2>=x2 && bf.y2>=y1 && bf.y2<=y2) )
                return true;
        }
        
        if( bf.x1<=x1 && bf.y1<=y1 ){
            if( (bf.x2>=x2 && bf.y2>=y2) || (bf.x2>=x2 && bf.y2>=y1 && bf.y2<=y2) || (bf.x2>=x1 && bf.x2<=x2 && bf.y2>=y2) ||  (bf.x2>=x1 && bf.x2<=x2 && bf.y2>=y1 && bf.y2<=y2) )
                return true;
        }
        
        if( bf.x1>=x1 && bf.x1<=x2 && bf.y1<=y1 ){
            if( (bf.x2>=x1 && bf.x2<=x2 && bf.y2>=y1 && bf.y2<=y2) || (bf.x2>=x2 && bf.y2>=y1 && bf.y2<=y2) || (bf.x2>=x2 && bf.y2>=y2) || (bf.x2>=x1 && bf.x2<=x2 && bf.y2>=y2) )
                return true;
            
        }
        
        if( bf.x1>=x1 && bf.x1<=x2 && bf.y1>=y1 && bf.y1<=y2 ){
            if( (bf.x2>=x2 && bf.y2>=y1 && bf.y2<=y2) || (bf.x2>=x1 && bf.x2<=x2 && bf.y2>=y2) || (bf.x2>=x2 && bf.y2>=y2) || (bf.x2>=x1 && bf.x2<=x2 && bf.y2>=y1 && bf.y2<=y2) )
                return true;
            
        }
        
        return false;
    }
    
    
    
    
    
    public BoundsF(byte[] pBytes){
        byte[] bx1 = new byte[4];
        byte[] by1 = new byte[4];
        byte[] bx2 = new byte[4];
        byte[] by2 = new byte[4];
        
        System.arraycopy(pBytes,0,bx1,0,4);
        System.arraycopy(pBytes,4,by1,0,4);
        System.arraycopy(pBytes,8,bx2,0,4);
        System.arraycopy(pBytes,12,by2,0,4);
        
        this.x1 = byteConverter.byte2Float(bx1);
        this.y1 = byteConverter.byte2Float(by1);
        this.x2 = byteConverter.byte2Float(bx2);
        this.y2 = byteConverter.byte2Float(by2);
    }
    
    public byte [] toBytes(){
        
        byte[] dummyByte = new byte[16];
        System.arraycopy(byteConverter.float2Byte((float)x1),0,dummyByte,0,4);
        System.arraycopy(byteConverter.float2Byte((float)y1),0,dummyByte,4,4);
        System.arraycopy(byteConverter.float2Byte((float)x2),0,dummyByte,8,4);
        System.arraycopy(byteConverter.float2Byte((float)y2),0,dummyByte,12,4);
        return dummyByte;
    }
    
}
