package web.chess;

import java.util.ArrayList;

import web.chess.other.Direction;

public class Slot {

	private Piece piece;
	private ArrayList<Neighbour> neighbours = new ArrayList<Neighbour>();
	
	public Piece getPiece () {
		
		return piece;
	}
	
	public void setPiece ( Piece piece ) {
		
		this.piece = piece;
	}
	
	public ArrayList < Neighbour > getNeighbours() {
		
		return neighbours;
	}
	
	public void addNeighbour ( Neighbour other ) {
		
		if ( other == null ) { return; }
		if ( other.getSlot() == this ) { return; }
		
		for ( int index = 0; index < this.neighbours.size(); index++ ) {
			
			if ( other.compareTo ( this.neighbours.get ( index ) ) == 0 ) { return; }
		}
		
		this.neighbours.add ( other );
	}
	
	public Neighbour getNeighbour ( Direction dir ) {
		
		for ( int index = 0; index < this.neighbours.size(); index++ ) {
			
			if ( dir == this.neighbours.get(index).getDir() ) {
				
				return this.neighbours.get(index);
			}
		}
		
		return null;
	}
	
}
