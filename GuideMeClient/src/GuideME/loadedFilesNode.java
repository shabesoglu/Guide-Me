/*
 * loadedFilesNode.java
 *
 * Created on January 13, 2008, 3:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package GuideME;

import java.util.ArrayList;
import java.util.LinkedList;
// <editor-fold defaultstate="collapsed" desc=" LoadedFileNode ">
public class loadedFilesNode{
    String fileName = "";
    boolean New=true;
    public LinkedList  nodesArray ;
    
    public loadedFilesNode(String pFileName,LinkedList  pNodesArray){
        fileName=pFileName;
        nodesArray=pNodesArray;
        
    }
    
    public boolean equals(Object o) {
        
        
        return ((loadedFilesNode) o).fileName.equals(fileName);
        
    }
    
    public String toString() {
        
        return fileName ;
    }
    
    void releaseMemory(ArrayList arList){
       
        arList.remove(this);
        if (fileName.equals("Route")) return ;
        try {
            nodesArray.clear();
            this.finalize();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        
    }
}// </editor-fold>