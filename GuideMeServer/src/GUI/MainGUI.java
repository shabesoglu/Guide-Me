package GUI;
/*
 * MainGUI.java
 *
 * Created on November 17, 2007, 4:03 AM
 */

import Server.Server;
import Helper.helper;
import astarnet.Graph;
import astarnet.Node;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author  sahin
 */
public class MainGUI extends javax.swing.JFrame {
   public  mapFile   mapFileObject = new mapFile(this);
    public Graph AstarGraph;
        public frmTracker wndTracker = new frmTracker(this);
    public MainGUI() {
        initComponents();
        helper.setDebug(this.jTextArea1);
    }
    
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jButton7 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnStartServer = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblResult = new javax.swing.JTable();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemReadPolish = new javax.swing.JMenuItem();
        itemBufferAll = new javax.swing.JMenuItem();
        itemMapReader = new javax.swing.JMenuItem();
        itemConvert = new javax.swing.JMenuItem();
        itemConvertAll = new javax.swing.JMenuItem();
        itemWriteOutTxt = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemStartServer = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GuideME Server by Sahin Habesoglu");
        jButton7.setText("find boundaries");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jButton1.setText("Read Polish");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jToolBar1.add(jButton1);

        jButton8.setText("Astar Initialize");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        jToolBar1.add(jButton8);

        jButton4.setText("Buffer All");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jToolBar1.add(jButton4);

        jButton5.setText("map reader");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jToolBar1.add(jButton5);

        jButton3.setText("convert");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jToolBar1.add(jButton3);

        btnStartServer.setText("Start Server");
        btnStartServer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStartServerMouseClicked(evt);
            }
        });

        jToolBar1.add(btnStartServer);

        jButton6.setText("convert all");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jToolBar1.add(jButton6);

        jButton2.setText("Tracker");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jToolBar1.add(jButton2);

        jLabel1.setText("||");
        jToolBar1.add(jLabel1);

        jSplitPane1.setDividerLocation(360);
        jSplitPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSplitPane2.setDividerLocation(189);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane3.setViewportView(jTextArea1);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 355, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 9, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jSplitPane2.setRightComponent(jPanel1);

        jTree1.setAutoscrolls(true);
        jTree1.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        jTree1.setShowsRootHandles(true);
        jScrollPane4.setViewportView(jTree1);

        jSplitPane2.setTopComponent(jScrollPane4);

        jSplitPane1.setLeftComponent(jSplitPane2);

        tblResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblResult.setDoubleBuffered(true);
        jScrollPane2.setViewportView(tblResult);

        jSplitPane1.setRightComponent(jScrollPane2);

        jMenu1.setText("Map operations");
        itemReadPolish.setText("Read Polish");
        itemReadPolish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReadPolishActionPerformed(evt);
            }
        });

        jMenu1.add(itemReadPolish);

        itemBufferAll.setText("Buffer All");
        itemBufferAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBufferAllActionPerformed(evt);
            }
        });

        jMenu1.add(itemBufferAll);

        itemMapReader.setText("Map reader");
        itemMapReader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMapReaderActionPerformed(evt);
            }
        });

        jMenu1.add(itemMapReader);

        itemConvert.setText("Convert");
        itemConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConvertActionPerformed(evt);
            }
        });

        jMenu1.add(itemConvert);

        itemConvertAll.setText("Convert all");
        itemConvertAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConvertAllActionPerformed(evt);
            }
        });

        jMenu1.add(itemConvertAll);

        itemWriteOutTxt.setText("Write Out.txt");
        itemWriteOutTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemWriteOutTxtActionPerformed(evt);
            }
        });

        jMenu1.add(itemWriteOutTxt);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Server");
        itemStartServer.setText("Start server");
        itemStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemStartServerActionPerformed(evt);
            }
        });

        jMenu2.add(itemStartServer);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(14, 14, 14)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jProgressBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .add(jProgressBar2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)))
                    .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jProgressBar2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(jButton7)
                        .add(525, 525, 525))
                    .add(layout.createSequentialGroup()
                        .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        AstarGraph=       mapFileObject.initializeAstarGraph();
    }//GEN-LAST:event_jButton8MouseClicked
    
    private void itemWriteOutTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemWriteOutTxtActionPerformed
        try {
            
            mapFileObject.writeOutTxt("C:\\vmaps\\rmap.mp");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_itemWriteOutTxtActionPerformed
    
    private void itemStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemStartServerActionPerformed
// TODO add your handling code here:
        Server.Start(this);
    }//GEN-LAST:event_itemStartServerActionPerformed
    
    private void itemConvertAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConvertAllActionPerformed
