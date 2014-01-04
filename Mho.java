public class Mho extends Actor {
	Location loc;

	// Mho getGrid().getYou().getLoc().getRow()s/getGrid().getYou().getLoc().getCol()umns
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
		Location mhoNextLoc = c;
		// in the same getGrid().getYou().getLoc().getRow()
		if (getGrid().getYou().getLoc().getRow() == mRow){
			if (getGrid().getYou().getLoc().getCol() < mCol){
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

		//in the same getGrid().getYou().getLoc().getCol()
		if (getGrid().getYou().getLoc().getCol() == mCol){
			if (getGrid().getYou().getLoc().getRow() < mRow){
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
		if (Math.abs(getGrid().getYou().getLoc().getRow() - mRow) == Math.abs(getGrid().getYou().getLoc().getCol() - mCol)){
			if((mRow < getGrid().getYou().getLoc().getRow()) && (mCol < getGrid().getYou().getLoc().getCol())){
				if (!getGrid().getActorName(dr).equals("Mho")){
					mhoNextLoc = dr;
				}
			}
			if((mRow > getGrid().getYou().getLoc().getRow()) && (mCol < getGrid().getYou().getLoc().getCol())){
				if (!getGrid().getActorName(ur).equals("Mho")){
					mhoNextLoc = ur;
				}
			}
			if ((mRow < getGrid().getYou().getLoc().getRow()) && (mCol > getGrid().getYou().getLoc().getCol())){
				if (!getGrid().getActorName(dl).equals("Mho")){
					mhoNextLoc = dl;
				}
			}
			if ((mRow > getGrid().getYou().getLoc().getRow()) && (mCol > getGrid().getYou().getLoc().getCol())){
				if (!getGrid().getActorName(ul).equals("Mho")){
					mhoNextLoc = ul;
				}
			}

		}

		//horizontal is greater than vertical
		if (Math.abs(getGrid().getYou().getLoc().getCol() - mCol) > Math.abs(getGrid().getYou().getLoc().getRow() - mRow)){
			if(mCol > getGrid().getYou().getLoc().getCol()){
				if (!getGrid().getActorName(l).equals("Mho")){
					mhoNextLoc = l;
				}
			}
			if(mCol < getGrid().getYou().getLoc().getCol()){
				if (!getGrid().getActorName(r).equals("Mho")){
					mhoNextLoc = r;
				}
			}

			//vertical is greater than horizontal
			if (Math.abs(getGrid().getYou().getLoc().getCol() - mCol) < Math.abs(getGrid().getYou().getLoc().getRow() - mRow)){
				if(mRow > getGrid().getYou().getLoc().getRow()){
					if (!getGrid().getActorName(u).equals("Mho")){
						mhoNextLoc = u;
					}
				}
				if(mRow < getGrid().getYou().getLoc().getRow()){
					if (!getGrid().getActorName(d).equals("Mho")){
						mhoNextLoc = d;
					}
				}

			}

		}
		return mhoNextLoc;

	}

}


