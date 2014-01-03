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

}
