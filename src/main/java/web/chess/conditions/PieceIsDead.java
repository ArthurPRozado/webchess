package web.chess.conditions;

import web.chess.Condition;
import web.chess.Piece;
import web.chess.Player;

public class PieceIsDead extends Condition {

	private Player player;
	private Piece piece;
	
	public PieceIsDead ( Player player, Piece piece ) {
		
		this.player = player;
		this.piece = piece;
	}
	
	@Override
	public boolean Confirm () {
		
		try {
		
			return this.player.getDead ().contains ( this.piece );
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
		
		return false;
	}

}
