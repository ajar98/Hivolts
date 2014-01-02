import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Fence extends Actor {	

	public Fence(Location loc, Grid gr) {
		super(loc, gr, "Fence");
		drawFence();
	}

	public void drawFence(){
		int width = getGrid().getCellDim() -1;
		int height = getGrid().getCellDim() -1;
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File("Fence.jpg"));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Fence.jpg not working because " + e);
		}

		getGrid().getGraphics().drawImage(image, getGrid().pixelLoc(getLoc()).getCol() + 1, getGrid().pixelLoc(getLoc()).getRow() + 1, width, height, null);


	}

}
