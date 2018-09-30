package Server;
import GUI.MainGUI;
import GUI.SlowNode;
import GUI.frmTracker;
import Helper.PointF;
import Helper.helper;
import astarnet.AStar;
import astarnet.Arc;
import astarnet.Node;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JButton;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;




public class Connect extends Thread  {
    
    
    LinkedList usrs = new LinkedList();
    ServerSocket myServerSocket;
    Socket newPerson;
    MainGUI parent;
    
    Connect(int port,MainGUI pParent) {
        this.parent=pParent;
        try {
            myServerSocket = new ServerSocket(port);
        } catch (Exception ex) {
            helper.zout("Error:" + ex.getMessage() );
        }
        
        while(true)  {
            try {
                newPerson = myServerSocket.accept();
                UserThread myNUT = new UserThread(newPerson);
                myNUT.start();
            usrs.add(myNUT);
            
            } catch (Exception ex) {
                helper.zout("Error:" + ex.getMessage() );
                break;
            }
        }
        
    }
    
    
    class UserThread extends Thread {
        
        InputStream is;
        PrintWriter pr;
        String userName;
        PointF position= new PointF(0,0);
        
        private user userClass;
        
        public UserThread(Socket skt) {
            
            try {
                pr= new PrintWriter(skt.getOutputStream(),true);
                is = skt.getInputStream();
                
                
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
            } catch (IOException ex) {
                helper.zout("User Out" );
            }
            
        }
        
