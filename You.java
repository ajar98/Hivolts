import java.util.ArrayList;
import java.util.Random;


public class You extends Actor {
        
        Grid g = super.getGrid();
        
        public You(Location loc, Grid gr) {
                super(loc, gr, "You");
        }
        
        public void jump() {
                ArrayList<Location> jumpLocs = new ArrayList<Location>();
                for (int i = 0; i < 12; i++) {
                        for (int j = 0; j < 12; j++) {
                                if ((g.getActorNameArray()[i][j].equals("Fence")) || (g.getActorNameArray()[i][j].equals("null"))) {
                                        jumpLocs.add(new Location(i, j));
                                }
                        }
                }
                Random r = new Random();
                int jumpIndex = r.nextInt(jumpLocs.size());
                move(jumpLocs.get(jumpIndex));
        }
        
        // public ArrayList<Location> adjacentLocations()

}
