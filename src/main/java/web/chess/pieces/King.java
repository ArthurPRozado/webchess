package web.chess.pieces;

import java.util.ArrayList;

import web.chess.Board;
import web.chess.Neighbour;
import web.chess.Piece;
import web.chess.Pieces;
import web.chess.Slot;
import web.chess.other.Direction;

public class King extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1757650892484038893L;

	public King ( int team ) {
		
		this.setTeam(team);
		this.setName("King");
		this.setType(Pieces.KING);
	}
	
	@Override
	public ArrayList < Slot > getMoves ( Board board, Slot slot ) {

		try {
			
			if ( board == null ) { return new ArrayList < Slot > (); }
			if ( slot == null ) { return new ArrayList < Slot > (); }
			if ( this != slot.getPiece() ) { return new ArrayList < Slot > (); }
			
			ArrayList < Slot > moves = new ArrayList < Slot > ();

			moves.addAll ( this.dirMove ( board, slot, Direction.U ) );
			moves.addAll ( this.dirMove ( board, slot, Direction.D ) );
			moves.addAll ( this.dirMove ( board, slot, Direction.R ) );
			moves.addAll ( this.dirMove ( board, slot, Direction.L ) );
			moves.addAll ( this.dirMove ( board, slot, Direction.UR ) );
			moves.addAll ( this.dirMove ( board, slot, Direction.UL ) );
			moves.addAll ( this.dirMove ( board, slot, Direction.DR ) );
			moves.addAll ( this.dirMove ( board, slot, Direction.DL ) );

			return moves;
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
		
		return new ArrayList < Slot > ();
	}

	private ArrayList < Slot > dirMove ( Board board, Slot slot, Direction dir ) {
		
		try {
			
			ArrayList < Slot > moves = new ArrayList < Slot > ();
		
			Slot next = slot;
			
			Neighbour n = next.getNeighbour ( dir );
				
			if ( n == null ) { return moves; }
				
			next = n.getSlot();
				
			if ( next == null ) { return moves;  }
				
			moves.add(next);
			Piece dead = next.getPiece ();
				
			if ( dead == null ) { return moves; }
				
			if ( dead.getTeam() == this.getTeam() ) {
					
				moves.remove(next);
			}
				
			return moves;
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
		
		return new ArrayList < Slot > ();
	}

}