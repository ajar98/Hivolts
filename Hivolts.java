import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JOptionPane;


public class Hivolts extends JApplet {
        
        private final int ROWS = 12;
        private final int COLS = 12;
        boolean finished = false;
        Grid gr = new Grid();
        String size;
        
        public Hivolts() { }
        
        public void init() {
        	size = JOptionPane.showInputDialog(null, "Would you like the window size: big, medium or small");
        	if (size.equalsIgnoreCase("big")) setSize(600, 660);
        	else if (size.equalsIgnoreCase("medium")) setSize(500, 550);
        	else if (size.equalsIgnoreCase("small")) setSize(400, 440);
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
        	antiAlias(g);
        	drawGrid(width, height, g);
            placeFences(gr, g);
            placeMhos(gr, g);
            placeYou(gr, g);
            // while (finished != true) {
                    // play game
            // }
        }
        
        public void nullLoc(Location loc, Graphics g) {
        	g.setColor(Color.black);
        	g.fillRect(pixelLoc(loc).getCol() + 1, pixelLoc(loc).getRow() + 1, getCellDim() - 1, getCellDim() - 1);
        }
        
        public void placeFences(Grid gr, Graphics g) {
        	for (Location loc : allAround()) {
        		Fence fence = new Fence(loc, gr);
        		drawFence(fence, g);
        	}
        	for (Location loc : choosePlaces(20, possibleFencePlaces())) {
        		Fence fence = new Fence(loc, gr);
        		drawFence(fence, g);
        	}
        }
        
        public void placeMhos(Grid gr, Graphics g) {
        	for (Location loc : choosePlaces(12, possibleMhoPlaces(gr))) {
        		Mho mho = new Mho(loc, gr);
        		drawMho(mho, g);
        	}
        }
        
        public void placeYou(Grid gr, Graphics g) {
        	Random r = new Random();
        	int randLocIndex = r.nextInt(possibleYouPlaces(gr).size());
        	Location youLoc = possibleYouPlaces(gr).get(randLocIndex);
        	You you = new You(youLoc, gr);
        	drawYou(you, g);
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
    	
        public ArrayList<Location> possibleMhoPlaces(Grid gr) {
        	ArrayList<Location> possibleMhoPlaces = new ArrayList<Location>();
        	for (Location loc : possibleFencePlaces()) {
        		if (!gr.isValid(loc)) {
        			possibleMhoPlaces.add(loc);
        		}
        	}
        	Collections.shuffle(possibleMhoPlaces);
    		return possibleMhoPlaces;
        }
        
        public ArrayList<Location> possibleYouPlaces(Grid gr) {
        	ArrayList<Location> possibleYouPlaces = new ArrayList<Location>();
        	for (Location loc : possibleFencePlaces()) {
        		if (!gr.isValid(loc)) {
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
        
        public void drawYou(You you, Graphics g) {
        	if (!you.checkIfDead()) {
        		int width = getCellDim() - 1;
                int height = getCellDim() - 1;
                BufferedImage image = null;
                try
                {
                        URL u = new URL(getCodeBase(), "You.jpg");
                        image = ImageIO.read(u);
                }
                catch (Exception e) 
                {
                        JOptionPane.showMessageDialog(null, "You.jpg not working because " + e);
                }
                g.drawImage(image, pixelLoc(you.getLoc()).getCol() + 1, pixelLoc(you.getLoc()).getRow() + 1, width, height, null);
        	} else {
        		finished = false;
        	}
                
        }
        
        public void drawFence(Fence fence, Graphics g){
        	int width = getCellDim() -1;
        	int height = getCellDim() -1;
        	BufferedImage image = null;
        	try
        	{
        		URL u = new URL(getCodeBase(), "Fence.jpg");
        		image = ImageIO.read(u);
        }
        	catch(Exception e)
        	{
        		JOptionPane.showMessageDialog(null, "Fence.jpg not working because " + e);
        	}
        	
        	g.drawImage(image, pixelLoc(fence.getLoc()).getCol() + 1, pixelLoc(fence.getLoc()).getRow() + 1, width, height, null);
        	
        }
        
        public void drawMho(Mho mho, Graphics g){
        	int width = getCellDim() -1;
        	int height = getCellDim() -1;
        	BufferedImage image = null;
        	try
        	{
        		URL u = new URL(getCodeBase(), "Mho.jpg");
        		image = ImageIO.read(u);
        }
        	catch(Exception e)
        	{
        		JOptionPane.showMessageDialog(null, "Mho.jpg not working because " + e);
        	}
        	
        	g.drawImage(image, pixelLoc(mho.getLoc()).getCol() + 1, pixelLoc(mho.getLoc()).getRow() + 1, width, height, null);
        	
        }
        
        public int getCellDim() {
                int dim;
                if ((double) getWidth() > (getHeight() / 1.1)) { // horizontal too large
                        dim = (int) (((getHeight() / 1.1) - 13.0) / 14.0);
                } else { // width is fine
                        dim = (int) (((double) (getWidth()) - 13.0) / 14.0);
                }
                return dim;
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
        
        void drawGrid(int width, int height, Graphics g) {
                int offset = (int) ((width - 13)/14);
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
                g.drawString("HIVOLTS",(int) (offset * 5.3), height - offset);
        }
        
        public void antiAlias(Graphics g) {
        	((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, // anti aliasing
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }          

}
        
        
