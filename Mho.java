public class Mho extends Actor {
	Location loc;

	// Mho youRows/youColumns
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
	}

	public Location nextMove(){
		Location mhoNextLoc = new Location(-1, -1);
		Location youLoc = getGrid().getYou().getLoc();
		int youRow = youLoc.getRow();
		int youCol = youLoc.getCol();
		// in the same youRow
		if (youRow == mRow){
			// Mho is to the right of You
			if (youCol < mCol){
				if (!getGrid().getActorName(l).equals("Mho")){
					mhoNextLoc = l;
				}
			}
			// Mho is to the left of You
			else {
				if (!getGrid().getActorName(r).equals("Mho")){
					mhoNextLoc = r;
				}
			}
		}

		//in the same youCol
		if (youCol == mCol){
			// Mho is below you
			if (youRow < mRow){
				if (!getGrid().getActorName(u).equals("Mho")){
					mhoNextLoc = u;
				}
			}
			// Mho is above you
			else{
				if (!getGrid().getActorName(d).equals("Mho")){
					mhoNextLoc = d;
				}
			}
		}



		//in the same diagonal
		if (Math.abs(youRow - mRow) == Math.abs(youCol - mCol)){
			// mho is up and left
			if((mRow < youRow) && (mCol < youCol)){
				if (!getGrid().getActorName(dr).equals("Mho")){
					mhoNextLoc = dr;
				}
			}
			// mho is down and left
			if((mRow > youRow) && (mCol < youCol)){
				if (!getGrid().getActorName(ur).equals("Mho")){
					mhoNextLoc = ur;
				}
			}
			// mho is up and right
			if ((mRow < youRow) && (mCol > youCol)){
				if (!getGrid().getActorName(dl).equals("Mho")){
					mhoNextLoc = dl;
				}
			}
			// mho is down and right
			if ((mRow > youRow) && (mCol > youCol)){
				if (!getGrid().getActorName(ul).equals("Mho")){
					mhoNextLoc = ul;
				}
			}

		}

		//horizontal is greater than vertical
		if (Math.abs(youCol - mCol) > Math.abs(youRow - mRow)){
			// Mho is to the right of you
			if(mCol > youCol){
				if (!getGrid().getActorName(l).equals("Mho")){
					mhoNextLoc = l;
				}
			}
			//Mho is to the left of you
			if(mCol < youCol){
				if (!getGrid().getActorName(r).equals("Mho")){
					mhoNextLoc = r;
				}
			}

			//vertical is greater than horizontal
			if (Math.abs(youCol - mCol) < Math.abs(youRow - mRow)){
				// Mho is below you
				if(mRow > youRow){
					if (!getGrid().getActorName(u).equals("Mho")){
						mhoNextLoc = u;
					}
				}
				// Mho is above you
				if(mRow < youRow){
					if (!getGrid().getActorName(d).equals("Mho")){
						mhoNextLoc = d;
					}
				}

			}

		}
		return mhoNextLoc;

	}

}

