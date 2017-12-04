package br.com.caelumvraptorlixo.teamworkboy.web.view;

import web.chess.Pieces;
import web.chess.Slot;

public class BoardView {
	
	private static String WHITE = "#FFFFFF";
	private static String BLACK = "#000000";
	private static String ORANGE = "#FF7619";
	
	private String white = WHITE;
	private String black = BLACK;
	
	private int theme = 0;
	
	public void cicleTheme () {
		
		this.theme++;
		
		if ( this.theme > 1 ) { this.theme = 0; }

		if ( this.theme == 0 ) {
			
			this.black = BLACK;
		}
		
		if ( this.theme == 1 ) {
			
			this.black = ORANGE;
		}
	}
	
	public String getTile(int i) {
		
		String url;
		
		if(i == 0) {
			
			url = this.white;
		} else {
			
			url = this.black;
		}
		
		return url;
	}

	public String getPieceImg ( Slot other ) {
		
		if ( other == null ) { return ""; }
		if ( other.getPiece () == null ) { return ""; }
		
		Pieces type = other.getPiece().getType();

		if ( other.getPiece().getTeam() == 0 ) {
			
			if ( type == Pieces.PAWN ) {
				
				return "https://cdn3.iconfinder.com/data/icons/chess-2/512/640619-pawn_chess_piece-64.png";
			}
			
			if ( type == Pieces.TOWER ) {
				
				return "https://cdn3.iconfinder.com/data/icons/chess-2/512/640600-rook_piece_chess_strategy-64.png";
			}
	
			if ( type == Pieces.HORSE ) {
				
				return "https://cdn3.iconfinder.com/data/icons/chess-2/512/640615-knight_piece_chess-64.png";
			}
	
			if ( type == Pieces.BISHOP ) {
				
				return "https://cdn3.iconfinder.com/data/icons/chess-2/512/640623-bishop_chess_piece-64.png";
			}
	
			if ( type == Pieces.QUEEN ) {
				
				return "https://cdn3.iconfinder.com/data/icons/chess-2/512/640621-queen_chess_piece-64.png";
			}
	
			if ( type == Pieces.KING ) {
				
				return "https://cdn3.iconfinder.com/data/icons/chess-2/512/640622-king_chess_piece-64.png";
			}
			
		}
		
		if ( other.getPiece().getTeam() == 1 ) {
			
			if ( type == Pieces.PAWN ) {
				
				return "https://cdn3.iconfinder.com/data/icons/chess-2/512/640608-pawn_chess_piece-64.png";
			}
			
			if ( type == Pieces.TOWER ) {
				
				return "https://cdn3.iconfinder.com/data/icons/chess-2/512/640601-rook_chess_piece-64.png";
			}
	
			if ( type == Pieces.HORSE ) {
				
				return "https://cdn3.iconfinder.com/data/icons/chess-2/512/640610-knight_piece_chess-64.png";
			}
	
			if ( type == Pieces.BISHOP ) {
				
				return "https://cdn3.iconfinder.com/data/icons/chess-2/512/640607-bishop_chess_piece-64.png";
			}
	
			if ( type == Pieces.QUEEN ) {
				
				return "https://cdn3.iconfinder.com/data/icons/chess-2/512/640584-queen_chess_piece-64.png";
			}
	
			if ( type == Pieces.KING ) {
				
				return "https://cdn3.iconfinder.com/data/icons/chess-2/512/640609-king_chess_piece-64.png";
			}
			
		}
		
		return "";
	}
}
