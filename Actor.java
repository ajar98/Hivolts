import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Actor {

	private Location actorLoc;
	private final Grid g;
	public String actorName;

	/**
	 * The constructor: a) initializes actorLoc, g and actorName from the parameters b) draws itself on the grid c) puts its name in the name array in Grid
	 * @param loc is the location of the actor
	 * @param gr is the grid it is in
	 * @param name is whether it is a fence, mho or you
	 */
	
	public Actor(Location loc, Grid gr, String name) { 
		actorLoc = loc;
		g = gr;
		actorName = name;
		draw(name + ".jpg");
		gr.putActor(this);
	}

	public String getName() {
		return actorName;
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
		int width = getGrid().getCellDim() - 1; // draw inside the bounds of the grid
		int height = getGrid().getCellDim() - 1;
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File(imageName));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, imageName + " not working because " + e);
		}
		// add one pixel so the drawing starts inside the grid square
		getGrid().getGraphics().drawImage(image, getGrid().pixelLoc(getLoc()).getCol() + 1, getGrid().pixelLoc(getLoc()).getRow() + 1, width, height, null);
	}

}
