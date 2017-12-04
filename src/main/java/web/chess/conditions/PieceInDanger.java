package web.chess.conditions;

import java.util.ArrayList;

import web.chess.Board;
import web.chess.Condition;
import web.chess.Piece;
import web.chess.Player;
import web.chess.Slot;

public class PieceInDanger extends Condition {

	private Player enemy;
	private Piece piece;
	private Board board;
	
	public PieceInDanger ( Player enemy, Piece piece, Board board ) {
		
		this.enemy = enemy;
		this.piece = piece;
		this.board = board;
	}
	
	@Override
	public boolean Confirm () {
		
		try {

			for ( int index = 0; index < this.enemy.getPieces().size(); index++ ) {
				
				Slot slot = this.board.getSlot(this.enemy.getPieces().get(index));
				
				if ( slot == null ) { continue; }
				
				ArrayList<Slot> moves = this.enemy.getPieces().get(index).getMoves(this.board, slot);
				
				for ( int x = 0; x < moves.size(); x++ ) {
					
					if ( this.piece == moves.get(x).getPiece() ) {
						
						return true;
					}
				}
			}
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
		
		return false;
	}

}
