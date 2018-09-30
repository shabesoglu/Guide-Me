package GuideME;
/*
 * Main.java
 *
 * Created on November 23, 2007, 1:35 AM
 */

/**
 *
 * @author  sahin
 */
import Client.Client;
import Helper.PointF;
import Helper.helper;
import Helper.helper;
import RouteSearch.frmNearestPOI;
//import RouteSearch.frmNearestPOI2;
import RouteSearch.frmResults;
import RouteSearch.frmRoutePointSelector;
import RouteSearch.frmRouteSearch;

import java.awt.Button;
import java.awt.Color;


//import creme.AWT;
//import java.awt.Canvas;


import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.MenuItem;
import java.awt.Polygon;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import javax.comm.CommPort;
import javax.comm.CommPortIdentifier;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

import javax.swing.ComboBoxEditor;
//import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.PopupMenuUI;


public class Main extends javax.swing.JFrame {
    static {
        try {
            //     javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch(Exception exception) {
            System.out.println("Error loading L&F: " + exception);
        }
    }
    
    int clickX=0;
    int clickY=0;
  
    public Client client = new Client(this);
    public  mapFile   mapFileObject= new   mapFile(this);
    
    final public  frmRouteSearch objfrmRouteSearch=new frmRouteSearch(this);
    final public frmResults objfrmResults = new frmResults(this);
    final public frmNearestPOI objfrmNearestPOI = new frmNearestPOI(this);
    final public frmRoutePointSelector objfrmRoutePointSelector=  new frmRoutePointSelector(this);
    
//    final public  frmRouteSearch objfrmRouteSearch=null;
//    final public frmResults objfrmResults = null;
//    final public frmRoutePointSelector objfrmRoutePointSelector=  null;
    Thread drawThread;
    public Main() {
        
        
//        System.out.println(helper.coordinateToFileName(new PointF(41.06758,29.06914)));
//System.exit(0);
        initComponents();
        
        
        mapFileObject.gImage= createImage(240,240);
//        mapFileObject.gImage2= createImage(240,240);
        
        mapFileObject.g= mapFileObject.gImage.getGraphics();
        mapFileObject.setPosition(40.96000,29.12000);
        
        drawThread=  new Thread(){
            public void run(){
                while(true){
                    tick=true;
//                   if(mapFileObject.swapframe) 
//                    jPanel2.getGraphics().drawImage(mapFileObject.gImage2,0,0,null);
//                   else
                  creme.AWT.keyboardHide();
                    

               Graphics g=  jPanel2.getGraphics();
                g.drawImage(mapFileObject.gImage,0,0,null);
                g.setColor(Color.RED);
                g.drawRect(mapFileObject.trackPointToMap().getiX()-5,mapFileObject.trackPointToMap().getiY()-5,10,10);
                      
                //  jPanel2.getGraphics().drawImage(mapFileObject.gImage,0,0,null);
                         jPanel2.getGraphics().finalize();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        
                    }
                }
            }
        };
        
        drawThread.start();
        
        
        
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnuExit = new javax.swing.JMenuItem();
        mnuConnect = new javax.swing.JMenuItem();
        mnuNearestPOI = new javax.swing.JMenuItem();
        mnuResults = new javax.swing.JMenuItem();
        mnuRoute = new javax.swing.JMenu();
        mnuFrom = new javax.swing.JMenuItem();
        mnuTo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        mnuRouteSearch = new javax.swing.JMenuItem();
        mnuGPS = new javax.swing.JMenuItem();
        mnuTrack = new javax.swing.JCheckBoxMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();

