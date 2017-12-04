package web.chess.pieces;

import java.util.ArrayList;

import web.chess.Board;
import web.chess.Neighbour;
import web.chess.Piece;
import web.chess.Pieces;
import web.chess.Slot;
import web.chess.other.Direction;

public class Queen extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 237909881409008468L;

	public Queen ( int team ) {
		
		this.setTeam(team);
		this.setName("Queen");
		this.setType(Pieces.QUEEN);
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
		
			Neighbour next = slot.getNeighbour ( dir );
			
			while ( next != null ) {
				
				Slot nSlot = next.getSlot ();
				
				if ( nSlot == null ) { 
				
					next = null;
					continue;
				}
				
				next = nSlot.getNeighbour ( dir );

				moves.add ( nSlot );
				Piece dead = nSlot.getPiece ();
				
				if ( dead == null ) { continue; }
				
				if ( dead.getTeam() == this.getTeam() ) {
					
					moves.remove ( nSlot );
				}
				
				next = null;
			}
			
			return moves;
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
		
		return new ArrayList < Slot > ();
	}

}