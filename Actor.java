
public class Actor {
	
	private final Location actorLoc;
	private int actorCol;
	private int actorRow;
	
	public Actor(Location loc) { 
		actorLoc = loc;
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
	
}