        jPopupMenu1.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(51, 51, 51)));
        jPopupMenu1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jPopupMenu1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jPopupMenu1PopupMenuWillBecomeVisible(evt);
            }
        });

        mnuExit.setText("Exit");
        mnuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuExitMouseClicked(evt);
            }
        });
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });

        jPopupMenu1.add(mnuExit);

        mnuConnect.setLabel("Connect");
        mnuConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConnectActionPerformed(evt);
            }
        });

        jPopupMenu1.add(mnuConnect);

        mnuNearestPOI.setLabel("Nearest POI");
        mnuNearestPOI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNearestPOIActionPerformed(evt);
            }
        });

        jPopupMenu1.add(mnuNearestPOI);

        mnuResults.setLabel("Results");
        mnuResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuResultsActionPerformed(evt);
            }
        });

        jPopupMenu1.add(mnuResults);

        mnuRoute.setText("Route Menu");
        mnuRoute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRouteActionPerformed(evt);
            }
        });

        mnuFrom.setLabel("From");
        mnuFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFromActionPerformed(evt);
            }
        });

        mnuRoute.add(mnuFrom);

        mnuTo.setLabel("To");
        mnuTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuToActionPerformed(evt);
            }
        });

        mnuRoute.add(mnuTo);

        mnuRoute.add(jSeparator1);

        mnuRouteSearch.setLabel("Route Search");
        mnuRouteSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRouteSearchActionPerformed(evt);
            }
        });

        mnuRoute.add(mnuRouteSearch);

        jPopupMenu1.add(mnuRoute);

        mnuGPS.setLabel("Start GPS");
        mnuGPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGPSActionPerformed(evt);
            }
        });

        jPopupMenu1.add(mnuGPS);

        mnuTrack.setText("Track GPS");
        mnuTrack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTrackActionPerformed(evt);
            }
        });

        jPopupMenu1.add(mnuTrack);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("GuideME");
        setBounds(new java.awt.Rectangle(0, 0, 240, 240));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 12));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel2MouseReleased(evt);
            }
        });
        jPanel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel2ComponentShown(evt);
            }
        });
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel2MouseMoved(evt);
            }
        });

        jToggleButton1.setText("jToggleButton1");
        jToggleButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButton1MouseClicked(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jToggleButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(216, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jToggleButton1)
                .addContainerGap(217, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    boolean track;
    private void mnuTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTrackActionPerformed
track= mnuTrack.getState();
    }//GEN-LAST:event_mnuTrackActionPerformed

    private void jPopupMenu1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jPopupMenu1PopupMenuWillBecomeInvisible

        creme.AWT.enableTapAndHoldEvent(1000);
        drawThread.resume();
    }//GEN-LAST:event_jPopupMenu1PopupMenuWillBecomeInvisible

    private void jPopupMenu1PopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jPopupMenu1PopupMenuWillBecomeVisible

        creme.AWT.disableTapAndHoldEvent();
drawThread.suspend();
    }//GEN-LAST:event_jPopupMenu1PopupMenuWillBecomeVisible
    
    private void mnuNearestPOIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNearestPOIActionPerformed
        client.send("list:Categories$POI:N");
        objfrmNearestPOI.setVisible(true);
        mapFileObject.draw();
    }//GEN-LAST:event_mnuNearestPOIActionPerformed
    
    private void mnuExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuExitMouseClicked
        
    }//GEN-LAST:event_mnuExitMouseClicked
    
    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuExitActionPerformed
    
    private void mnuConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConnectActionPerformed
        client.connect("10.2.12.126",666);
        //drawThread.resume();
    }//GEN-LAST:event_mnuConnectActionPerformed
    
    private void mnuResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuResultsActionPerformed
        objfrmResults.setVisible(true);
    }//GEN-LAST:event_mnuResultsActionPerformed
    
    private void mnuFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFromActionPerformed
        mapFileObject.startPoint.setX(mapFileObject.currentFileCoordinate.getX() -(double)clickY*mapFileObject.gettZoomAreaFactor());
        mapFileObject.startPoint.setY(mapFileObject.currentFileCoordinate.getY() +(double)clickX*mapFileObject.gettZoomAreaFactor());
        objfrmRouteSearch.txtFrom.setText( String.valueOf( mapFileObject.startPoint.getX()).substring(0,8) +" "+ String.valueOf( mapFileObject.startPoint.getY()).substring(0,8));
        mapFileObject.draw();
        //drawThread.resume();
    }//GEN-LAST:event_mnuFromActionPerformed
    
    private void mnuToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuToActionPerformed
        mapFileObject.endPoint.setX(mapFileObject.currentFileCoordinate.getX() -(double)clickY*mapFileObject.gettZoomAreaFactor());
        mapFileObject.endPoint.setY(mapFileObject.currentFileCoordinate.getY() +(double)clickX*mapFileObject.gettZoomAreaFactor());
        objfrmRouteSearch.txtTo.setText( String.valueOf( mapFileObject.endPoint.getX()).substring(0,8) +" "+ String.valueOf( mapFileObject.endPoint.getY()).substring(0,8));
        mapFileObject.draw();
        //drawThread.resume();
    }//GEN-LAST:event_mnuToActionPerformed
    
    private void mnuRouteSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRouteSearchActionPerformed
        objfrmRouteSearch.setVisible(true);
mapFileObject.draw();

        //drawThread.resume();
    }//GEN-LAST:event_mnuRouteSearchActionPerformed
    
    private void mnuRouteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRouteActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_mnuRouteActionPerformed
    
    private void mnuGPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGPSActionPerformed
        startGPS();
        //drawThread.resume();
    }//GEN-LAST:event_mnuGPSActionPerformed
    
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed


    }//GEN-LAST:event_jToggleButton1ActionPerformed
    
    private void jToggleButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton1MouseClicked

        jPopupMenu1.show(jPanel2,2,2);        

    }//GEN-LAST:event_jToggleButton1MouseClicked
    
    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
drawThread.resume();  
        if (evt.isPopupTrigger()) {
            jPopupMenu1.show(this,evt.getX(),evt.getY());
        
            //creme.AWT.disableTapAndHoldEvent();
        }
    }//GEN-LAST:event_jPanel2MouseClicked
    
    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