        public String toString(){
            
            return position.toString();
        }
        synchronized public void run() {
            try {
                
                String osm = null;
                helper.zout("New user connected..." );
                parent.wndTracker.lstUsers.setListData(usrs.toArray());
                 
                pr.flush();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                
                while (true) {
                    
                    osm = br.readLine();
                    
                    String token = osm.split(":")[0].toLowerCase();
                    String [] params = osm.split(":");
                 helper.zout(osm );   
                    
                    try{
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        
                        if ( token.equals( "near" ) )  {
                            TreeNode dummyNode=    helper.findByName(parent.jTree1,new String[] {"Categories","POI",params[1]},false  );
                            double PosX =Double.parseDouble( params[2].split(",")[0]);
                            double PosY =Double.parseDouble( params[2].split(",")[1]);
                            
                            Enumeration en= dummyNode.children();
                            double min=+99999999;
                            SlowNode found=null;
                            while (en.hasMoreElements() ) {
                                
                                Object  o =((DefaultMutableTreeNode) en.nextElement()) .getUserObject(); ;
                                
                                SlowNode  item=(SlowNode) o;
                                PointF pos= (PointF) item.ppoint().get(0);
                                
                                Double  euqlidianDistance = Math.sqrt( Math.pow( pos.getX()-PosX,2)  + Math.pow((pos.getY()-PosY),2)  );
                                
                                if (euqlidianDistance<min) {
                                    min= euqlidianDistance;
                                    found=item;
                                }
                                
                                
                                
                            }
                            
                            
                            pr.println("nearest:" + found.pLabel+":"+found.ppoint().get(0).toString() );
                            pr.flush();
                            
                        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        if ( token.equals( "pos" ) )  {
                            
                            position.setY(Double.parseDouble(params[1].split(",")[0]));
                            position.setX(Double.parseDouble(params[1].split(",")[1]));
                            
                         PointF dpoint=helper.coordinateToPoint(position,parent.mapFileObject.BzoomLevelValue,parent.mapFileObject.currentFileCoordinate);
                         parent.wndTracker.reDraw();
                         Graphics g= parent.wndTracker.jPanel1.getGraphics() ;
                         g.setColor(Color.RED);
                         g.fillOval(dpoint.getiX()+parent.wndTracker.posx,dpoint.getiY()+parent.wndTracker.posy,10,10);
                         g.drawString("+",  dpoint.getiX()+parent.wndTracker.posx,dpoint.getiY()+parent.wndTracker.posy);
                            
                         parent.wndTracker.lstUsers.setListData(usrs.toArray());
                        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        if ( token.equals( "ss" ) )  {
                            
                            TreeNode dummyNode=    helper.findByName(parent.jTree1,params[1].split("\\$"),false  );
                            
                            Enumeration en= dummyNode.children();
                            
                            pr.println("clrsearchlist:ss");
                            
                            if (dummyNode.getChildCount()==0 ) {
                                
                                Object  o =((DefaultMutableTreeNode) dummyNode) .getUserObject(); ;
                                
                                if( o.getClass()==SlowNode.class ){
                                    SlowNode  item=(SlowNode) o;
                                    pr.println("s+:"+ item.toString()+"@"+ item.ppoint().get(0).toString()+":"+params[2] );
                                    
                                } else{
                                    
                                    pr.println("s+:"+ o.toString()+":"+params[2]);
                                    
                                }
                                
                            }
                            
                            while (en.hasMoreElements() ) {
                                Object  o =((DefaultMutableTreeNode) en.nextElement()).getUserObject() ;
                                
                                if( o.getClass()==SlowNode.class ){
                                    SlowNode  item=(SlowNode) o;
                                    pr.println("s+:"+ item.toString()+"@"+ item.ppoint().get(0).toString()+":"+params[2] );
                                    
                                } else{
                                    
                                    pr.println("s+:"+ o.toString()+":"+params[2]);
                                    
                                }
                                
                                
                            }
                            
                            pr.println("release"+":"+params[2]);
                            pr.flush();
                        }
                        //////////////////////////////////////////////////////////////////
                        if ( token.equals( "astar" ) )  {
                            final JButton dmyBtn =  new JButton();
                            dmyBtn.setText("Cancel");
                            
                            final AStar dummyAStar= new AStar(parent.AstarGraph);
                            parent.jPanel3.setLayout(new  FlowLayout(FlowLayout.LEFT));
                            parent.jPanel3.add(dmyBtn);
                            parent.jPanel3.updateUI();
                            
                            dmyBtn.addMouseListener(new MouseListener() {
                                public void mouseClicked(MouseEvent e) {
                                    pr.println("astar:Canceled");
                                    parent.jPanel3.remove(dmyBtn);
                                    parent.jPanel3.updateUI();
                                    dummyAStar.exitSign=true;
                                }
                                public void mouseEntered(MouseEvent e) {
                                }
                                public void mouseExited(MouseEvent e) {
                                }
                                public void mousePressed(MouseEvent e) {
                                }
                                public void mouseReleased(MouseEvent e) {
                                }
                            });
                            
                            pr.println("astar:started");
                            //Node startNode = parent.AstarGraph.getNodeByCoordinate(astar:41.29417:27.77618:40.52466:30.32149);
                            //Node endNode = parent.AstarGraph.getNodeByCoordinate(40.52466,30.32149);
                            double snx=Double.parseDouble(params[1]);
                            double sny=Double.parseDouble(params[2]);
                            double enx=Double.parseDouble(params[3]);
                            double eny=Double.parseDouble(params[4]);
                            
                            Node startNode = parent.AstarGraph.ClosestNode(snx,sny,0);
                            Node endNode = parent.AstarGraph.ClosestNode(enx,eny,0);
                            pr.println("astar:ClosesStartNode:"+startNode.ToString());
                            pr.println("astar:ClosesEndNode:"+endNode.ToString());
                            double length=0;
                            dummyAStar.SearchPath(startNode,endNode);
                            
                            for (Arc A : dummyAStar.PathByArcs()) {
                                pr.println("a+:" + A.getEndNode()._Name +"@"+ A.getStartNode()._roadID+"@"+A.getEndNode().getPosition().ToString() );
                            }
                            
                            pr.println("astar:finished");
                            
                            parent.jPanel3.remove(dmyBtn);
                            parent.jPanel3.updateUI();
                        }
                        
                        //////////////////////////////////////////////////////////////////
                        if ( token.equals( "list" ) )  {
                            
                            TreeNode dummyNode=    helper.findByName(parent.jTree1,params[1].split("\\$"),true  );
                            pr.println("clrsearchlist:list");
                            Enumeration en= dummyNode.children();
                            while (en.hasMoreElements()) {
                                Object  o =((DefaultMutableTreeNode) en.nextElement()).getUserObject() ;
                                
                                if( o.getClass()==SlowNode.class ){
                                    SlowNode  item=(SlowNode) o;
                                    
                                    pr.println("l+:"+ item.toString()+"@"+ item.ppoint().toString()+":"+   params[2] );
                                    
                                    
                                } else{
                                    
                                    pr.println("l+:"+ o.toString()+":"+params[2] );
                                    
                                }
                                
                                
                            }
                            
                            pr.println("release"+":"+params[2]);
                            pr.flush();
                        }
                        
                        //////////////////////////////////////////////////////////////////
                        
                        
                        if ( token.equals( "u" ) )  {
                            userName = osm.split(":")[1].toLowerCase();
                            
                            //System.out.println(nickname);
                            helper.zout("New user registered..." );
                            pr.println("Welcome User:" + userName);
                            pr.println("ok");
                            pr.flush();
                            
                            int bul=0;
                            
                            for ( int y = 0 ; y < usrs.size() ; y++ )  {
                                if (((user) usrs.get(y)).userName.equals(userName))
                                    bul=1;
                                
                            }
                            
                            if(bul==0){
                                userClass = new user(pr,userName);
                                
                                usrs.add(userClass);
                            }
                            
                            pr.flush();
                            
                        }
                        //////////////////////////////////////////////////////////////////
                        
                       
                    } catch(Exception ex){
                        pr.println(ex.getMessage());
                    }
                    
                }
                
            } catch (Exception e) {
             
                
                helper.zout("User disconnected...");
                pr.println(e.getMessage());
                        usrs.remove(this);
                    
                parent.wndTracker.lstUsers.setListData(usrs.toArray());
                
                
            }
            
        }
        
        
    }
}







