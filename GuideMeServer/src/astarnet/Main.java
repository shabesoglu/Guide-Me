/*
 * Main.java
 *
 * Created on December 21, 2007, 1:52 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package astarnet;

import java.awt.Polygon;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author sahin
 */
public class Main {
    
    /** Creates a new instance of Main */
//    public Main() {
//    }
//    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        
//        DataInputStream SR;
//        Graph G = new Graph();
//        
//        Node[] nodeArr = new Node[200000];
//        String S;
//        try {
//            FileInputStream fs = new FileInputStream("c:\\2\\out2.txt");
//            SR = new DataInputStream(fs);
//            
//            while (SR.available() != 0 ) {
//                S = SR.readLine();
//                
//                String[] nodeParam = S.split("/")[0].split("\\*")[1].split(",");
//                String[] leafNodes = S.split("/")[1].split("\\$")[0].split("-");
//                String weight = S.split("/")[1].split("\\$")[1];
//                double x = Double.parseDouble(nodeParam[0]);
//                double y = Double.parseDouble(nodeParam[1]);
//                
//                int shouldAddIndexMain = Integer.parseInt(S.split("/")[0].split("\\*")[0]);
//                if (nodeArr[shouldAddIndexMain] == null){
//                    //nodeArr[shouldAddIndexMain] = new Node(x, y, 0);
//                    
//                    
//                    G.AddNode(nodeArr[shouldAddIndexMain]);
//                }
//                
//                Node tempNode = nodeArr[shouldAddIndexMain];
//                
//                for (int k = 0; k < leafNodes.length ; k++) {
//                    String [] parsed=leafNodes[k].split("\\*");
//                    int shouldAddIndex = Integer.parseInt(parsed[0]);
//                    if (nodeArr[shouldAddIndex] == null){
//                   //     nodeArr[shouldAddIndex] = new Node(Double.parseDouble(parsed[1].split(",")[0]), Double.parseDouble(parsed[1].split(",")[1]), 0);
//                        
//                        G.AddNode(nodeArr[shouldAddIndex]);
//                    }
//                    
//                    Node TempLeaf = nodeArr[shouldAddIndex];
//                    
//                    if (!tempNode.ToString().equals(TempLeaf.ToString()))
//                        G.AddArc(tempNode, TempLeaf, Integer.parseInt(weight)*Integer.parseInt(weight) );
//                    
//                }
//                
//            }
//            SR.close();
//        } catch (NumberFormatException ex) {
//            ex.printStackTrace();
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        
//        
//        
//        System.out.println(G.getNodes().size());
//        long start = System.currentTimeMillis();
//        
//        Node startNode = G.getNodeByCoordinate(41.15693,28.23448);
//        Node endNode = G.getNodeByCoordinate(40.52466,30.32149);
//        
//        AStar astar= new AStar(G);
//        
//        
//        double length=0;
//        astar.SearchPath(startNode,endNode);
//        
//        for (Arc A : astar.PathByArcs()) {
//            length += A.getLength();
//            
//            System.out.println(A.ToString());
//        }
//        System.out.println(length);
//        
//        
//        long elapsedTimeMillis = System.currentTimeMillis()-start;
//        System.out.println(elapsedTimeMillis);
//    }
    
}
