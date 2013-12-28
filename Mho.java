public class Mho extends Actor {
	Location youLoc = getGrid().getYou();
	int row = youLoc.getRow();
	int col = youLoc.getCol();
	Location loc;
	
        public Mho(Location loc, Grid gr) {
                super(loc, gr, "Mho");
        }
        
        public void nextMove(){
        	
        	if (row == loc.getRow()){
        		if (col < loc.getCol()){
        			//move to (col + 1, row)
        		}
        		else{
        			//move to (col - 1, row)
        		}
        	}
        	if (col == loc.getCol()){
        		if (row < loc.getRow()){
        			//move to (col, row + 1)
        		}
        		else{
        			//move to (col, row - 1)
        		}
        	}
        	
        }
        
}
