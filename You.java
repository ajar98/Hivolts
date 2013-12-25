import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

// create Fence.java, Mho.java


public class You {
	
	private int youCol;
	private int youRow;
	
	public You (Location loc) {
		youCol = loc.getCol();
		youRow = loc.getRow();
	}
	
	public void setCol(int col) {
		youCol = col;
	}
	
	public void setRow(int row) {
		youRow = row;
	}
	
	public Location getYouLoc() {
		return new Location(youCol, youRow);
	}
	
	
	
	public void move(Location nextLoc) {
		setCol(nextLoc.getCol());
		setRow(nextLoc.getRow());
	}
	
	// public void jump()
	// Location[] adjacentLocations
}
