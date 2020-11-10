package week7;

import java.util.Random;

public class Bot_p_kalkar implements Bot {
	public Direction chooseDirection() {
		
		    String[] directions = { "N", "S", "W", "E" };
		    Random r = new Random();
		    int ind = r.nextInt(4);
		    String st = directions[ind];
		    if (st== "N")
		    	return Direction.NORTH;
		    else if (st== "S")
		    	return Direction.SOUTH;
		    else if (st== "E")
		    	return Direction.EAST;
		    else
		    	return Direction.WEST;
	}

}
