public class Actor {
	
	private final Location actorLoc;
	private int actorCol;
	private int actorRow;
	private final Grid g;
	
	public Actor(Location loc, Grid gr) { 
		actorLoc = loc;
		g = gr;
	}
	
	public void setCol(int col) {
		actorCol = col;
	}
	
	public void setRow(int row) {
		actorRow = row;
	}
	
	public Location getLoc() {
		return new Location(actorCol, actorRow);
	}
	
	
	
	public void move(Location nextLoc) {
		setCol(nextLoc.getCol());
		setRow(nextLoc.getRow());
	}
	
	public Grid getGrid() {
		return g;
	}
	
	// public void setActorBool(Location loc)
    	// public void eraseActor(Location loc)
	
}
