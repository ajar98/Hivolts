import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Actor {

	private Location actorLoc;
	private int actorCol;
	private int actorRow;
	private final Grid g;
	public String actorName;

	public Actor(Location loc, Grid gr, String name) { 
		actorLoc = loc;
		g = gr;
		actorName = name;
		if (!name.equals("you")) gr.putActor(this);
		draw(name + ".jpg");
	}

	public String getName() {
		return actorName;
	}

	public void setCol(int col) {
		actorCol = col;
	}

	public void setRow(int row) {
		actorRow = row;
	}

	public void setLoc(Location loc) {
		actorLoc = loc;
	}

	public Location getLoc() {
		return actorLoc;
	}

	public Grid getGrid() {
		return g;
	}
	
	public void draw(String imageName) {
		int width = getGrid().getCellDim() -1;
		int height = getGrid().getCellDim() -1;
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File(imageName));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, imageName + " not working because " + e);
		}

		getGrid().getGraphics().drawImage(image, getGrid().pixelLoc(getLoc()).getCol() + 1, getGrid().pixelLoc(getLoc()).getRow() + 1, width, height, null);
	}

}
