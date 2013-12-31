
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
			gr.getYou().jump();
			repaint();
			// get one more move
			break;
		case 's': // s
			gr.moveMhos();
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

	       

}


