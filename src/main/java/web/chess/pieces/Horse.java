package web.chess.pieces;

import java.util.ArrayList;

import web.chess.Board;
import web.chess.Neighbour;
import web.chess.Piece;
import web.chess.Pieces;
import web.chess.Slot;
import web.chess.other.Direction;

public class Horse extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7322448556077326889L;

	public Horse ( int team ) {
		
		this.setTeam(team);
		this.setName("Horse");
		this.setType(Pieces.HORSE);
	}
	
	@Override
	public ArrayList < Slot > getMoves ( Board board, Slot slot ) {
		
		try {

			if ( board == null ) { return new ArrayList < Slot > (); }
			if ( slot == null ) { return new ArrayList < Slot > (); }
			if ( this != slot.getPiece() ) { return new ArrayList < Slot > (); }
			
			ArrayList < Slot > moves = new ArrayList < Slot > ();

			moves.addAll ( this.dirMove ( board, slot, Direction.U, Direction.R ) );
			moves.addAll ( this.dirMove ( board, slot, Direction.U, Direction.L ) );
			
			moves.addAll ( this.dirMove ( board, slot, Direction.D, Direction.R ) );
			moves.addAll ( this.dirMove ( board, slot, Direction.D, Direction.L ) );
			
			moves.addAll ( this.dirMove ( board, slot, Direction.R, Direction.U ) );
			moves.addAll ( this.dirMove ( board, slot, Direction.R, Direction.D ) );
			
			moves.addAll ( this.dirMove ( board, slot, Direction.L, Direction.U ) );
			moves.addAll ( this.dirMove ( board, slot, Direction.L, Direction.D ) );

			return moves;
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
			
		return new ArrayList < Slot > ();
	}

	private ArrayList < Slot > dirMove ( Board board, Slot slot, Direction dir, Direction capDir ) {
		
		try {
			
			ArrayList < Slot > moves = new ArrayList < Slot > ();
		
			Slot jump = this.jumpAxis ( board, slot, dir );
			
			if ( jump == null ) { return moves; }
			
			Neighbour goal = jump.getNeighbour ( capDir );
			
			if ( goal == null ) { return moves; }
			
			Slot next = goal.getSlot();
			
			if ( next == null ) { return moves; }
			
			moves.add(next);
			Piece dead = next.getPiece();
			
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

	private Slot jumpAxis ( Board board, Slot slot, Direction dir ) {

		try {
			
			int count = 2;
			Slot next = slot;
			
			while ( count > 0 ) {
				
				count--;

				Neighbour n = next.getNeighbour ( dir );
				next = null;
				
				if ( n == null ) { count = -1; continue; }
				
				next = n.getSlot();
				
				if ( next == null ) { count = -1; continue; }
			}
			
			return next;
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}