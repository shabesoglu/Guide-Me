package GUI;


import Helper.PointF;
import Helper.helper;
import astarnet.AStar;
import astarnet.Graph;
import astarnet.Node;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
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
import java.nio.ByteBuffer;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Timer;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.NullCipher;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.event.TreeModelListener;
import javax.swing.plaf.synth.Region;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;






class mapTableModel extends AbstractTableModel {
    public Object[] nodesArray;
    mapTableModel( LinkedList pNodesArray ){
        this.nodesArray= pNodesArray.toArray();
    }
    
    public int getRowCount() {
        return nodesArray.length;
    }
    
    public int getColumnCount() {
        return 5;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        SlowNode dummyNode =(SlowNode)  nodesArray[rowIndex];
        switch( columnIndex){
            case 0 :
                return dummyNode.pData;
            case 1 :
                return dummyNode.pLabel;
            case 2 :
                return dummyNode.pName;
            case 3 :
                return dummyNode.pType;
            case 4 :
                return dummyNode.pRoadId;
            case 5 :
                return dummyNode.pbounds;
                
                
        }
        return null;
        
    }
    
}


public class mapFile {
    
    public LinkedList nodesArray = new LinkedList();
    mapTableModel TableModel ;
    ArrayList loadedFiles = new ArrayList();
    public String fileName;
    public int currentFileWest;
    public int currentFileEast ;
    private PointF startCoordinate  = new PointF(42.033f, 27.77618f);
    private static float startFileWest  = 5194;
    private static float startFileEast = 1050;
    byte [] contentFile = new byte[20000000];
    public  PointF currentFileCoordinate=new PointF(42.033f, 27.77618f);
    private RandomAccessFile indexFile=null;
    MainGUI parent=null;
    float  maxY  = 0;
    float minX = 99999;
    float nodeMinX=99999;
    float nodeMaxX=0;
    float nodeMinY=99999;
    float nodeMaxY=0;
    FileOutputStream fstream;
   int startDrawPoint=0;
   public int BzoomLevelValue=0;

    private int zoomLevelValue=0;
      public void setZoomLevel(int pBzoomLevelValue){
        BzoomLevelValue=pBzoomLevelValue;
        
        if (BzoomLevelValue<=500)    zoomLevelValue = 2;
        if(BzoomLevelValue>500 && BzoomLevelValue<=1270) zoomLevelValue = 1;
        if (BzoomLevelValue>1270)    zoomLevelValue = 0;
        
        
        //draw( parent.jPanel2.getGraphics());
    }
   
     public  boolean  draw(Graphics pPicture ,boolean clearStart,JProgressBar j ){
        //Image b = new Image(pPicture.getWidth(), pPicture.getHeight() );
       
         
        Graphics  g = pPicture;
        g.setColor(Color.RED);
        g.drawString(String.valueOf(BzoomLevelValue) ,22,22);
        g.drawString(String.valueOf(zoomLevelValue) ,72,72);
        if (clearStart) startDrawPoint=0;
        int size=nodesArray.size();
        
     j.setMaximum(size);
        
        for (int k=startDrawPoint ;k< size;k++ ){
         j.setValue(k);
         j.update(j.getGraphics());
          SlowNode polygonItem = (SlowNode) nodesArray.get(k);
            
//            ArrayList dummyArray = polygonItem.ppoint(zoomLevel,currentFileCoordinate);
            Polygon dummyPoly = polygonItem.ppoint(BzoomLevelValue,currentFileCoordinate);
            
//            int dummyPointsX[] = new int[dummyArray.size()];
//            int dummyPointsY[] = new int[dummyArray.size()];
            
            int count = 0;
            
            if (polygonItem.pData.getZoomLevel() != zoomLevelValue) continue;
            
//            PointF dmyPoint=null;
//            for( Object objpolygonPointItem : dummyArray){
//
//                PointF polygonPointItem = (PointF)objpolygonPointItem;
//                dmyPoint=coordinateToPoint(polygonPointItem, zoomLevel);
//
//                dummyPointsX[count]= dmyPoint.getiX();
//                dummyPointsY[count]= dmyPoint.getiY();
//
//                count ++;
//            }
            
            
            
            if( polygonItem.pName==enumNodeNames.poi) {
                
                
                
           //    g.fillOval(dummyPoly.xpoints[0], dummyPoly.ypoints[0], 2, 2);
                
            }
            
            if( polygonItem .pName==enumNodeNames.polyLine) {
                
                //g.FillPolygon(getBrush(polygonItem.Pname, polygonItem.PType), dummyPoints)
                
//                g.drawPolyline(dummyPointsX,dummyPointsY,count);
            //    g.drawPolyline(dummyPoly.xpoints,dummyPoly.ypoints,dummyPoly.npoints);
                g.setColor(Color.BLACK);
                g.drawPolyline(dummyPoly.xpoints,dummyPoly.ypoints,dummyPoly.npoints);
                //     g.FillEllipse(Brushes.Azure, dummyPoints(0).X, dummyPoints(0).Y, 2, 2);
            }
            
            if( polygonItem.pName==enumNodeNames.polygon) {
                
                
               g.setColor(Color.GRAY) ;
                g.fillPolygon(dummyPoly.xpoints,dummyPoly.ypoints,dummyPoly.npoints); 
               
            }
            
            //  g.drawString(polygonItem.pLabel,dummyPoly.xpoints[0], dummyPoly.ypoints[1]);
            
            
                
            
        }
        
        startDrawPoint=size;
        if (startDrawPoint==size) return false;
        return true;
        
    }
    