creme.AWT.disableTapAndHoldEvent();

track=false;
        mnuTrack.setState(false);
        int x= evt.getX();
        int y= evt.getY();
        
        
        if(oldY==0)
            oldY=y;
        if(oldX==0)
            oldX=x;
        
        
        
        
        if (x>mapFileObject.showArea-20 && x <= mapFileObject.showArea  ) {
            if(tick){
                mapFileObject.setZoomLevel((y-oldY)*3 );
                mapFileObject.draw();
                
                tick=false;
            }
        }
        
        
        if (x<mapFileObject.showArea-20 ) {
            if(tick){
                mapFileObject.setPositionDelta((double)(y-oldY)*mapFileObject.gettZoomAreaFactor() *2 ,(double)(oldX-x)*mapFileObject.gettZoomAreaFactor() *2);
                
                mapFileObject.draw();
                
                tick=false;
            }
        }
        
        oldX=x;
        oldY=y;
        
        
        
        creme.AWT.enableTapAndHoldEvent(1000);        
        
        
        
        
        // jPanel2.getGraphics().drawString(String.valueOf(button), 30,30);
    }//GEN-LAST:event_jPanel2MouseDragged
    
    private void jPanel2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseMoved
        
    }//GEN-LAST:event_jPanel2MouseMoved
    
    private void jPanel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseReleased
        
    }//GEN-LAST:event_jPanel2MouseReleased
    
    private void jPanel2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel2ComponentShown
        
    }//GEN-LAST:event_jPanel2ComponentShown
    
    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed

            oldY= evt.getY();
            oldX= evt.getX();
            
            clickX=oldX;
            clickY=oldY;
    }//GEN-LAST:event_jPanel2MousePressed
    boolean tick=true;
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        mapFileObject.draw();
        
        
    }//GEN-LAST:event_formComponentShown
    
    
    void startGPS() {
//        System.setProperty("serial.ports", "COM1:,COM2:"); 
//        System.setProperty("java.library.path", System.getProperty("java.home") + File.separator + "bin" );
//        JOptionPane.showMessageDialog(null,System.getProperty("java.library.path") );
        (new Thread(){
            public void run() {
                OutputStream cos;
                BufferedReader  cis;
                CommPort port;
                CommPortIdentifier cpi = null;
                for (Enumeration e = CommPortIdentifier.getPortIdentifiers() ; e.hasMoreElements() ;) {
                    CommPortIdentifier tmp = (CommPortIdentifier)e.nextElement();
                   
                    if (tmp.getName().equals("COM2:")) {
                        cpi = tmp;
                        break;
                    }
                }
                if (cpi == null) {
                    
                    JOptionPane.showMessageDialog(null,"Unable to find any COM ports.");
                    return;
                } else {
                    
                }
//                int c =0;
                try {
                    port = cpi.open("gps", 10000);
                    
                    
                    cos = port.getOutputStream();
                    
                    cis =new  BufferedReader(new InputStreamReader( port.getInputStream()));
                    // we have now succesfully opened COM1

                    port.enableReceiveTimeout(10);
                    
                    
                    
                    
                    while(true) {
                    String st="";
                        if(port.getInputStream().available()!=0)
                       st = cis.readLine();
                         
                        try {
                            if (!st.equals("")){
                            String Type = GuideME.StringEX.split(st,',')[0];
                            if( Type.compareTo("$GPRMC") == 0 ){
                                double lon = helper.toDecimal(GuideME.StringEX.split(st,',')[3]);
                                double lat = helper.toDecimal(GuideME.StringEX.split(st,',')[5]);
                              //if(client.connected)
                                client.send("pos:"+String.valueOf(lon)+","+String.valueOf(lat));
                             if (track){
                                    mapFileObject.trackPoint= new PointF(lat,lon);
                                    mapFileObject.draw();
                                    
                                mapFileObject.setPosition(lat,lon);
                             }
                             else 
                             {
                                    
                                    mapFileObject.trackPoint= new PointF(lat,lon);
//                                  
//                                   }
                                    
                             }
                            }
                            }
                        } catch(Exception e) {
                           
                            System.out.println(e.getMessage());
                        }
                  
                 Thread.sleep(100);

                        

                        
                    }

                } catch (Throwable e) {

                   
                    return;
                }
            }
            
        }).start();
        
    }
    
    
    int button;
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JMenuItem mnuConnect;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenuItem mnuFrom;
    private javax.swing.JMenuItem mnuGPS;
    private javax.swing.JMenuItem mnuNearestPOI;
    private javax.swing.JMenuItem mnuResults;
    private javax.swing.JMenu mnuRoute;
    private javax.swing.JMenuItem mnuRouteSearch;
    private javax.swing.JMenuItem mnuTo;
    private javax.swing.JCheckBoxMenuItem mnuTrack;
    // End of variables declaration//GEN-END:variables
    
    
    
    private int oldY;
    private int oldX;
}
