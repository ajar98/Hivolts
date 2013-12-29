import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Mho extends Actor {
	Location youLoc = getGrid().getYou();
	int row = youLoc.getRow();
	int col = youLoc.getCol();
	Location loc;
	
        public Mho(Location loc, Grid gr) {
            super(loc, gr, "Mho");
            drawMho();
        }
        
        public void nextMove(){
        	
        	// in the same row
        	if (row == loc.getRow()){
        		if (col < loc.getCol()){
        			//move to (col + 1, row)
        		}
        		else {
        			//move to (col - 1, row)
        		}
        	}
        	
        	//in the same col
        	if (col == loc.getCol()){
        		if (row < loc.getRow()){
        			//move to (col, row + 1)
        		}
        		else{
        			//move to (col, row - 1)
        		}
        	}
        	
        	//in the same diagonal
        	if (Math.abs(row - loc.getRow()) == Math.abs(col - loc.getCol())){
        		if((loc.getRow() < row) && (loc.getCol() < col)){
        			//move (col +, row +)
        		}
        		if((loc.getRow() > row) && (loc.getCol() < col)){
        			//move (col +, row -)
        		}
        		if ((loc.getRow() < row) && (loc.getCol() > col)){
        			//move (col -, row +)
        		}
        		if ((loc.getRow() > row) && (loc.getCol() > col)){
        			// move (col -, row -)
        		}
        		
        	}
        	
        	//horizontal is greater than vertical
        	if (Math.abs(col - loc.getCol()) > Math.abs(row - loc.getRow())){
        		if(loc.getCol() > col){
        			//move (col +, row)
        		}
        		if(loc.getCol() < col){
        			//move (col -, row )
        	}
        	
        	//vertical is greater than horizontal
        	if (Math.abs(col - loc.getCol()) < Math.abs(row - loc.getRow())){
        		if(loc.getRow() > row){
        			//move (col -, row)
        		}
        		if(loc.getRow() < row){
        			//move (col +, row)
        		}
        		
        	}
        	
        }
        
        }
        
        public void drawMho(){
    		int width = getGrid().getCellDim() -1;
    		int height = getGrid().getCellDim() -1;
    		BufferedImage image = null;
    		try
    		{
    			image = ImageIO.read(new File("Mho.jpg"));
    		}
    		catch(Exception e)
    		{
    			JOptionPane.showMessageDialog(null, "Mho.jpg not working because " + e);
    		}

    		getGrid().getGraphics().drawImage(image, getGrid().pixelLoc(getLoc()).getCol() + 1, getGrid().pixelLoc(getLoc()).getRow() + 1, width, height, null);

    	}
}

