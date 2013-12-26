import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JOptionPane;


public class Hivolts extends JApplet {
        
        private final int ROWS = 12;
        private final int COLS = 12;
        // boolean[][] actors = new boolean[COLS][ROWS]; SHOULD BE IN ACTOR.JAVA
        boolean finished = false;
        
        public Hivolts() { }
        
        
        
        public void init() {
                setSize(600, 660);
                // initActorArray();
        }
        
        public void paint(Graphics g) {
                Graphics2D graphics = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                
                if (width > (int) (height / 1.1)) { // horizontal too large
                        playHivolts((int) (height / 1.1), height, g);
                } else if (height > (int) (width * 1.1)) { // vertical too large
                        playHivolts(width, (int) (width * 1.1), g);
                } else {
                        playHivolts(width, height, g);
                }
                
        }
        
        public void playHivolts(int width, int height, Graphics g) {
                drawGrid(width, height, g);
                Grid gr = new Grid();
                placeFences(allAround(), possibleFencePlaces(), gr, g);
                // placeYou()
                // placeFences()
                // placeMhos()
                // while (finished != true) {
                        // play game
                // }
        }
        
        public void nullLoc(Location loc, Graphics g) {
        	g.setColor(Color.white);
        	g.fillRect(pixelLoc(loc).getCol() + 1, pixelLoc(loc).getRow() + 1, getCellDim() - 1, getCellDim() - 1);
        }
        
        public void placeYou(Grid gr, Location[] locs) {
        	Random r = new Random();
        	int randLocIndex = r.nextInt(locs.length);
        	Location youLoc = locs[randLocIndex];
        	You you = new You(youLoc, gr);
        }
        
        public void placeFences(ArrayList<Location> allAround, ArrayList<Location> rest, Grid gr, Graphics g) {
        	for (Location loc : allAround) {
        		Fence fence = new Fence(loc, gr);
        		drawFence(fence, g);
        	}
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
    		return possibleFencePlaces;
    	}
        // public void placeMhos()
        
        
        public void drawYou(You you, Graphics g) {
        	if (you.checkIfDead()) {
        		
        	}
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
                if (getWidth() > (int) (getHeight() / 1.1)) { // horizontal too large
                        dim = (int) (((int) (getHeight() / 1.1) - 13) / 14);
                } else { // width is fine
                        dim = (int) ((getWidth() - 13) / 14);
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
        }
        
                
}
        
        
