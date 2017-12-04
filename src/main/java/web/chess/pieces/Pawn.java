package web.chess.pieces;

import java.util.ArrayList;

import web.chess.Board;
import web.chess.Neighbour;
import web.chess.Piece;
import web.chess.Pieces;
import web.chess.Slot;
import web.chess.other.Direction;

public class Pawn extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5878585775524979619L;
	private boolean moved;
	
	public Pawn ( int team ) {
		
		this.setTeam(team);
		this.setName("Pawn");
		this.setType(Pieces.PAWN);
	}
	
	@Override
	public ArrayList < Slot > getMoves ( Board board, Slot slot ) {

		try {
			
			if ( board == null ) { return new ArrayList < Slot > (); }
			if ( slot == null ) { return new ArrayList < Slot > (); }
			if ( this != slot.getPiece() ) { return new ArrayList < Slot > (); }
			
			ArrayList < Slot > moves = new ArrayList < Slot > ();

			if ( this.getTeam () == 0 ) {

				moves.addAll ( this.dirMove ( board, slot, Direction.D ) );
				moves.addAll ( this.dirCap ( board, slot, Direction.DR ) );
				moves.addAll ( this.dirCap ( board, slot, Direction.DL ) );
			}

			if ( this.getTeam () == 1 ) {

				moves.addAll ( this.dirMove ( board, slot, Direction.U ) );
				moves.addAll ( this.dirCap ( board, slot, Direction.UR ) );
				moves.addAll ( this.dirCap ( board, slot, Direction.UL ) );
			}

			return moves;
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
		
		return new ArrayList < Slot > ();
	}

	private ArrayList < Slot > dirMove ( Board board, Slot slot, Direction dir ) {
		
		try {
			
			if ( slot == null ) { new ArrayList < Slot > (); }
			
			ArrayList < Slot > moves = new ArrayList < Slot > ();
			
			Neighbour neighbour = slot.getNeighbour(dir);
			
			if ( neighbour == null ) { new ArrayList < Slot > (); }
			
			Slot next = neighbour.getSlot ();
			
			if ( next == null ) { new ArrayList < Slot > (); }
			
			Piece dead = next.getPiece ();
			
			if ( dead == null ) {
				
				moves.add(next);
			}
			
			if ( ! this.moved ) {
				
				Neighbour nextNeighbour = next.getNeighbour ( dir );
				
				if ( nextNeighbour == null ) { return moves; }
				
				Slot nextNext = nextNeighbour.getSlot ();
				
				if ( nextNext == null ) { return moves; }
				
				Piece nextDead = nextNext.getPiece ();
				
				if ( nextDead == null ) {
					
					moves.add(nextNext);
				}
			}
			
			return moves;
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
		
		return new ArrayList < Slot > ();
	}
	
	@Override
	public void onMove () {
		
		this.moved = true;
	}
	
	private ArrayList < Slot > dirCap ( Board board, Slot slot, Direction dir ) {

		try {
			
			ArrayList < Slot > moves = new ArrayList < Slot > ();
			
			Neighbour neighbour = slot.getNeighbour(dir);
			
			if ( neighbour == null ) { return new ArrayList < Slot > (); }
			
			Slot next = neighbour.getSlot ();
			
			if ( next == null ) { return new ArrayList < Slot > (); }
			
			Piece nextPiece = next.getPiece ();
			
			if ( nextPiece != null ) {
				
				if ( nextPiece.getTeam() != this.getTeam() ) {

					moves.add(next);	
				}
			}
			
			return moves;
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
		
		return new ArrayList < Slot > ();
	}
}
