import java.awt.Graphics;

public class Grid {
	
	boolean[][] actors = new boolean[12][12];
	String[][] actors2 = new String[12][12];
	
	public Grid() {
		initActorArray();
	}
	
	void initActorArray() {
		for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                    actors[i][j] = false;
            }
		}
	}
	
	void initActorNameArray() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				actors2[i][j] = "null";
			}
		}
	}
	
	public boolean isValid(Location loc) {
		boolean validity;
		if (actors[loc.getCol()][loc.getRow()])
			validity = true;
		else
			validity = false;
		return validity;
	}
	
	public void putActor(Actor a) {
		actors[a.getLoc().getCol()][a.getLoc().getRow()] = true;
		actors2[a.getLoc().getCol()][a.getLoc().getRow()] = a.getName();
	}
	
	public boolean[][] getActorArray() {
		return actors;
	}
	
	public String[][] getActorNameArray() {
		return actors2;
	}
	
} 
