package GuideME;

public class byteConverter {
    
    
    public static final double[] byte2Double(byte[] inData,boolean byteSwap) {
        int j=0,upper,lower;
        int length=inData.length/8;
        double[] outData=new double[length];
        if (!byteSwap) for (int i=0;i<length;i++) {
            j=i*8;
            upper=( ((inData[j]   & 0xff) << 24) +
                    ((inData[j+1] & 0xff) << 16) +
                    ((inData[j+2] & 0xff) << 8) +
                    ((inData[j+3] & 0xff) << 0) );
            lower=( ((inData[j+4] & 0xff) << 24) +
                    ((inData[j+5] & 0xff) << 16) +
                    ((inData[j+6] & 0xff) << 8) +
                    ((inData[j+7] & 0xff) << 0) );
            outData[i]=Double.longBitsToDouble( (((long)upper) << 32) +
                    (lower & 0xffffffffl) );
        } else for (int i=0;i<length;i++) {
            j=i*8;
            upper=( ((inData[j+7] & 0xff) << 24) +
                    ((inData[j+6] & 0xff) << 16) +
                    ((inData[j+5] & 0xff) << 8) +
                    ((inData[j+4] & 0xff) << 0) );
            lower=( ((inData[j+3] & 0xff) << 24) +
                    ((inData[j+2] & 0xff) << 16) +
                    ((inData[j+1] & 0xff) << 8) +
                    ((inData[j]   & 0xff) << 0) );
            outData[i]=Double.longBitsToDouble( (((long)upper) << 32) +
                    (lower & 0xffffffffl) );
        }
        
        return outData;
    }
    
    
    public static final float fromArrayByte2Float(byte[] inData,int startIndex,int inlength) {
        int j=0,value;
        int length=inlength/4;
        float[] outData=new float[length];
        for (int i=0;i<length;i++) {
            j=startIndex+ i*4;
            value=( ((inData[j]   & 0xff) << 24) +
                    ((inData[j+1] & 0xff) << 16) +
                    ((inData[j+2] & 0xff) << 8) +
                    ((inData[j+3] & 0xff) << 0) );
            outData[i]=Float.intBitsToFloat(value);
        }
        return outData[0];
    }
    
    public static final float byte2Float(byte[] inData) {
        return   byte2Float(inData,false)[0];
    }
    public static final float[] byte2Float(byte[] inData,boolean byteSwap) {
        int j=0,value;
        int length=inData.length/4;
        float[] outData=new float[length];
        if (!byteSwap) for (int i=0;i<length;i++) {
            j=i*4;
            value=( ((inData[j]   & 0xff) << 24) +
                    ((inData[j+1] & 0xff) << 16) +
                    ((inData[j+2] & 0xff) << 8) +
                    ((inData[j+3] & 0xff) << 0) );
            outData[i]=Float.intBitsToFloat(value);
        } else for (int i=0;i<length;i++) {
            j=i*4;
            value=( ((inData[j+3] & 0xff) << 24) +
                    ((inData[j+2] & 0xff) << 16) +
                    ((inData[j+1] & 0xff) << 8) +
                    ((inData[j]   & 0xff) << 0) );
            outData[i]=Float.intBitsToFloat(value);
        }
        
        return outData;
    }
    
    
    public static final int[] byte2Int(byte[] inData,boolean byteSwap) {
        int j=0,value;
        int length=inData.length/4;
        int[] outData=new int[length];
        if (!byteSwap) for (int i=0;i<length;i++) {
            j=i*4;
            outData[i]=( ((inData[j]   & 0xff) << 24) +
                    ((inData[j+1] & 0xff) << 16) +
                    ((inData[j+2] & 0xff) << 8) +
                    ((inData[j+3] & 0xff) << 0) );
        } else for (int i=0;i<length;i++) {
            j=i*4;
            outData[i]=( ((inData[j+3] & 0xff) << 24) +
                    ((inData[j+2] & 0xff) << 16) +
                    ((inData[j+1] & 0xff) << 8) +
                    ((inData[j]   & 0xff) << 0) );
        }
        
        return outData;
    }
    
