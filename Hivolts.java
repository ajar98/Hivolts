
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JApplet;
import javax.swing.JOptionPane;


public class Hivolts extends JApplet implements KeyListener {

	Graphics2D g2d;
	boolean finished = false;
	Grid gr;
	String size;
	ArrayList<Location> fencePlaces = new ArrayList<Location>();
	ArrayList<Location> mhoPlaces = new ArrayList<Location>();
	Location youLoc;
	String[][] actorNames = new String[12][12];

	public Hivolts() { }

	public void init() {
		addKeyListener(this);
		setFocusable(true);
		setSize(600, 660);
		initActorNameArray();
		initFencePlaces();
		initMhoPlaces();
		initYouPlace();
	}
	
	

	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		if (width > (int) (height / 1.1)) { // horizontal too large
			playHivolts((int) (height / 1.1), height, g);
		} else if (height > (int) (width * 1.1)) { // vertical too large
			playHivolts(width, (int) (width * 1.1), g);
		} else {
			playHivolts(width, height, g);
		}

	}

	public void playHivolts(int width, int height, Graphics g) {
		gr = new Grid(width, height, g, fencePlaces, mhoPlaces, youLoc, actorNames);
		gr.drawGrid();
		// while (finished != true) {
		// play game
		// }
	}


	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyChar()) {
		case 'j':
			gr.getYou().jump();
			repaint();
			break;
		case 's':
			gr.moveMhos();
			repaint();
			break;
		case 'q':
			gr.getYou().move(gr.getYou().adjacentLocations().get(7));
			repaint();
			gr.moveMhos();
			repaint();
			break;
		case 'w':
			gr.getYou().move(gr.getYou().adjacentLocations().get(0));
			repaint();
			gr.moveMhos();
			repaint();
			break;
		case 'e':
			gr.getYou().move(gr.getYou().adjacentLocations().get(1));
			repaint();
			gr.moveMhos();
			repaint();
			break;
		case 'a':
			gr.getYou().move(gr.getYou().adjacentLocations().get(2));
			repaint();
			gr.moveMhos();
			repaint();
			break;
		case 'd':
			gr.getYou().move(gr.getYou().adjacentLocations().get(3));
			repaint();
			gr.moveMhos();
			repaint();
			break;
		case 'z':
			gr.getYou().move(gr.getYou().adjacentLocations().get(4));
			repaint();
			gr.moveMhos();
			repaint();
			break;
		case 'x':
			gr.getYou().move(gr.getYou().adjacentLocations().get(5));
			repaint();
			gr.moveMhos();
			repaint();
			break;
		case 'c':
			gr.getYou().move(gr.getYou().adjacentLocations().get(6));
			repaint();
			gr.moveMhos();
			repaint();
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void initFencePlaces() {
		ArrayList<Location> fences = choosePlaces(20, possibleFencePlaces());
		for (Location loc : fences) {
			actorNames[loc.getCol()][loc.getRow()] = "fence";
		}
		fencePlaces = fences;
	}
	
	public void initMhoPlaces() {
		ArrayList<Location> mhos = choosePlaces(12, possibleMhoPlaces());
		for (Location loc : mhos) {
			actorNames[loc.getCol()][loc.getRow()] = "mho";
		}
		mhoPlaces = mhos;
	}

	public void initYouPlace() {
		youLoc = choosePlaces(1, possibleYouPlaces()).get(0);
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
	
	void initActorNameArray() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				actorNames[i][j] = "null";
			}
		}
	}
	
	public boolean isValid(Location loc) {
		boolean validity;
		if (actorNames[loc.getCol()][loc.getRow()].equals("null"))
			validity = true;
		else
			validity = false;
		return validity;
	}
	       

}


