import javax.swing.JOptionPane;


public class Location {
        
        private final int COL;
        private final int ROW;
        
        public Location(int col, int row) {
                COL = col;
                ROW = row;
        }
        
        public int getRow() {
                return ROW;
        }
        
        public int getCol() {
                return COL;
        }
        
        public String printLoc() {
                return "(" + getCol() + ", " + getRow() + ")";
        }
        
 }
