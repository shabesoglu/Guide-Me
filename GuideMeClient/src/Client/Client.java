

package Client;

import GuideME.Main;
import GuideME.StringEX;
import GuideME.fastNode;
import GuideME.loadedFilesNode;
import Helper.Cpoint;
import RouteSearch.frmNearestPOI;
import RouteSearch.frmRoutePointSelector;
import RouteSearch.frmRouteSearch;
import com.sun.cdc.i18n.Helper;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.ComboBoxModel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author sahin
 */


class AstarTableModel extends AbstractTableModel {
    public Object[] nodesArray;
    AstarTableModel( LinkedList pNodesArray ){
        this.nodesArray= pNodesArray.toArray();
    }
    
    public String  getColumnName(int i) {
        switch( i){
            case 2 :
                return "Turnpoint end";
            case 1 :
                return "Turnpoint start";
            case 0 :
                return "Locations";
        }
        return "yok" ;}
    public int getRowCount() {
        return nodesArray.length;
    }
    
    public int getColumnCount() {
        return 3;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        
        String dummyNode[] =  StringEX.split( nodesArray[rowIndex].toString(),'@');
        
        return dummyNode[columnIndex];
        
        
        
    }
    
}

class resultTableModel extends AbstractTableModel {
    public Object[] nodesArray;
    resultTableModel( LinkedList pNodesArray ){
        this.nodesArray= pNodesArray.toArray();
    }
    
    public String  getColumnName(int i) {
        switch( i){
            case 1 :
                return "Positions";
            case 0 :
                return "Locations";
        }
        return "yok" ;}
    public int getRowCount() {
        return nodesArray.length;
    }
    
    public int getColumnCount() {
        return 2;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        
        String dummyNode[] =  StringEX.split( nodesArray[rowIndex].toString(),'@');
        
        return dummyNode[columnIndex];
        
        
        
    }
    
}

public class Client {
    static Socket skt ;
    public  final  LinkedList listResult= new LinkedList();
    public Object[] resultArray;
    static  InputStreamReader isr ;
    static  BufferedReader br ;
    public loadedFilesNode Route;
    String [] params;
    InputStream is;
    PrintWriter pr;
    Main gui;
      public boolean connected=false;
    
    public Client(Main pGui) {
        gui= pGui;
    }
    
