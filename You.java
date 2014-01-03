import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class You extends Actor {

	Grid g = super.getGrid();
	boolean death = false;

	public You(Location loc, Grid gr) {
		super(loc, gr, "You");
		if (gr.isValid(loc))
			drawYou(this);
		else {
			drawYou(this);
			gr.setFinished();
		}
	}

	public Location jump() {
		ArrayList<Location> jumpLocs = new ArrayList<Location>();
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				if (!(g.getActorName(new Location(i, j)).equals("Fence"))) {
					jumpLocs.add(new Location(i, j));
				}
			}
		}
		Random r = new Random();
		int jumpIndex = r.nextInt(jumpLocs.size());
		return (jumpLocs.get(jumpIndex));
	}

	public ArrayList<Location> adjacentLocations() {
		ArrayList<Location> adjacentLocations = new ArrayList<Location>();
		Location youLoc = super.getLoc();
		adjacentLocations.add(new Location(youLoc.getCol(), youLoc.getRow() - 1)); // up
		adjacentLocations.add(new Location(youLoc.getCol() + 1, youLoc.getRow() - 1)); // up right
		adjacentLocations.add(new Location(youLoc.getCol() + 1, youLoc.getRow())); // right
		adjacentLocations.add(new Location(youLoc.getCol() + 1, youLoc.getRow() + 1)); // down right
		adjacentLocations.add(new Location(youLoc.getCol(), youLoc.getRow() + 1)); // down
		adjacentLocations.add(new Location(youLoc.getCol() - 1, youLoc.getRow() + 1)); // down left
		adjacentLocations.add(new Location(youLoc.getCol() - 1, youLoc.getRow())); // left
		adjacentLocations.add(new Location(youLoc.getCol() - 1, youLoc.getRow() - 1)); // up left
		return adjacentLocations;
	}

	public void drawYou(You you) {
		int width = getGrid().getCellDim() - 1;
		int height = getGrid().getCellDim() - 1;
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File("You.jpg"));
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "You.jpg not working because " + e);
		}
		getGrid().getGraphics().drawImage(image, getGrid().pixelLoc(getLoc()).getCol() + 1, getGrid().pixelLoc(getLoc()).getRow() + 1, width, height, null);

	}

}
