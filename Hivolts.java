
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;
import javax.swing.JOptionPane;


public class Hivolts extends JApplet implements KeyListener {
        
        Graphics2D g2d;
        boolean finished = false;
        Grid gr;
        String size;
        You you;
        
        public Hivolts() { }
        
        public void init() {
        	addKeyListener(this);
        	setFocusable(true);
            size = JOptionPane.showInputDialog(null, "Would you like the window size: big or medium");
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
            gr = new Grid(width, height, g);
            gr.drawGrid();
            // while (finished != true) {
                    // play game
            // }
        }
        

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyChar()) {
				case 'j': // j
					you.jump();
					// get one more move
					break;
				case 's': // s
					break;
				case 'q':
					you.move(you.adjacentLocations().get(7));
					break;
				case 'w':
					you.move(you.adjacentLocations().get(0));
					break;
				case 'e':
					you.move(you.adjacentLocations().get(1));
					break;
				case 'a':
					you.move(you.adjacentLocations().get(2));
					break;
				case 'd':
					you.move(you.adjacentLocations().get(3));
					break;
				case 'z':
					you.move(you.adjacentLocations().get(4));
					break;
				case 'x':
					you.move(you.adjacentLocations().get(5));
					break;
				case 'c':
					you.move(you.adjacentLocations().get(6));
					break;
			}
			
		}
		
		public void keyReleased(KeyEvent e) { }

		public void keyTyped(KeyEvent e) { }          

}
        
 