    public static final int fromArrayByte2Int(byte[] inData,int startIndex,int inlength) {
        int j=0,value;
        int length=inlength/4;
        int[] outData=new int[length];
        for (int i=0;i<length;i++) {
            j=startIndex+ i*4;
            outData[i]=( ((inData[j]   & 0xff) << 24) +
                    ((inData[j+1] & 0xff) << 16) +
                    ((inData[j+2] & 0xff) << 8) +
                    ((inData[j+3] & 0xff) << 0) );
        }
        return outData[0];
    }
    
    public static final long byte2Long(byte[] inData) {
        
        return byte2Long(inData,false)[0];
        
    }
    
    public static final long fromArrayByte2Long(byte[] inData,int startIndex,int inlength) {
        int j=0;
        long value;
        int length=inlength/8;
        long ff = 0xff;
        long[] outData=new long[length];
        for (int i=0;i<length;i++) {
            j=startIndex+ i*8;
            outData[i]=( ((inData[j]   & ff) << 56) +
                    ((inData[j+1] & ff) << 48) +
                    ((inData[j+2] & ff) << 40) +
                    ((inData[j+3] & ff) << 32) +
                    ((inData[j+4] & ff) << 24) +
                    ((inData[j+5] & ff) << 16) +
                    ((inData[j+6] & ff) << 8) +
                    ((inData[j+7] & ff) << 0) );
            
            
            
        }
        return outData[0];
    }
    
    public static final long[] byte2Long(byte[] inData,boolean byteSwap) {
        int j=0;
        long value;
        int length=inData.length/8;
        long ff = 0xff;
        long[] outData=new long[length];
        if (!byteSwap) for (int i=0;i<length;i++) {
            j=i*8;
            outData[i]=( ((inData[j]   & ff) << 56) +
                    ((inData[j+1] & ff) << 48) +
                    ((inData[j+2] & ff) << 40) +
                    ((inData[j+3] & ff) << 32) +
                    ((inData[j+4] & ff) << 24) +
                    ((inData[j+5] & ff) << 16) +
                    ((inData[j+6] & ff) << 8) +
                    ((inData[j+7] & ff) << 0) );
            
            
            
        } else for (int i=0;i<length;i++) {
            j=i*8;
            outData[i]=( ((inData[j+7]   & 0xff) << 56) +
                    ((inData[j+6] & 0xff) << 48) +
                    ((inData[j+5] & 0xff) << 40) +
                    ((inData[j+4] & 0xff) << 32) +
                    ((inData[j+3] & 0xff) << 24) +
                    ((inData[j+2] & 0xff) << 16) +
                    ((inData[j+1] & 0xff) << 8) +
                    ((inData[j]   & 0xff) << 0) );
        }
        
        return outData;
    }
    
    
    public static final short byte2Short(byte[] inData) {
        
        return byte2Short(inData,false)[0];
        
    }
    
    public static final short[] byte2Short(byte[] inData,boolean byteSwap) {
        
        int length=inData.length/2;
        short[] outData=new short[length];
        if (!byteSwap) for (int i=0,j=0;i<length;i++,j+=2) {
            
            outData[i]=(short)( (inData[j] << 8) + (inData[j+1] & 0xff) );
        } else for (int i=0;i<length;i++) {
            int j=i*2;
            outData[i]=(short)( ((inData[j+1] & 0xff) << 8) + ((inData[j] & 0xff) << 0) );
        }
        
        return outData;
    }
    
    public static final short fromArrayByte2Short(byte[] inData,int startIndex,int inlength) {
        
        int length=inlength/2;
        short[] outData=new short[length];
        
        for (int i=0,j=startIndex;i<length;i++,j+=2) {
            
            outData[i]=(short)( (inData[j] << 8) + (inData[j+1] & 0xff) );
        }
        
        
        return outData[0];
    }
    
