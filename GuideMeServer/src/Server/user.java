package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

        

public class user {
    
    PrintWriter out = null;
    String userName = null;

    public user(PrintWriter out, String pUserName) {
       
        this.userName=pUserName;
        this.out=out;
        
    }
    
    public void sendMsg(String message)  {
        out.println(message);
        out.flush();
    }
}
