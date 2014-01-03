import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Grid {

	String[][] actors2 = new String[12][12];
	private final int ROWS = 12;
	private final int COLS = 12;
	private int gridWidth;
	private int gridHeight;
	private boolean finished = false;
	You you;
	Graphics2D g;
	ArrayList<Location> fencePlaces = new ArrayList<Location>();
	ArrayList<Location> mhoPlaces = new ArrayList<Location>();
	Location youLoc;

	public Grid(int width, int height, Graphics graphics, ArrayList<Location> fenceLocs, ArrayList<Location> mhoLocs, Location youLocation, String[][] actorNames) {
		gridWidth = width;
		gridHeight = height;
		g = (Graphics2D) graphics;
		fencePlaces = fenceLocs;
		mhoPlaces = mhoLocs;
		youLoc = youLocation;
		actors2 = actorNames;
	}
	
	public void printArrayList(ArrayList<Location> locs) {
		String ans = "[";
		for (Location loc : locs) {
			ans += loc.printLoc() + ", ";
		}
		ans += "]";
		JOptionPane.showMessageDialog(null, ans);
	}

	public Graphics getGraphics() {
		return g;
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
		if ((actors2[loc.getCol()][loc.getRow()].equals("null")) || (actors2[loc.getCol()][loc.getRow()].equals("you")))
			validity = true;
		else
			validity = false;
		return validity;
	}

	public void putActor(Actor a) {
		actors2[a.getLoc().getCol()][a.getLoc().getRow()] = a.getName();
	}

	public String[][] getActorNameArray() {
		return actors2;
	}  

	public ArrayList<Mho> getMhoLocs() {
		ArrayList<Mho> mhoLocs = new ArrayList<Mho>();
		for (int i = 0; i < COLS; i++) {
			for (int j = 0; j < ROWS; j++) {
				if ((getActorName(new Location(i, j))).equalsIgnoreCase("mho")) {
					mhoLocs.add(new Mho(new Location(i, j), this));
				}
			}
		}
		return mhoLocs;
	}

	public String getActorName(Location loc) {
		String actorName = "null";
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if ((loc.getCol() == i) && (loc.getRow() == j)) {
					actorName = getActorNameArray()[i][j];
				}
			}
		}
		return actorName;
	}
	
	void drawGrid() {
		int offset = getCellDim();
		int cell_width = offset;
		int cell_height = offset;
		antiAlias();
		g.setColor(Color.black);
		g.setColor(Color.white);
		for (int row = 0; row <= ROWS; row++) {
			g.drawLine(offset,
					offset + (row * (cell_height + 1)), offset
					+ COLS * (cell_width + 1), offset
					+ (row * (cell_height + 1)));
		}
		for (int col = 0; col <= COLS; col++) {
			g.drawLine(offset + (col * (cell_width + 1)), offset,
					offset + (col * (cell_width + 1)), offset
					+ ROWS * (cell_height + 1));
		}
		Font Hivolts = new Font("TimesRoman", Font.BOLD, offset);
		g.setFont(Hivolts);
		g.drawString("HIVOLTS",(int) (offset * 5.3), gridHeight - offset);
		placeFences();
		placeMhos();
		placeYou();
	}

	public int getCellDim() {
		int dim;
		if ((double) gridWidth > (gridHeight / 1.1)) { // horizontal too large
			dim = (int) (((gridHeight / 1.1) - 13.0) / 14.0);
		} else { // width is fine
			dim = (int) (((double) (gridHeight) - 13.0) / 14.0);
		}
		return dim - 4;
	}

	public void placeFences() {
		for (Location loc : allAround()) {
			Fence fence = new Fence(loc, this);
		}
		for (Location loc : fencePlaces) {
			Fence fence = new Fence(loc, this);
		}
	}

	public void placeMhos() {
		for (Location loc : mhoPlaces) {
			Mho mho = new Mho(loc, this);
		}
	}

	public void placeYou() {
		you = new You(youLoc, this);
	}
	
	public You getYou() {
		return you;
	}

	public ArrayList<Location> allAround() {
		ArrayList<Location> allAround = new ArrayList<Location>();
		for (int i = 0; i < 12; i++) {
			allAround.add(new Location(i, 0));
			allAround.add(new Location(i, 11));
			allAround.add(new Location(0, i));
			allAround.add(new Location(11, i));
		}
		return allAround;
	}
	
	public Location pixelLoc(Location loc) {
		Location pixelLoc;
		int row = loc.getRow();
		int col = loc.getCol();
		int offset = getCellDim();
		int celldim = getCellDim();
		pixelLoc = new Location(offset + (col * (celldim + 1)), offset + (row * (celldim + 1)));
		return pixelLoc;
	}

	public void antiAlias() {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, // anti aliasing
				RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	public boolean getFinished() {
		return finished;
	}
	
	public void setFinished() {
		finished = !finished;
	}

} 
