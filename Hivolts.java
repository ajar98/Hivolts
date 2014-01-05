
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class Hivolts extends JApplet implements KeyListener {

	Grid gr;
	String size;
	String[][] actorNames;
	ArrayList<Location> fencePlaces;
	ArrayList<Location> mhoPlaces;
	Location youLoc;
	Graphics graphics;
	String fenceInput;
	int fenceNum = 20;
	String mhoInput;
	int mhoNum = 12;
	AudioClip clip;
	// ReplotButton r;

	/**
	 * Hivolts constructor
	 * Asks user for how many fences they want
	 * Allows for default option which puts 20 fences and 12 mhos
	 */
	
	public Hivolts() { 
		fenceInput = JOptionPane.showInputDialog(null, "How many fences on the interior? Enter 'default' for the default values.");
		if (fenceInput.equals("default")) {
			fenceNum = 20;
		} else {
			fenceNum = Integer.parseInt(fenceInput);
		}
		mhoInput = JOptionPane.showInputDialog(null, "How many mhos on the interior? Enter 'default' for the default values.");
		if (mhoInput.equals("default")) {
			mhoNum = 12;
		} else {
			mhoNum = Integer.parseInt(mhoInput);
		}
	}
	
	/**
	 * Initializes ArrayList<Location>s for places for Fences, Mhos and You
	 * Sets size of applet
	 * Adds necessary KeyListener calls
	 * init() is only called once, so when repaint() is called, the game isn't created over again
	 */

	public void init() {
		addKeyListener(this);
		setFocusable(true);
		setSize(600, 660);
		try {
			clip = Applet.newAudioClip(new URL(getCodeBase(), "Applause.mp3"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actorNames = initActorArray();
		fencePlaces = initFencePlaces();
		mhoPlaces = initMhoPlaces();
		youLoc = initYouPlace();
	}

	/**
	 * Allows changing of window size without redoing the entire program
	 */
	
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

	/**
	 * Passes fencePlaces, mhoPlaces, youLoc and actorNames that were initialized in init() into the new Grid
	 * Creates the new Grid
	 * Checks if You is dead and allows user to restart game, or calls System.exit and kills the applet
	 * @param width is the correct width for the grid
	 * @param height is the correct height for the grid
	 * @param g allows Graphics to be passed to the grid
	 */
	
	public void playHivolts(int width, int height, Graphics g) {
		gr = new Grid(width, height, g, fencePlaces, mhoPlaces, youLoc, actorNames);
		gr.drawGrid();
		/* r = new ReplotButton();
		r.setBounds(75, 550, 100, 36);
		add(r);
		r.setVisible(true); */ 
		if (!gr.getActorName(gr.getYou().getLoc()).equals("You")) {
			int again;
			again = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
			if (again != 0) {
				System.exit(0);
			} else {
				init();
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
			case 'j': // You jumps
				youLoc = gr.getYou().jump();
				repaint();
				break;
			case 's': // You sits
				moveMhos();
				repaint();
				break;
			case 'q': // You moves northwest
				youLoc = gr.getYou().adjacentLocations().get(7);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'w': // You moves north
				youLoc = gr.getYou().adjacentLocations().get(0);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'e': // You moves northeast
				youLoc = gr.getYou().adjacentLocations().get(1);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'd': // You moves east
				youLoc = gr.getYou().adjacentLocations().get(2);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'a': // You moves west
				youLoc = gr.getYou().adjacentLocations().get(6);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'c': // You moves southeast
				youLoc = gr.getYou().adjacentLocations().get(3);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'x': // You moves south
				youLoc = gr.getYou().adjacentLocations().get(4);
				repaint();
				moveMhos();
				repaint();
				break;
			case 'z': // You moves southwest
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

	/**
	 * Uses global variables youLoc, fencePlaces, and mhoPlaces so all of Hivolts.java can use them and pass them easily
	 * @return ArrayList<Location> for fences, mhos and Location of you
	 */
	
	public ArrayList<Location> initFencePlaces() {
		ArrayList<Location> fences = choosePlaces(fenceNum, possibleFencePlaces());
		for (Location loc : fences) {
			actorNames[loc.getCol()][loc.getRow()] = "fence";
		}
		return fences;
	}
	
	public ArrayList<Location> initMhoPlaces() {
		ArrayList<Location> mhos = choosePlaces(mhoNum, possibleMhoPlaces());
		for (Location loc : mhos) {
			actorNames[loc.getCol()][loc.getRow()] = "mho";
		}
		return mhos;
	}

	public Location initYouPlace() {
		Location youLocation = choosePlaces(1, possibleYouPlaces()).get(0);
		return youLocation;
	}
	
	/**
	 * Collections.shuffle shuffles the ArrayList, so that choosePlaces can just take the first n locations in the ArrayList
	 * possibleFencePlaces() is just everything not on the border
	 * possibleMhoPlaces() and possibleYouPlaces() are possibleFencePlaces() but only the valid places (isValid)
	 * @return ArrayList of possible locations
	 */
	
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
	
	/**
	 * choosePlaces just takes an ArrayList and returns a subset of it according to @param num
	 * @param num is the number of places to choose
	 * @param possibleLocs the places to choose from
	 * @return the places that have been chosen
	 */
	
	public ArrayList<Location> choosePlaces(int num, ArrayList<Location> possibleLocs) {
		ArrayList<Location> places = new ArrayList<Location>();
		for (int i = 0; i < num; i++) {
			places.add(possibleLocs.get(i));        
		}
		return places;
	}
	
	/**
	 * Checks if location in actorNames (NOT grid) has an actor
	 * @param loc which is checking if the Location has an actor
	 * @return true, if space has no actor or false, if space has an actor
	 */
	
	public boolean isValid(Location loc) {
		boolean validity;
		if ((actorNames[loc.getCol()][loc.getRow()].equals("null")))
			validity = true;
		else
			validity = false;
		return validity;
	}
	
	/**
	 * Checks if next move of each mho is valid: if true, then it sets the new value, else it removes it from the ArrayList
	 * If there are no more mhos, then the game either ends or starts over
	 */
	
	public void moveMhos() {
		for (int i = 0; i < mhoPlaces.size(); i++) {
			if (gr.isValid((new Mho(mhoPlaces.get(i), gr).nextMove()))) {
				mhoPlaces.set(i, (new Mho(mhoPlaces.get(i), gr)).nextMove());
			} else {
				mhoPlaces.remove(i);
			}
		}
		if (mhoPlaces.isEmpty()) {
			clip.play();
			int again = JOptionPane.showConfirmDialog(null, "You have won! Play again?");
			if (again == 0) {
				init();
			} else {
				System.exit(0);
			}
		}
	}
	       
	/* private class ReplotButton extends JButton implements ActionListener {
		ReplotButton() {
			super("REPLOT");
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent arg0) {
			Hivolts h = new Hivolts();
		}
	} */
	
}


