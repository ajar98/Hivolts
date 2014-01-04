
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JApplet;
import javax.swing.JOptionPane;


public class Hivolts extends JApplet implements KeyListener {

	Grid gr;
	String size;
	String[][] actorNames;
	ArrayList<Location> fencePlaces;
	ArrayList<Location> mhoPlaces;
	Location youLoc;
	Graphics graphics;

	public Hivolts() { }

	public void init() {
		addKeyListener(this);
		setFocusable(true);
		setSize(600, 660);
		actorNames = initActorArray();
		fencePlaces = initFencePlaces();
		mhoPlaces = initMhoPlaces();
		youLoc = initYouPlace();
	}

	public void paint(Graphics g) {
		graphics = g;
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
		if (!gr.checkIfYou()) {
			int again;
			again = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
			if (again != 0) {
				System.exit(0);
			}
		}
	}
	
	public String printBool(boolean b) {
		if (b) return "true";
		else return "false";
	}
	
	public void appletPrint(String s) {
		JOptionPane.showMessageDialog(null, s);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyChar()) {
			case 'j':
				youLoc = gr.getYou().jump();
				repaint();
				break;
			case 's':
				moveMhos();
				repaint();
				break;
			case 'q':
				youLoc = gr.getYou().adjacentLocations().get(7);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'w':
				youLoc = gr.getYou().adjacentLocations().get(0);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'e':
				youLoc = gr.getYou().adjacentLocations().get(1);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'd':
				youLoc = gr.getYou().adjacentLocations().get(2);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'a':
				youLoc = gr.getYou().adjacentLocations().get(6);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'c':
				youLoc = gr.getYou().adjacentLocations().get(3);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'x':
				youLoc = gr.getYou().adjacentLocations().get(4);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'z':
				youLoc = gr.getYou().adjacentLocations().get(5);
				repaint();
				moveMhos();
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

	public ArrayList<Location> initFencePlaces() {
		ArrayList<Location> fences = choosePlaces(20, possibleFencePlaces());
		for (Location loc : fences) {
			actorNames[loc.getCol()][loc.getRow()] = "fence";
		}
		return fences;
	}
	
	public ArrayList<Location> initMhoPlaces() {
		ArrayList<Location> mhos = choosePlaces(12, possibleMhoPlaces());
		for (Location loc : mhos) {
			actorNames[loc.getCol()][loc.getRow()] = "mho";
		}
		return mhos;
	}

	public Location initYouPlace() {
		Location youLocation = choosePlaces(1, possibleYouPlaces()).get(0);
		return youLocation;
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
			if (actorNames[loc.getCol()][loc.getRow()].equals("null")) {
				possibleMhoPlaces.add(loc);
			}
		}
		Collections.shuffle(possibleMhoPlaces);
		return possibleMhoPlaces;
	}
	
	public ArrayList<Location> possibleYouPlaces() {
		ArrayList<Location> possibleYouPlaces = new ArrayList<Location>();
		for (Location loc : possibleFencePlaces()) {
			if (isValid(loc)) {
				possibleYouPlaces.add(loc);
			}
		}
		return possibleYouPlaces;
	}
	
	String[][] initActorArray() {
		String[][] actors = new String[12][12];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				actors[i][j] = "null";
			}
		}
		return actors;
	}
	
	public ArrayList<Location> choosePlaces(int num, ArrayList<Location> possibleLocs) {
		ArrayList<Location> places = new ArrayList<Location>();
		for (int i = 0; i < num; i++) {
			places.add(possibleLocs.get(i));        
		}
		return places;
	}
	
	public boolean isValid(Location loc) {
		boolean validity;
		if ((actorNames[loc.getCol()][loc.getRow()].equals("null")))
			validity = true;
		else
			validity = false;
		return validity;
	}
	
	public void moveMhos() {
		for (int i = 0; i < mhoPlaces.size(); i++) {
			if (gr.isValid((new Mho(mhoPlaces.get(i), gr).nextMove()))) {
				mhoPlaces.set(i, (new Mho(mhoPlaces.get(i), gr)).nextMove());
			} else {
				mhoPlaces.remove(i);
			}
		}
		if (mhoPlaces.isEmpty()) {
			int again = JOptionPane.showConfirmDialog(null, "You have won! Play again?");
			if (again == 0) {
				init();
			} else {
				System.exit(0);
			}
		}
	}
	       
	
	
}


