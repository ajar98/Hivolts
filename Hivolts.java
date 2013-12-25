import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JOptionPane;


public class Hivolts extends JApplet {
        
        private final int ROWS = 12;
        private final int COLS = 12;
        // boolean[][] actors = new boolean[COLS][ROWS]; SHOULD BE IN ACTOR.JAVA
        boolean finished = false;
        
        public Hivolts() { }
        
       /*  public void initActorArray() {
                for (int i = 0; i < 12; i++) {
                        for (int j = 0; j < 12; j++) {
                                actors[i][j] = false;
                        }
                }
        } */ // SHOULD BE IN ACTOR.JAVA
        
        
        
        public void init() {
                setSize(600, 660);
                // initActorArray();
                // Buttons HERE
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
                // placeYou()
                // placeFences()
                // placeMhos()
                // while (finished != true) {
                        // play game
                // }
        }
        
        // public void placeYou()
        // public void placeFences()
        // public void placeMhos()
        
        public void drawYou(You you, Graphics g) {
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
                        JOptionPane.showMessageDialog(null, "You.jpg not working because of " + e);
                }
                
                g.drawImage(image, pixelLoc(you.getLoc()).getCol() + 1, pixelLoc(you.getLoc()).getRow() + 1, width, height, null);
        }
        
        // public void drawFence(Fence fence, Graphics g)
        // public void drawMho(Mho mho, Graphics g)
        
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
        
        
        //preccess move, not sure if its right at all or if it works.
        public void ProccessMove(KeyEvent e){
                if (your turn){
                        if (e.getKeyChar() == q) {
                                You.makemove(up left)
                        }
                }
                
        }
        
        // private class jumpButton extends JButton implements ActionListener
        // private class rightButton extends JButton implements ActionListener
        // private class leftButton extends JButton implements ActionListener
        // private class upButton extends JButton implements ActionListener
        // private class downButton extends JButton implements ActionListener
        // private class upRightButton extends JButton implements ActionListener
        // private class downRightButton extends JButton implements ActionListener
        // private class upLeftButton extends JButton implements ActionListener
        // private class downLeftButton extends JButton implements ActionListener
        // private class doneButton extends JButton implements ActionListener
        
}
