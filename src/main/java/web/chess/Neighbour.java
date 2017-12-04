package web.chess;

import web.chess.other.Direction;

public class Neighbour implements Comparable < Neighbour > {

	private Slot slot;
	private Direction dir;
	
	public Neighbour ( Slot slot, Direction dir ) {
		
		this.slot = slot;
		this.dir = dir;
	}

	public Slot getSlot () {
		
		return slot;
	}

	public void setSlot ( Slot slot ) {
		
		this.slot = slot;
	}

	public Direction getDir () {
		
		return dir;
	}

	public void setDir ( Direction dir ) {
		
		this.dir = dir;
	}

	@Override
	public int compareTo ( Neighbour other ) {
		
		if ( other == null ) { return -1; }
		if ( other == this ) { return 0; }
		
		if ( other.slot == this.slot ) { return 0; }
		
		return -1;
	}
	
	
}