    public static final byte[] double2Byte(double[] inData) {
        int j=0;
        int length=inData.length;
        byte[] outData=new byte[length*8];
        for (int i=0;i<length;i++) {
            long data=Double.doubleToLongBits(inData[i]);
            outData[j++]=(byte)(data>>>56);
            outData[j++]=(byte)(data>>>48);
            outData[j++]=(byte)(data>>>40);
            outData[j++]=(byte)(data>>>32);
            outData[j++]=(byte)(data>>>24);
            outData[j++]=(byte)(data>>>16);
            outData[j++]=(byte)(data>>>8);
            outData[j++]=(byte)(data>>>0);
        }
        return outData;
    }
    
    
    public static final byte[] float2Byte(float pinData) {
        float [] inData={pinData};
        int j=0;
        int length=inData.length;
        byte[] outData=new byte[length*4];
        for (int i=0;i<length;i++) {
            int data=Float.floatToIntBits(inData[i]);
            outData[j++]=(byte)(data>>>24);
            outData[j++]=(byte)(data>>>16);
            outData[j++]=(byte)(data>>>8);
            outData[j++]=(byte)(data>>>0);
        }
        return outData;
    }
    
    
    public static final byte []  int2Byte(int inData) {
        int j=0;
        
        byte[] outData=new byte[4];
        
        outData[0]=(byte)(inData>>>24);
        outData[1]=(byte)(inData>>>16);
        outData[2]=(byte)(inData>>>8);
        outData[3]=(byte)(inData>>>0);
        
        return outData;
    }
    
    
    public static final byte[] long2Byte(long pinData) {
        long [] inData={pinData};
        int j=0;
        int length=inData.length;
        byte[] outData=new byte[length*8];
        for (int i=0;i<length;i++) {
            outData[j++]=(byte)(inData[i]>>>56);
            outData[j++]=(byte)(inData[i]>>>48);
            outData[j++]=(byte)(inData[i]>>>40);
            outData[j++]=(byte)(inData[i]>>>32);
            outData[j++]=(byte)(inData[i]>>>24);
            outData[j++]=(byte)(inData[i]>>>16);
            outData[j++]=(byte)(inData[i]>>>8);
            outData[j++]=(byte)(inData[i]>>>0);
        }
        return outData;
    }
    
    
    public static final byte[] short2Byte(short pinData) {
        int j=0;
        short[] inData= {pinData};
        
        int length=inData.length;
        byte[] outData=new byte[length*2];
        for (int i=0;i<length;i++) {
            outData[j++]=(byte)(inData[i]>>>8);
            outData[j++]=(byte)(inData[i]>>>0);
        }
        return outData;
    }
    
    
    public static final byte[] double2Byte(double[] inData, boolean makeLSB) {
        if (!makeLSB) return double2Byte(inData);
        int j=0;
        int length=inData.length;
        byte[] outData=new byte[length*8];
        for (int i=0;i<length;i++) {
            long data=Double.doubleToLongBits(inData[i]);
            outData[j++]=(byte)(data>>>0);
            outData[j++]=(byte)(data>>>8);
            outData[j++]=(byte)(data>>>16);
            outData[j++]=(byte)(data>>>24);
            outData[j++]=(byte)(data>>>32);
            outData[j++]=(byte)(data>>>40);
            outData[j++]=(byte)(data>>>48);
            outData[j++]=(byte)(data>>>56);
        }
        return outData;
    }
    
    public static final byte[] float2Byte(float[] inData, boolean makeLSB) {
        if (!makeLSB) return float2Byte(inData[0]);
        int j=0;
        int length=inData.length;
        byte[] outData=new byte[length*4];
        for (int i=0;i<length;i++) {
            int data=Float.floatToIntBits(inData[i]);
            outData[j++]=(byte)(data>>>0);
            outData[j++]=(byte)(data>>>8);
            outData[j++]=(byte)(data>>>16);
            outData[j++]=(byte)(data>>>24);
        }
        return outData;
    }
    
    
    
    
    
}
