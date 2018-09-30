/*
 * Equality.java
 *
 * Created on December 23, 2007, 11:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package astarnet;

/**
 *
 * @author sahin
 */
public class Equality{
    
    boolean AreEqual(Object O1, Object O2){
      
            Track P1 = (Track)O1 ;
            Track P2 = (Track)O2 ;
        
            return P1.EndNode == P2.EndNode;
       
    
    }
    
}