package GuideME;
import java.util.Vector;
/*
 * StringEX.java
 *
 * Created on November 23, 2007, 1:50 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author sahin
 */
public   class StringEX{
    
    public final static String[] split( String str, char separatorChar ) {
        if ( str == null ) {
            return null;
        }
        int       len    = str.length();
        if ( len == 0 ) {
            return null;
        }
        Vector    list   = new Vector();
        int       i      = 0;
        int       start  = 0;
        boolean   match  = false;
        while ( i < len ) {
            if ( str.charAt( i ) == separatorChar ) {
                if ( match ) {
                    list.addElement( str.substring( start, i ).trim() );
                    match = false;
                }
                start = ++i;
                continue;
            }
            match = true;
            i++;
        }
        if ( match ) {
            list.addElement( str.substring( start, i ).trim() );
        }
        String[]  arr    = new String[list.size()];
        list.copyInto( arr );
        return arr;
    }
    
    
    
}
