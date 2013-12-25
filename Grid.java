public class Grid {
	
	boolean[][] actors = new boolean[12][12];
	
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
	
	public boolean isValid(Location loc) {
		boolean validity;
		if (actors[loc.getCol()][loc.getRow()])
			validity = true;
		else
			validity = false;
		return validity;
	}
	
	public void putActor(Location loc) {
		actors[loc.getCol()][loc.getRow()] = true;
	}
	
	public boolean[][] getActorArray() {
		return actors;
	}
	
} 