    public Graph initializeAstarGraph(){
        final Graph G = new Graph();
        (new Thread(){
            public void run() {
                
                DataInputStream SR;
                
                
                Node[] nodeArr = new Node[350000];
                String S;
                try {
                    FileInputStream fs = new FileInputStream("c:\\2\\out.txt");
                    SR = new DataInputStream(fs);
                    int max= SR.available();
                    parent.jProgressBar2.setMaximum(max);
                    while (SR.available() != 0 ) {
                        S = SR.readLine();
                        parent.jProgressBar2.setValue(max-SR.available());
                        
                        String   nodeName  =S.split("\\|")[0].split("\\*")[0].split("@")[1];
                        String   roadID  =S.split("\\|")[0].split("\\*")[0].split("@")[0];
                        String[] nodeParam = S.split("\\|")[0].split("\\*")[1].split(",");
                        
                        String[] leafNodes = S.split("\\|")[1].split("\\$")[0].split("-");
                        String weight = S.split("\\|")[1].split("\\$")[1];
                        
                        double x = Double.parseDouble(nodeParam[0]);
                        double y = Double.parseDouble(nodeParam[1]);
                        
                        int shouldAddIndexMain = Integer.parseInt(S.split("\\|")[0].split("\\*")[0].split("@")[0]);
                        if (nodeArr[shouldAddIndexMain] == null){
                            nodeArr[shouldAddIndexMain] = new Node(shouldAddIndexMain, nodeName, x, y, 0);
                            
                            
                            G.AddNode(nodeArr[shouldAddIndexMain]);
                        } else {
                            nodeArr[shouldAddIndexMain]._Name=nodeName;
                            nodeArr[shouldAddIndexMain]._roadID=Integer.valueOf(roadID).intValue();
                            
                        }
                        
                        Node tempNode = nodeArr[shouldAddIndexMain];
                        
                        for (int k = 0; k < leafNodes.length ; k++) {
                            String [] parsed=leafNodes[k].split("\\*");
                            
                            int shouldAddIndex = Integer.parseInt(parsed[0]);
                            if (nodeArr[shouldAddIndex] == null){
                                nodeArr[shouldAddIndex] = new Node(0,"", Double.parseDouble(parsed[1].split(",")[0]), Double.parseDouble(parsed[1].split(",")[1]), 0);
                                
                                G.AddNode(nodeArr[shouldAddIndex]);
                            }
                            
                            Node TempLeaf = nodeArr[shouldAddIndex];
                            
                            if (!tempNode.ToString().equals(TempLeaf.ToString()))
                                G.AddArc(tempNode, TempLeaf, Integer.parseInt(weight)*40 );
                            
                        }
                        
                    }
                    SR.close();
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                
            }
            
        }).start();
        
        
        return G;
        
    }
    
    public PointF coordinateToPoint(PointF pPoint, int zoomLevel) {
        
        PointF currentFileCoordinate  = new PointF((float)(startCoordinate.getX() - (startFileEast - currentFileEast) * 0.04), (float)(startCoordinate.getY() + (currentFileWest - startFileWest) * 0.04));
        PointF dummyPoint  = new PointF((currentFileCoordinate.getX() - pPoint.getX()) * zoomLevel, (currentFileCoordinate.getY() - pPoint.getY()) * zoomLevel);
        
        return new PointF(-dummyPoint.getY(), dummyPoint.getX());
        
    }
    
    public mapFile(MainGUI pParent) {
        
        parent=pParent;
        
    }
    
    public String convertTypes( int dummyType){
        switch(dummyType){
            case 12036: return "Airports";
            case 11265: return "Amusement/Theme parks";
            case 25604: return "Religious Centers";
            case 12291: return "City halls";
            case 11269: return "Schools";
            case 11009: return "Hotels/Motels";
            case 11012: return "Resorts";
            case 25602: return "Buildings";
            case 25611: return "Militaries";
            case 12292: return "Court Houses";
            case 11780: return "Shopping Malls";
            case 11523: return "Cinemas";
            case 11522: return "Bar/Nightclubs";
            
            case 12289: return "Police Stations";
            case 11267: return "Libraries";
            case 10755: return "Dinings";
            case 12290: return "Hospitals";
            case 11521: return "Live Theatres";
            case 11266: return "Museums";
            case 22016: return "Squares";
            case 11271: return "Zoos";
            case 10752: return "Cafe/Restaurants";
            case 25617: return "Towers";
            case 12040: return "Ground Transportations";
            case 11776: return "Shopping";
            case 12037: return "Post Offices";
            case 25603: return "Cemetaries";
            case 11270: return "Parks";
            case 11781: return "Pharmacies";
            case 10756: return "Dining (Chinese)";
            case 10763: return "Dining (sea food) ";
            case 1024: return "Large Cities (1-2M) ";
            case 1792: return "Medium Cities (100-200K)";
            case 2560: return "Small city/town (10-20K)";
            
            case 4: return "Arterial road";
            case 3: return "Other highway road";
            case 2: return "Principal highway";
            case 1: return "Major highway";
            case 6: return "Residential street";
            case 5: return "Collector road";
            case 10: return "Unpaved road";
            case 7: return "Alleyway/private driveway";
            case 20: return "Railroad";
            case 0: return "Road";
            
            default:    return String.valueOf(dummyType);
        }
    }
    
    public void initialize(String pFileName,boolean singleMode){
        System.gc();
        DefaultMutableTreeNode top ;
        DefaultTreeModel  defaultModel ;
        final DefaultMutableTreeNode treenodePOI =new DefaultMutableTreeNode("POI");
        final DefaultMutableTreeNode treenodePOLYGON =new DefaultMutableTreeNode("POLYGON");
        final DefaultMutableTreeNode treenodePOLYLINE =new DefaultMutableTreeNode("POLYLINE");
        final boolean dummySingleMode=singleMode;
        
        if (singleMode){
            top = new DefaultMutableTreeNode("Categories");
            defaultModel =  new DefaultTreeModel(top);
            parent.jTree1.setModel(defaultModel);
            
            
            
            top.add( treenodePOI );
            top.add( treenodePOLYGON );
            top.add( treenodePOLYLINE );
            parent.jTree1.expandRow(0);
            parent.jTree1.expandRow(1);
            parent.jTree1.expandRow(2);
            parent.jTree1.expandRow(3);
        }
        
        this.fileName=pFileName;
        final String sfilename=pFileName;
        try{
            
            Thread temp= (new Thread() {
                
                public void run(){
                    
                    
                    try{
                        int PN=0;
                        int PL=0;
                        
                        int PriorityIndex=0;
                        int dummyDataStartAddress=0;
                        
                        nodesArray.clear();
                        
                        maxY  = 0;
                        minX = 99999;
                        FileInputStream fstream = new FileInputStream(sfilename);
                        
                        String  s = null;
                        List a = new List();
                        
                        int  dummyType =-1;
                        String  dummyLabel = "";
                        int dummyRoadID=0;
                        polyCollection dummyData   = new polyCollection();
                        enumNodeNames nodeTypeName = enumNodeNames.nothing;
                        
                        boolean addFirst  = false;
                        
                        DataInputStream in = new DataInputStream(fstream);
                        
                        nodeMinX=99999;
                        nodeMaxX=0;
                        nodeMinY=99999;
                        nodeMaxY=0;
                        parent.jProgressBar1.setMaximum(in.available());
                        DefaultMutableTreeNode treenodeDummy=null;
                        Hashtable hash= new Hashtable();
                        
                        while (in.available() !=0){
                            //  Thread.sleep(100);
                            
                            parent.jProgressBar1.setValue(parent.jProgressBar1.getMaximum()- in.available());
                            
                            s=in.readLine();
                            
                            String dummyText = s.split("=")[0].toLowerCase();
                            
                            
                            
                            if(dummyText.equals( "[poi]")){
                                treenodeDummy = treenodePOI;
                                dummyData = new polyCollection();
                                nodeTypeName = enumNodeNames.poi;
                                
                            }
                            
                            if(dummyText.equals( "[polyline]")){
                                treenodeDummy = treenodePOLYLINE;
                                dummyData = new polyCollection();
                                nodeTypeName = enumNodeNames.polyLine;
                                
                            }
                            
                            if(dummyText.equals( "[polygon]")){
                                treenodeDummy = treenodePOLYGON;
                                dummyData = new polyCollection();
                                nodeTypeName = enumNodeNames.polygon;
                                
                            }
                            
                            if(dummyText.length()>3 )
                                if(dummyText.substring(0,3).equals("nod")) {
                                try {
                                    if(dummyText.length()>5){
                                        if(dummyText.substring(0,5).equals("nodes")){
                                            
                                            Pattern p = Pattern.compile("\\d+,\\d+(,\\d+)*");
                                            
                                            Matcher m = p.matcher(s.split("=")[1]);
                                            
                                            while(m.find()) {
                                                
                                                String item=m.group();
                                                
                                                
                                                int index= Integer.parseInt( item.split(",")[0] );
                                                
                                                String hede=(String) dummyData.get(index);
                                                
                                                dummyData.set(index,hede +","+ item.split(",")[1] );
                                            }
                                            
                                        }
                                        
                                    }else{
                                        int index= Integer.parseInt( s.split("=")[1].split(",")[0] );
                                        
                                      
                                        String hede=(String) dummyData.get(index);
                                        
                                    
                                        dummyData.set(index,hede +"," +s.split("=")[1].split(",")[1] );
                                    }
                                } catch (NumberFormatException ex) {
                                    
                                }
                                
                                }
                            
                            
                            
                            
                            if(dummyText. equals("type"))
                                dummyType = Integer.parseInt(s.split("=")[1].replace("0x",""),16);
                            
                            
                            if(dummyText. equals("label"))
                                dummyLabel = s.split("=")[1];
                            
                            
                            
                            if(dummyText. equals("roadid"))
                                dummyRoadID = Integer.valueOf(s.split("=")[1]);
                            
                            if(dummyText. equals("data0")||dummyText. equals("data1")||dummyText. equals("data2")||dummyText. equals("data3")) {
                                dummyDataStartAddress = dummyData.size();
                                
                                
                                dummyData   = new polyCollection();
                                dummyData.setZoomLevel( Integer.parseInt(s.split("=")[0].toLowerCase().substring(s.split("=")[0].toLowerCase().length()- 1)));
                                
                                
                                Pattern p = Pattern.compile("\\d+\\.\\d+,\\d+\\.\\d+");
                                
                                Matcher m = p.matcher(s.split("=")[1]);
                                StringBuffer sb = new StringBuffer();
                                
                                while(m.find()) {
                                    
                                    
                                    String item=m.group();
                                    
                                    float x  = 0;
                                    float y  = 0;
                                    
                                    
                                    
                                    
                                    x = Float.parseFloat(item.split(",")[0]);
                                    
                                    y = Float.parseFloat(item.split(",")[1]);
                                    if (x < minX)  minX = x;
                                    if (y > maxY)  maxY = y;
                                    
                                    if (x < nodeMinX)  nodeMinX = x;
                                    if (y > nodeMaxY)  nodeMaxY = y;
                                    
                                    if (x > nodeMaxX)  nodeMaxX = x;
                                    if (y < nodeMinY)  nodeMinY = y;
                                    
                                    
                                    dummyData.add(item);
                                    
                                    
                                }
                                
                                
                                
                                switch(dummyType){
                                    
                                    case 75:
                                        PriorityIndex=0;
                                        PN++;PL++;
                                        
                                        break;
                                    case 40 :
                                        PriorityIndex=PN;
                                        PL++;
                                        PN++;
                                        break;
                                        
                                    default:
                                        PriorityIndex=PL;
                                        PL++;
                                        break;
                                        
                                        
                                }
                                
                                
                                
                                SlowNode dummyNode= new SlowNode(nodeTypeName, dummyLabel, dummyData, dummyType,new BoundsF(nodeMinX,nodeMinY,nodeMaxX,nodeMaxY),dummyRoadID);
                                
                                
                                
                                nodesArray.add(PriorityIndex, dummyNode);
                                
                                if(dummySingleMode){
                                    DefaultMutableTreeNode treenodeDummyType;
                                    
                                    if (hash.get( treenodeDummy.toString()+"\\"+convertTypes( dummyType)) !=null ) {
                                        treenodeDummyType= (DefaultMutableTreeNode) hash.get( treenodeDummy.toString()+"\\"+convertTypes( dummyType)   );
                                        
                                        
                                    } else {
                                        treenodeDummyType=new DefaultMutableTreeNode(convertTypes( dummyType));
                                        hash.put(treenodeDummy.toString()+"\\"+convertTypes( dummyType),treenodeDummyType ) ;
                                        treenodeDummy.add(treenodeDummyType);
                                        
                                    }
                                    
                                    treenodeDummyType.add(new DefaultMutableTreeNode(dummyNode));
                                }
                                
                            }
                            
                            if(dummyText.equals( "[end]") ) {
                                dummyDataStartAddress = 0;
                                addFirst = false;
                                dummyLabel = "";
                                dummyType = -1;
                                dummyRoadID=0;
                                nodeMinX=99999;
                                nodeMaxX=0;
                                nodeMinY=99999;
                                nodeMaxY=0;
                                
                                
                            }
                            
                            if(dummyText.equals( "background"))
                                addFirst = true;
                            
                            
                            
                            
                        }
                        
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if(dummySingleMode)  {
                        TableModel= new mapTableModel(nodesArray);
                        parent.tblResult.setModel(TableModel);
                        helper.zout("Loading finished :" +fileName);
                    }
                    //JOptionPane.showMessageDialog(null,"Finished");
                }
            });
            temp.start();
            if(!singleMode)    temp.join();
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    void convertAll(){
        parent.jProgressBar2.setMaximum((1050-1009)*(5277-5194));
        
        (new Thread(){
            public void run() {
                
                for(int x=1009;x<1050;x++)
                    
                    for(int y=5194;y<5277;y++) {
                    try {
                        sleep(10);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    
                    parent.jProgressBar1.setValue((x-1009)*(5277-5194)+(y-5194));
                    initialize("C:\\vmaps\\New Folder (2)\\" + x + "-" + y +".mp" ,false);
                    convert("c:\\storage card\\mps2\\" + x + "-" + y  );
                    parent.jProgressBar2.setValue((x-1009)*(5277-5194)+(y-5194));
                    }
            }}).start();
            
    }
    
    void convert(String Filename){
        try {
            FileOutputStream fstream = new FileOutputStream(Filename+".dat");
            FileOutputStream dataIndexes = new FileOutputStream(Filename+".idx");
            
            fstream.write(byteConverter.float2Byte(minX));
            fstream.write(byteConverter.float2Byte(maxY));
            long fileOffsetCounter = 0;
            short length =0;
            long fileOffset=0;
            byte zlnt;
            int type;
            int roadID;
            for (int k=0 ;k< nodesArray.size();k++ ){
                SlowNode polygonItem = (SlowNode) nodesArray.get(k);
                
                
                zlnt= byteConverter.int2Byte(polygonItem.pData.getZoomLevel()<<4 | polygonItem.pName.ordinal())[3];
                length=(short)polygonItem.ppoint().size();
                type = polygonItem.pType;
                roadID=polygonItem.pRoadId;
                
                fstream.write(byteConverter.int2Byte(type));
                fstream.write(zlnt);
                fstream.write(byteConverter.int2Byte(roadID));
                fstream.write(byteConverter.short2Byte(length));
                fstream.write(byteConverter.long2Byte(fileOffsetCounter));
                
                fstream.write(polygonItem.pbounds.toBytes());
                
                PointF dmyPoint=null;
                Polygon dp = new Polygon();
                
                
                dataIndexes.write((byte)polygonItem.pLabel.getBytes().length);
                fileOffsetCounter += 1;
                dataIndexes.write(polygonItem.pLabel.getBytes());
                fileOffsetCounter += polygonItem.pLabel.getBytes().length;
                
                for( Object objpolygonPointItem : polygonItem.ppoint()){
                    
                    PointF polygonPointItem = (PointF)objpolygonPointItem;
                    length=(short)polygonItem.ppoint().size();
                    dataIndexes.write(byteConverter.float2Byte(polygonPointItem.getfX()));
                    dataIndexes.write(byteConverter.float2Byte(polygonPointItem.getfY()));
                    
                    fileOffsetCounter += 8;
                    
                }
                
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    void bufferAll(){
        bufferAll(this.fileName);
    }
    
    void bufferAll(String fname){
        Object startTime;
        FileInputStream fstream;
        fileName=fname;
        try {
            
            fstream = new FileInputStream(fname);
            int size = fstream.available();
            contentFile= new byte[size];
            
            fstream.read(contentFile);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    private static void copyUsingClone( int[] aArray , int startIndex, int stopIndex) {
        for ( int idx = 0 ; idx < stopIndex; ++idx ) {
            
            int[] copy = (int[])aArray.clone();
            
        }
    }
    
    private static void cpyArray( byte[] source,byte[] destination, int startIndex, int length) {
        
        
        System.arraycopy(source,startIndex,destination,0,length);
        
        
        
    }
    
    public  void writeOutTxt(String fileName) throws Exception{
        System.gc();
        final node2[] nodesArray2;
        
        nodesArray2 = new node2[650000] ;
        final   FileInputStream fstream = new FileInputStream(fileName);
        final   DataInputStream in = new DataInputStream(fstream);
        
        for (int k=0;k<nodesArray2.length;k++) nodesArray2[k]=new node2();
        
        (new Thread(){
            public void run() {
                
                int total=200000;
                
                
                try {
                    
                    int prevIndex=-1;
                    String  s = null;
                    String dummyType="";
                    String dummyLabel = "";
                    ArrayList dummyPosition=new ArrayList();
                    ArrayList dummyNodes=new ArrayList();
                    enumNodeNames nodeTypeName = enumNodeNames.nothing;
                    int dummyRoadID= -1;
                    
                    
                    
                    parent.jProgressBar1.setMaximum(in.available());
                    int max= parent.jProgressBar1.getMaximum();
                    int lineCount=0;
                    
                    
                    
                    while (in.available() !=0){
                        parent.jProgressBar1.setValue(max-in.available());
                        s=in.readLine();
                        
                        
                        
                        String dummySplit[]=s.split("=");
                        String dummyText = dummySplit[0].toLowerCase();
                        String paramText ="";
                        
                        
                        
                        if(dummySplit.length>1)
                            paramText = dummySplit[1].toLowerCase();
                        
                        
                        if(dummyText. equals("label"))
                            dummyLabel = s.split("=")[1];
                        
                        
                        if(dummyText.equals( "[polyline]")){
                            nodeTypeName = enumNodeNames.polyLine;
                            dummyPosition.clear();
                        }
                        
                        if (nodeTypeName==enumNodeNames.polyLine) {
                            
                            if(dummyText. equals("roadid"))
                                dummyRoadID = Integer.parseInt(paramText);
                            
                            if( dummyText.equals("type") )
                                dummyType=paramText;
                            
                            
                            
                            if(dummyText. equals("data0") && dummyRoadID>0  ) {
                                
                                
                                Pattern p = Pattern.compile("\\d+\\.\\d+,\\d+\\.\\d+");
                                Matcher m = p.matcher(paramText);
                                
                                int k=0;
                                while(m.find()) {
                                    String str=m.group();
                                    dummyPosition.add(str);
                                    
                                    dummyNodes.add("-1");
//                                    int currentIndex = total++;
//
//                                    nodesArray2[currentIndex].Name=dummyLabel;
//                                    nodesArray2[currentIndex].roadID.add(dummyRoadID);
//                                    nodesArray2[currentIndex].type = dummyType;
//                                    nodesArray2[currentIndex].position=str;
//                                    nodesArray2[currentIndex].posIndex=0;
//                                    if (prevIndex!=-1){
//                                        nodesArray2[prevIndex].nodeID.add(currentIndex);
//                                        nodesArray2[currentIndex].nodeID.add(prevIndex);
//
//                                    }
//                                    prevIndex=currentIndex;
                                    
                                }
                                
                            }
                            
                            if(dummyText.substring(0,3).equals("nod")   ) {
                                
                                
                                int subIndex   = Integer.parseInt( paramText.split(",")[0]);
                                int currentIndex = Integer.parseInt( paramText.split(",")[1]);
                                
                                nodesArray2[currentIndex].Name+="%"+dummyLabel;
                                nodesArray2[currentIndex].roadID.add(dummyRoadID);
                                nodesArray2[currentIndex].type = dummyType;
                                nodesArray2[currentIndex].position=(String) dummyPosition.get(subIndex);
                                nodesArray2[currentIndex].posIndex=subIndex;
                                dummyNodes.set(subIndex,String.valueOf(currentIndex));
//                                if (prevIndex!=-1){
//                                    nodesArray2[prevIndex].nodeID.add(currentIndex);
//                                    nodesArray2[currentIndex].nodeID.add(prevIndex);
//
//                                }
//                                prevIndex=currentIndex;
//
                                
                            }
                            
                            
                            
                        }
                        
                        if(dummyText.equals( "[end]")) {
                            
                            for(int k=1;k<dummyPosition.size();k++){
                                 int prvIndex= Integer.valueOf(dummyNodes.get(k-1).toString()).intValue()  ;
                                int index= Integer.valueOf(dummyNodes.get(k).toString()).intValue()  ;
                                if(index==-1 ) {index= total++;
                                
                                nodesArray2[index].Name+="%"+dummyLabel;
                                nodesArray2[index].roadID.add(dummyRoadID);
                                nodesArray2[index].type = dummyType;
                                nodesArray2[index].position=(String) dummyPosition.get(k);
                                nodesArray2[index].posIndex=k;
                               dummyNodes.set(k,index);
                                }
                                nodesArray2[index ].nodeID.add(prvIndex );
                                nodesArray2[prvIndex].nodeID.add(index);
                                
                                
                            }
                            
                            
                            
                            prevIndex=-1;
                            nodesArray.clear();
                            dummyPosition.clear();
                            dummyNodes.clear();
                            dummyRoadID=-1;
                            nodeTypeName= enumNodeNames.nothing;
                            
                            
                            
                        }
                        
                    }
                    
                    ///////////////////////////////////////////////////////////////////////////////////////
                    FileOutputStream fout= new FileOutputStream("c:\\2\\out.txt");
                    
                    parent.jProgressBar2.setMaximum(nodesArray2.length);
                    
                    for(int k=0;k<nodesArray2.length;k++){
                        parent.jProgressBar2.setValue(k);
                        if( !nodesArray2[k].position.equals("")){
                            fout.write( ( String.valueOf(k) +"@"+   nodesArray2[k].Name  +"*"+   nodesArray2[k].position + "|" ). getBytes());
                            for(Object index : nodesArray2[k].nodeID ){
                                
                                fout.write( ( index.toString() +"*" + nodesArray2[ Integer.parseInt(index.toString())].position +"*" + nodesArray2[ Integer.parseInt(index.toString())].posIndex +"-" ).getBytes() )  ;
                            }
                            
                            String tmpWeight = nodesArray2[k].type.split("x")[1];
                            
                            if( !tmpWeight.equals("a") && !tmpWeight.equals("0") ){
                                fout.write( ("$"+tmpWeight).getBytes());
                                
                            }else{
                                fout.write( "$8".getBytes());
                            }
                            
                            
                            fout.write("\n".getBytes());
                        }
                        
                        fout.flush();
                        
                    }
                    
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
            }
            
        }).start();
        
    }
    
}



class node2{
    
    String Name="";
    String position="";
    int posIndex=0;
    String type="";
    LinkedList  nodeID=new LinkedList();
    LinkedList  roadID=new LinkedList();
    
}




    

