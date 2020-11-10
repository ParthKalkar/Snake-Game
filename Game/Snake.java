package week7;

import java.util.ArrayList;
import java.util.Random;

public class Snake {
	boolean alive = true;
	ArrayList<Position> positions = new ArrayList<Position>();
	Position head;

	/**
	 * Constructor
	 * 
	 * @param row    Row of the head
	 * @param column Column of the head
	 */
	
	public Snake(int row, int column) {
		alive = true;
		
		Position pos = new Position(row, column);
		this.positions.add(0, pos);
		Position pos1 = new Position(row-1, column);
		this.positions.add(1, pos1);
		Position pos2 = new Position(row-2, column);
		this.positions.add(2, pos2);
		
	}

	public Position getSec() {
		head = this.positions.get(1);
		return head;
	}

	public Position getHead() {
		head = this.positions.get(0);
		return head;
	}
	
	public String randMove() {
	    String[] directions = { "N", "S", "W", "E" };
	    Random r = new Random();
	    int ind = r.nextInt(4);
	    String st = directions[ind];
	    return st;
	}
	    
	    public void Move(int row, int column, boolean apple) {
		Position pos = new Position(row, column);
		this.positions.add(0, pos);
		if (!apple)
			this.positions.remove(this.positions.size()-1);
		
	}
}
