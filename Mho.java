
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Mho extends Actor {
	Location youLoc = getGrid().getYouLoc();
	int row = youLoc.getRow();
	int col = youLoc.getCol();
	Location loc;

	// Mho rows/columns
	int mRow = getLoc().getRow();
	int mCol = getLoc().getCol();

	// Mho possible positions
	Location c = (new Location ((mCol), (mRow)));           //current position
	Location ul = (new Location ((mCol - 1), (mRow - 1)));  //up and left
	Location u = (new Location ((mCol), (mRow - 1)));       //up
	Location ur = (new Location ((mCol + 1), (mRow - 1)));  //up and right
	Location r = new Location ((mCol + 1), (mRow));       //right
	Location dr = (new Location ((mCol + 1), (mRow + 1)));  //down and right
	Location d = (new Location ((mCol), (mRow + 1)));       //down
	Location dl = (new Location ((mCol - 1), (mRow + 1)));  //down and left
	Location l = (new Location ((mCol - 1), (mRow)));       //left




	public Mho(Location loc, Grid gr) {
		super(loc, gr, "Mho");
		drawMho();
	}

	public Location nextMove(){
		Location mhoNextLoc = c;
		// in the same row
		if (row == mRow){
			if (col < mCol){
				if (!getGrid().getActorName(l).equals("Mho")){
					mhoNextLoc = l;
				}
			}
			else {
				if (!getGrid().getActorName(r).equals("Mho")){
					mhoNextLoc = r;
				}
			}
		}

		//in the same col
		if (col == mCol){
			if (row < mRow){
				if (!getGrid().getActorName(u).equals("Mho")){
					mhoNextLoc = u;
				}
			}
			else{
				if (!getGrid().getActorName(d).equals("Mho")){
					mhoNextLoc = d;
				}
			}
		}



		//in the same diagonal
		if (Math.abs(row - mRow) == Math.abs(col - mCol)){
			if((mRow < row) && (mCol < col)){
				if (!getGrid().getActorName(dr).equals("Mho")){
					mhoNextLoc = dr;
				}
			}
			if((mRow > row) && (mCol < col)){
				if (!getGrid().getActorName(ur).equals("Mho")){
					mhoNextLoc = ur;
				}
			}
			if ((mRow < row) && (mCol > col)){
				if (!getGrid().getActorName(dl).equals("Mho")){
					mhoNextLoc = dl;
				}
			}
			if ((mRow > row) && (mCol > col)){
				if (!getGrid().getActorName(ul).equals("Mho")){
					mhoNextLoc = ul;
				}
			}

		}

		//horizontal is greater than vertical
		if (Math.abs(col - mCol) > Math.abs(row - mRow)){
			if(mCol > col){
				if (!getGrid().getActorName(l).equals("Mho")){
					mhoNextLoc = l;
				}
			}
			if(mCol < col){
				if (!getGrid().getActorName(r).equals("Mho")){
					mhoNextLoc = r;
				}
			}

			//vertical is greater than horizontal
			if (Math.abs(col - mCol) < Math.abs(row - mRow)){
				if(mRow > row){
					if (!getGrid().getActorName(u).equals("Mho")){
						mhoNextLoc = u;
					}
				}
				if(mRow < row){
					if (!getGrid().getActorName(d).equals("Mho")){
						mhoNextLoc = d;
					}
				}

			}

		}
		return mhoNextLoc;

	}

	public void drawMho(){
		int width = getGrid().getCellDim() -1;
		int height = getGrid().getCellDim() -1;
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File("Mho.jpg"));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Mho.jpg not working because " + e);
		}

		getGrid().getGraphics().drawImage(image, getGrid().pixelLoc(getLoc()).getCol() + 1, getGrid().pixelLoc(getLoc()).getRow() + 1, width, height, null);

	}

}


