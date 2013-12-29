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

	boolean[][] actors = new boolean[12][12];
	String[][] actors2 = new String[12][12];
	private final int ROWS = 12;
	private final int COLS = 12;
	private int gridWidth;
	private int gridHeight;
	private boolean finished = false;
	You you;
	Graphics2D g;

	public Grid(int width, int height, Graphics graphics) {
		initActorArray();
		initActorNameArray();
		gridWidth = width;
		gridHeight = height;
		g = (Graphics2D) graphics;
	}
	
	public Graphics getGraphics() {
		return g;
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

	public Location getYou() {
		Location youLoc = new Location (-1, -1);
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				if (actors2[i][j].equalsIgnoreCase("You"))
					youLoc = new Location(i, j);
			}
		}
		return youLoc;
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
		int offset = (int) ((gridWidth - 13)/14);
		int cell_width = offset;
		int cell_height = offset;
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

	public void nullLoc(Location loc) {
		g.setColor(Color.black);
		g.fillRect(pixelLoc(loc).getCol() + 1, pixelLoc(loc).getRow() + 1, getCellDim() - 1, getCellDim() - 1);
	}

	public void placeFences() {
		for (Location loc : allAround()) {
			Fence fence = new Fence(loc, this);
		}
		for (Location loc : choosePlaces(20, possibleFencePlaces())) {
			Fence fence = new Fence(loc, this);
		}
	}

	public void placeMhos() {
		for (Location loc : choosePlaces(12, possibleMhoPlaces())) {
			Mho mho = new Mho(loc, this);
		}
	}

	public void placeYou() {
		Random r = new Random();
		int randLocIndex = r.nextInt(possibleYouPlaces().size());
		Location youLoc = possibleYouPlaces().get(randLocIndex);
		you = new You(youLoc, this);
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

	public ArrayList<Location> possibleFencePlaces() {
		ArrayList<Location> possibleFencePlaces = new ArrayList<Location>();
		for (int i = 1; i < 11; i++) {
			for (int j = 1; j < 11; j++) {
				possibleFencePlaces.add(new Location(i, j));
			}
		}
		Collections.shuffle(possibleFencePlaces);
		return possibleFencePlaces;
	}

	public ArrayList<Location> possibleMhoPlaces() {
		ArrayList<Location> possibleMhoPlaces = new ArrayList<Location>();
		for (Location loc : possibleFencePlaces()) {
			if (!isValid(loc)) {
				possibleMhoPlaces.add(loc);
			}
		}
		Collections.shuffle(possibleMhoPlaces);
		return possibleMhoPlaces;
	}

	public ArrayList<Location> possibleYouPlaces() {
		ArrayList<Location> possibleYouPlaces = new ArrayList<Location>();
		for (Location loc : possibleFencePlaces()) {
			if (!isValid(loc)) {
				possibleYouPlaces.add(loc);
			}
		}
		return possibleYouPlaces;
	}

	public ArrayList<Location> choosePlaces(int num, ArrayList<Location> possibleLocs) {
		ArrayList<Location> places = new ArrayList<Location>();
		for (int i = 0; i < num; i++) {
			places.add(possibleLocs.get(i));        
		}
		return places;
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

	public void setFinished() {
		finished = !finished;
	}

} 
