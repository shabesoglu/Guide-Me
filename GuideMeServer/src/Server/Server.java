package Server;

import GUI.MainGUI;
import Helper.helper;
public class Server  {
    
 
    static public    void Start(MainGUI pParent )  {
    final  MainGUI parent = pParent;
        
        (new Thread(){
            public void run() {
                
                
                helper.zout("GuideME Server is running on port 666...");
                Connect sc = new Connect(666,parent);
                sc.run();
                
            }}).start();
            
            
    }
}