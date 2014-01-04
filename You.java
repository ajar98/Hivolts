import java.util.ArrayList;
import java.util.Random;


public class You extends Actor {

	Grid g = super.getGrid();
	boolean death = false;

	public You(Location loc, Grid gr) {
		super(loc, gr, "You");
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

}