// TODO add your handling code here:
        mapFileObject.convertAll();
    }//GEN-LAST:event_itemConvertAllActionPerformed
    
    private void itemConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConvertActionPerformed
// TODO add your handling code here:
        JFileChooser dummyfileChooser = new JFileChooser();
        dummyfileChooser.showOpenDialog(this);
        
        if (!dummyfileChooser.getSelectedFile().exists()){
            mapFileObject.convert(dummyfileChooser.getSelectedFile().getAbsoluteFile().toString().split(".")[0] );
            
            JOptionPane.showMessageDialog(null,"Finished");
        }
    }//GEN-LAST:event_itemConvertActionPerformed
    
    private void itemMapReaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMapReaderActionPerformed
// TODO add your handling code here:
//        mapFileObject.readGuideMeMapFormatFromMemory();
    }//GEN-LAST:event_itemMapReaderActionPerformed
    
    private void itemBufferAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBufferAllActionPerformed
// TODO add your handling code here:
        mapFileObject.bufferAll();
    }//GEN-LAST:event_itemBufferAllActionPerformed
    
    private void itemReadPolishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReadPolishActionPerformed
// TODO add your handling code here:
        String  fname = "C:\\vmaps\\map.mp";
        if ((new File(fname) ).exists() )
            mapFileObject.initialize(fname,true);
    }//GEN-LAST:event_itemReadPolishActionPerformed
    
    private void btnStartServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartServerMouseClicked
        
        Server.Start(this);
        
        
    }//GEN-LAST:event_btnStartServerMouseClicked
    
    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
// TODO add your handling code here:
//        mapFileObject.findBoundaries();
    }//GEN-LAST:event_jButton7MouseClicked
    
    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        mapFileObject.convertAll();
        
    }//GEN-LAST:event_jButton6MouseClicked
    
    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
// TODO add your handling code here:
        //mapFileObject = new mapFile(5221, 1025);
//        mapFileObject.readGuideMeMapFormatFromMemory();
    }//GEN-LAST:event_jButton5MouseClicked
    
    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        
// TODO add your handling code here:
        mapFileObject.bufferAll();
        
    }//GEN-LAST:event_jButton4MouseClicked
    
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        
        JFileChooser dummyfileChooser = new JFileChooser();
        dummyfileChooser.showOpenDialog(this);
        
        if (!dummyfileChooser.getSelectedFile().exists()){
            mapFileObject.convert(dummyfileChooser.getSelectedFile().getAbsoluteFile().toString().split(".")[0] );
            
            JOptionPane.showMessageDialog(null,"Finished");
        }
        
    }//GEN-LAST:event_jButton3MouseClicked
    
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
// TODO add your handling code here:
        wndTracker.setVisible(true);
        //jTextArea1.getGraphics().drawString("kljlkj",20,20);
        
    }//GEN-LAST:event_jButton2MouseClicked
    
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
// TODO add your handling code here:
        
        //JFileChooser dummyfileChooser = new JFileChooser();
        //dummyfileChooser.showOpenDialog(this);
        //String fname=dummyfileChooser.getSelectedFile().getAbsoluteFile().toString();
        String  fname = "C:\\vmaps\\rmap.mp";
        if ((new File(fname) ).exists() )
            mapFileObject.initialize(fname,true);
        
    }//GEN-LAST:event_jButton1MouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartServer;
    private javax.swing.JMenuItem itemBufferAll;
    private javax.swing.JMenuItem itemConvert;
    private javax.swing.JMenuItem itemConvertAll;
    private javax.swing.JMenuItem itemMapReader;
    private javax.swing.JMenuItem itemReadPolish;
    private javax.swing.JMenuItem itemStartServer;
    private javax.swing.JMenuItem itemWriteOutTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JProgressBar jProgressBar1;
    public javax.swing.JProgressBar jProgressBar2;
    public javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JSplitPane jSplitPane1;
    public javax.swing.JSplitPane jSplitPane2;
    public javax.swing.JTextArea jTextArea1;
    private javax.swing.JToolBar jToolBar1;
    public javax.swing.JTree jTree1;
    public javax.swing.JTable tblResult;
    // End of variables declaration//GEN-END:variables
    
}
