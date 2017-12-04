package web.chess;

import web.chess.conditions.PieceInDanger;
import web.chess.conditions.PieceIsDead;
import web.chess.other.Axis;
import web.chess.other.Dir;
import web.chess.other.Direction;
import web.chess.pieces.Bishop;
import web.chess.pieces.Horse;
import web.chess.pieces.King;
import web.chess.pieces.Pawn;
import web.chess.pieces.Queen;
import web.chess.pieces.Tower;

public class Board implements Cloneable {

	private Slot [][] board = new Slot[8][8];
	
	public void setBoard ( Player white, Player black ) {

		this.startBoard();
		this.startNeighbours();

		this.addWhitePieces(white);
		this.addBlackPieces(black);
		
		this.winLossConditions(white, black);
	}
	
	private void startBoard () {
		
		for ( int x = 0; x < 8; x++ ) {
			
			for ( int y = 0; y < 8; y++ ) {
				
				this.board[x][y] = new Slot();
			}
		}
	}
	
	private void startNeighbours () {
		
		for ( int x = 0; x < 8; x++ ) {
			
			for ( int y = 0; y < 8; y++ ) {
				
				for ( int index = 0; index < Direction.values().length; index++ ) {
					
					Axis temp = Dir.getDirAxis ( Direction.values () [ index ] );
					
					this.board [ x ][ y ].addNeighbour (
							new Neighbour ( this.getSlot ( x + temp.x, y + temp.y ), Direction.values () [ index ] ) );
				}
			}
		}
	}

	private void winLossConditions ( Player white, Player black ) {
		
		try {
			
			if ( white == null ) { return; }
			if ( black == null ) { return; }
			
			King whiteKing = ( King ) white.getPieceByType(Pieces.KING);
			King blackKing = ( King ) black.getPieceByType(Pieces.KING);
			
			if ( whiteKing == null ) { return; }
			if ( blackKing == null ) { return; }

			white.winCondition = new PieceIsDead(black, blackKing);
			white.lossCondition = new PieceIsDead(white, whiteKing);
			white.kingCondition = new PieceInDanger(black, whiteKing, this);

			black.winCondition = new PieceIsDead(white, whiteKing);
			black.lossCondition = new PieceIsDead(black, blackKing);
			black.kingCondition = new PieceInDanger(white, blackKing, this);
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
	}
	
	private void addWhitePieces ( Player other ) {
		
		if ( other == null ) { return; }
		
		int team = 0;
		
		for ( int index = 0; index < 8; index++ ) {
			
			Pawn temp = new Pawn (team);
			
			other.addPiece(temp);
			this.board[index][6].setPiece( temp );
		}
		
		Tower ta = new Tower (team);
		Tower tb = new Tower (team);
		
		Horse ha = new Horse (team);
		Horse hb = new Horse (team);
		
		Bishop ba = new Bishop (team);
		Bishop bb = new Bishop (team);
		
		Queen q = new Queen (team);
		King k = new King (team);
		
		this.board[0][7].setPiece(ta);
		this.board[7][7].setPiece(tb);
		
		this.board[1][7].setPiece(ha);
		this.board[6][7].setPiece(hb);
		
		this.board[2][7].setPiece(ba);
		this.board[5][7].setPiece(bb);
		
		this.board[3][7].setPiece(q);
		this.board[4][7].setPiece(k);
		
		other.addPiece(ta);
		other.addPiece(tb);
		other.addPiece(ha);
		other.addPiece(hb);
		other.addPiece(ba);
		other.addPiece(bb);
		other.addPiece(q);
		other.addPiece(k);
	}

	private void addBlackPieces ( Player other ) {
		
		if ( other == null ) { return; }
		
		int team = 1;
		
		for ( int index = 0; index < 8; index++ ) {
			
			Pawn temp = new Pawn (team);
			
			other.addPiece(temp);
			this.board[index][1].setPiece( temp );
		}
		
		Tower ta = new Tower (team);
		Tower tb = new Tower (team);
		
		Horse ha = new Horse (team);
		Horse hb = new Horse (team);
		
		Bishop ba = new Bishop (team);
		Bishop bb = new Bishop (team);
		
		Queen q = new Queen (team);
		King k = new King (team);
		
		this.board[0][0].setPiece(ta);
		this.board[7][0].setPiece(tb);
		
		this.board[1][0].setPiece(ha);
		this.board[6][0].setPiece(hb);
		
		this.board[2][0].setPiece(ba);
		this.board[5][0].setPiece(bb);
		
		this.board[3][0].setPiece(q);
		this.board[4][0].setPiece(k);
		
		other.addPiece(ta);
		other.addPiece(tb);
		other.addPiece(ha);
		other.addPiece(hb);
		other.addPiece(ba);
		other.addPiece(bb);
		other.addPiece(q);
		other.addPiece(k);
		
		other.lossCondition = new PieceIsDead(other, k);
	}

	public Slot getSlot ( int x, int y ) {
		
		try {

			if ( x < 0 ) { return null; }
			if ( y < 0 ) { return null; }

			if ( x > 7 ) { return null; }
			if ( y > 7 ) { return null; }
			
			return this.board [x][y];
			
		} catch ( Exception e ) {
			
			System.out.println ( e.getMessage() );
		}
		
		return null;
	}

	public Slot getSlot ( Piece other ) {
		
		try {
			
			if ( other == null ) { return null; }

			for ( int x = 0; x < 8; x++ ) {
				
				for ( int y = 0; y < 8; y++ ) {
					
					if ( other == this.board[x][y].getPiece() ) {
						
						return this.board[x][y];
					}
				}
			}
			
		} catch ( Exception e ) {
			
			System.out.println ( e.getMessage() );
		}
		
		return null;
	}

	public Axis getSlotPos ( Slot other ) {
		
		for ( int x = 0; x < 8; x++ ) {
			
			for ( int y = 0; y < 8; y++ ) {
				
				if ( this.board [ x ][ y ] == other ) {
					
					return new Axis ( x, y );
				}
			}
		}
		
		return new Axis ( -1, -1 );
	}
	
	public Board getClone () {
		
		try {
			
			return ( Board ) this.clone();
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}
