import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class You extends Actor {

	Grid g = super.getGrid();

	public You(Location loc, Grid gr) {
		super(loc, gr, "You");
		if (!this.checkIfDead()) {
			drawYou(this);
		} else {
			if (JOptionPane.showConfirmDialog(null, "Would you like to play again?") == 0) {
				// figure out what goes here to start the game again
			} else {
				g.setFinished();
			}
		} 
	}

	public void jump() {
		JOptionPane.showMessageDialog(null, "I HATE KeyListener, but it got to jump()");
		ArrayList<Location> jumpLocs = new ArrayList<Location>();
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				if (!(g.getActorName(new Location(i, j)).equals("Fence"))) {
					jumpLocs.add(new Location(i, j));
				}
			}
		}
		JOptionPane.showMessageDialog(null, "HEY IT GOT TO HERE!1!!1!!!!!1!!");
		Random r = new Random();
		int jumpIndex = r.nextInt(jumpLocs.size());
		JOptionPane.showMessageDialog(null, jumpLocs.get(jumpIndex));
		move(jumpLocs.get(jumpIndex));
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
		return adjacentLocations();
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

	public boolean checkIfDead() {
		boolean death;
		if (g.isValid(getLoc()))
			death = false;
		else
			death = true;
		return death;
	}

}
