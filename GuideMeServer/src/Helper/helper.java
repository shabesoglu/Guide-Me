package Helper;

import java.util.Enumeration;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;


public final  class helper {
    
    private static PointF startCoordinate  = new PointF(42.00000d-0.04d,27.80000d-0.04d);
    
    private static int startFileWest  = 5194;
    private static int startFileEast = 1050;
    
    static public double toDecimal(String Pos){
        
        double PosDb = Double.valueOf(Pos).doubleValue();
        double Deg = Math.floor((double)(PosDb / 100));
        double DecPos = Deg + ((PosDb - (Deg * 100)) / 60);
        
        return DecPos;
        
    }
    
    
    
    public     static void copyUsingClone( int[] aArray , int startIndex, int stopIndex) {
        for ( int idx = 0 ; idx < stopIndex; ++idx ) {
            
            int[] copy = (int[])aArray.clone();
            
        }
    }
    
    public static void cpyArray( byte[] source,byte[] destination, int startIndex, int length) {
        
        System.arraycopy(source,startIndex,destination,0,length);
        
    }
    
    static void   msgbox(String msg){
        JOptionPane.showMessageDialog(null,msg);
    }
    
    static void msgbox(int msg){
        JOptionPane.showMessageDialog(null,String.valueOf(msg));
    }
    
    static void msgbox(Object msg){
        JOptionPane.showMessageDialog(null,String.valueOf(msg));
    }
    
    static void msgbox(long msg){
        JOptionPane.showMessageDialog(null,String.valueOf(msg));
    }
    
    public static PointF coordinateToPoint(PointF pPoint, double zoomLevel,PointF currentFileCoordinate ) {
        
        
        double px=  currentFileCoordinate.getX()-pPoint.getX()  ;
        double py= currentFileCoordinate.getY() - pPoint.getY() ;
        
        return new PointF(- (int)( py *zoomLevel) , (int)(px * zoomLevel) );
        
    }
    
    public static String coordinateToFileName(PointF pPoint) {
        
        
        
        long  px=  (long) (startCoordinate.getX()*1000l) - (long)(pPoint.getX()*1000l ) ;
        long  py=  (long) (pPoint.getY()*1000l) - (long) (startCoordinate.getY() *1000l) ;
        
        
        
//            return "c:\\Storage Card\\mps2\\"+ Integer.toString( (int)Math.round(startFileEast- (px/40))-2 )    + "-" + Integer.toString(  (int)Math.round(startFileWest+ (py/40))  ) ;
        return "c:\\mps2\\"+ Integer.toString( (int)Math.round(startFileEast- (px/40))-2 )    + "-" + Integer.toString(  (int)Math.round(startFileWest+ (py/40))  ) ;
        
    }
    
    public static PointF coordinateToFileStartPosition(PointF pPoint) {
        
        
        double px=  startCoordinate.getX()-pPoint.getX()  ;
        double py= startCoordinate.getY() - pPoint.getY() ;
        
        
        
        int ix =((int) ( (px/0.04) ) )  ;
        int iy=((int) ( (py/0.04) ) )  ;
        
        return new PointF(   startCoordinate.getX()+ ix*0.04 ,startCoordinate.getY()+iy*0.04)  ;
        
        
        
    }
    
    
    public static void msgbox(boolean b) {
        JOptionPane.showMessageDialog(null,String.valueOf(b));
    }
    
    public static String ts(long o){
        return String.valueOf(o);
        
    }
    
    public      static String ts(double o){
        return String.valueOf(o);
        
    }
    
    public  static String ts(float o){
        return String.valueOf(o);
        
    }
    
    public static String ts(int o){
        return String.valueOf(o);
        
    }
    
    public static void setDebug(JTextArea pDebug){
        debug=pDebug;
        
        
    }
    
    public  static JTextArea debug;
    public static void  zout(String Message){
        
        debug.setText(debug.getText()+"\n"+Message);
        
        
    }
    
    public static TreeNode find(JTree tree, Object[] nodes,boolean exactWord) {
        TreeNode root = (TreeNode)tree.getModel().getRoot();
        return find2(tree, new TreePath(root), nodes, 0, false, exactWord);
    }
    
    
    public static TreeNode findByName(JTree tree, String[] names, boolean exactWord) {
        TreeNode root = (TreeNode)tree.getModel().getRoot();
        return find2(tree, new TreePath(root), names, 0, true,exactWord);
    }
    private static TreeNode find2(JTree tree, TreePath parent, Object[] nodes, int depth, boolean byName,boolean exactWord) {
        TreeNode node = (TreeNode)parent.getLastPathComponent();
        Object o = node;
        
        
        if (byName) {
            o = o.toString();
        }
        
        if ( (o.equals(nodes[depth]) && exactWord) || (o.toString().indexOf   (nodes[depth].toString() ) >-1 && !exactWord)  ) {
            
            if (depth == nodes.length-1) {
                return node;
            }
            
            
            if (node.getChildCount() >= 0) {
                for (Enumeration e=node.children(); e.hasMoreElements(); ) {
                    TreeNode n = (TreeNode)e.nextElement();
                    TreePath path = parent.pathByAddingChild(n);
                    TreeNode result = find2(tree, path, nodes, depth+1, byName,exactWord);
                    
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        
        
        return null;
    }
    
}
