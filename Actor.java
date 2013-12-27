
public class Actor {
	
	private final Location actorLoc;
	private int actorCol;
	private int actorRow;
	private final Grid g;
	
	public Actor(Location loc, Grid gr) { 
		actorLoc = loc;
		g = gr;
		gr.putActor(loc);
	}
	
	public void setCol(int col) {
		actorCol = col;
	}
	
	public boolean checkIfDead() {
		boolean death;
		if (g.isValid(actorLoc))
			death = false;
		else
			death = true;
		return death;
	}
	
	public void setRow(int row) {
		actorRow = row;
	}
	
	public Location getLoc() {
		return actorLoc;
	}
	
	
	
	public void move(Location nextLoc) {
		setCol(nextLoc.getCol());
		setRow(nextLoc.getRow());
	}
	
	public Grid getGrid() {
		return g;
	}
	
}