    public void send(String msg){
        
        pr.println(msg);
        pr.flush();
    }
    
    
    public  void connect(String IP, int port){
        try{
            skt = new Socket(IP, port);
            pr= new PrintWriter(skt.getOutputStream(),true);
            
            is = skt.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            final  LinkedList lk= new LinkedList();
            final  LinkedList lS= new LinkedList();
            
            
            new Thread(new Runnable(){
                public void run(){
                    try{
                        
                        
                        
                        
                        while(true) {
                            
                            String serverMessage = br.readLine();
                            params = StringEX.split(serverMessage, ':');
                            connected=true;
                            
                            
                            if(params[0].equals("nearest") ){
                                gui.objfrmNearestPOI.lblocation.setText( params[1] );
                                gui.objfrmNearestPOI.lblResult.setText( params[2] );
                            }
                            
                            
                            
                            if(params[0].equals("clrsearchlist")) {
                                try{
                                    
                                    
                                    if(params[1].equals("list") )  {
                                        gui.objfrmRoutePointSelector.cmbCategory.removeAllItems();
                                        gui.objfrmRoutePointSelector.cmbStreet.removeAllItems();
                                        gui.objfrmNearestPOI.cmbCat.removeAllItems();
                                    }
                                    if(params[1].equals("ss") )  {
                                        lk.clear();gui.objfrmRoutePointSelector.jTable1.removeAll();
                                        lS.clear();gui.objfrmRoutePointSelector.jTable3.removeAll();  }
                                    
                                }catch(Exception E){}
                            }
                            
                            
                            
                            
                            
                            if( params[0].equals("s+") )  {
                                if (  params[2].equals("C") ) lk.add(params[1])   ;}
                            if( params[0].equals("s+")  )  {
                                if(params[2].equals("S"))lS.add(params[1])   ;}
                            
                            
                            if( params[0].equals("release"))  {
                                
                                
                                
                                
                                
                                if(params[1].equals("N") ){
                                    
                                }
                                
                                if(params[1].equals("C") ){
                                    TableModel model = new resultTableModel(lk);
                                    
                                    gui.objfrmRoutePointSelector.jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
                                    gui.objfrmRoutePointSelector.jTable1.setModel(model);
                                    gui.objfrmRoutePointSelector.jTable1.revalidate();
                                    gui.objfrmRoutePointSelector.jTable1.getColumnModel().getColumn(0).setWidth(120);
                                    gui.objfrmRoutePointSelector.jTable1.getColumnModel().getColumn(1).setWidth(120);
                                    
                                }
                                
                                if(params[1].equals("S") ){
                                    TableModel model2 = new resultTableModel(lS);
                                    gui.objfrmRoutePointSelector.jTable3.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
                                    gui.objfrmRoutePointSelector.jTable3.setModel(model2);
                                    gui.objfrmRoutePointSelector.jTable3.revalidate();
                                    gui.objfrmRoutePointSelector.jTable3.getColumnModel().getColumn(0).setWidth(120);
                                    gui.objfrmRoutePointSelector.jTable3.getColumnModel().getColumn(1).setWidth(120);
                                }
                                
                            }
                            
                            
                            
                            if( params[0].equals("a+"))  {
                                listResult.add(params[1]);
                            }
                            if( params[0].equals("astar"))  {
                                
                                
                                if(params[1].equals("started")) {
                                    
                                    listResult.clear();
                                    
                                    
                                }
                                
                                if(params[1].equals("finished")) {
                                    gui.objfrmResults.setVisible(true);
                                    TableModel model3 = new AstarTableModel(listResult);
                                    gui.objfrmResults.tblResult.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
                                    gui.objfrmResults.tblResult.setModel(model3);
                                    resultArray= listResult.toArray() ;
                                    LinkedList fastNodes = new LinkedList();
                                    
                                    fastNode fn= new fastNode();
                                    if(resultArray!=null)
                                        for(int k1=0;k1< resultArray.length;k1++){
                                        String elem = (String) resultArray[k1];
                                        int dRoadID=Integer.valueOf( StringEX.split(elem,'@')[1]).intValue();
                                        String leftside=StringEX.split(elem,'@')[2];
                                        String[] numbers= StringEX.split(leftside,',');
                                        
                                        
                                        fn.Label="";
                                        fn.isChached=true;
                                        fn.zoomLevel=0;
                                        fn.Type=0;
                                        fn.Name=2;
                                        fn.length++;
                                        float  X= Float.valueOf( numbers[0]  ).floatValue();
                                        float Y= Float.valueOf(numbers[1] ).floatValue();
                                        
                                        fn.chachedPoints.add(new Cpoint(X,Y) );
                                        
                                        
                                        
                                        }
                                    
                                    fastNodes.add(fn);
                                    pr.println(fn.chachedPoints.size());
                                    
                                    Route= new loadedFilesNode("Route",fastNodes);
                                    
                                    
                                    
                                    
                                }
                                
                            }
                            
                            
                            if( params[0].equals("l+"))  {
                                
                                
                                if(params[2].equals("C"))
                                    gui.objfrmRoutePointSelector.cmbCategory.addItem(params[1]);
                                if(params[2].equals("S"))
                                    gui.objfrmRoutePointSelector.cmbStreet.addItem(params[1]);
                                if(params[2].equals("N"))
                                    gui.objfrmNearestPOI.cmbCat.addItem(params[1]);
                                
                            }
                            
                            
                        }
                    }catch(Exception E){  E.printStackTrace();connected=false;   }
                    
                    
                }
                
            }
            ).start();
            
        }catch(Exception Ex){
            connected=false;
        }
        
    }
    
}
