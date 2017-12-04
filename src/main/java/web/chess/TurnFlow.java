package web.chess;

import java.util.ArrayList;

import web.chess.other.Axis;

public class TurnFlow {

	public static final int NEXT_TURN = 0;
	public static final int CURRENT_WON = 10;
	public static final int CURRENT_LOST = 20;
	public static final int GAME_OVER = 30;
	
	public static final int FAILED = -10;
	public static final int NON_VALID = -20;
	
	private Board board;
	private Player current;
	private int currentId = -1;
	private final ArrayList < Player > players = new ArrayList < Player > ();
	
	private Slot selectedSlot;
	private Piece selectedPiece;
	public final ArrayList < Slot > selectedPieceMoves = new ArrayList < Slot > ();
	
	private TurnGame game;
	
	private boolean gameOver = false;
	
	private Piece white;
	private Piece black;
	
	public boolean hasSelection () {
		
		if ( this.selectedSlot == null ) { return false; }
		if ( this.selectedPiece == null ) { return false; }
		if ( this.selectedPieceMoves.size() < 1 ) { return false; }
		
		return true;
	}
	
	public void unselect () {
		
		this.selectedSlot = null;
		this.selectedPiece = null;
		this.selectedPieceMoves.clear();
	}
	
	public Player getCurrent () {
		
		return this.current;
	}
	
	public void setPlayers ( ArrayList < Player > players ) {
		
		if ( players == null ) { return; }
		if ( players.size () < 2 ) { return; }
		
		this.current = null;
		this.currentId = -1;
		
		this.players.clear ();
		this.players.addAll ( players );
		
		this.game = new TurnGame ( this.players );
	}
	
	public void start ( Board board ) {
		
		this.board = board;
		
		this.currentId = 0;
		this.current = this.players.get ( this.currentId );
		
		this.unselect ();
		
		this.game = new TurnGame ( this.players );
		this.gameOver = false;
		
		this.white = this.getPieceByType(0, Pieces.KING);
		this.black = this.getPieceByType(1, Pieces.KING);
	}
	
	public int next () {
		
		try {

			System.out.println ( "GAME OVER " + this.gameOver );
			
			if ( this.gameOver ) { return GAME_OVER; }
			
			this.game.addTurn ( new Turn ( this.current, this.currentId, this.board.getClone () ) );
			
			this.currentId++;
			
			if ( this.currentId > this.players.size () - 1 ) {
				
				this.currentId = 0;
			}

			this.current = this.players.get ( this.currentId );
			
			this.unselect ();
			
			Condition condition = this.current.lossCondition;
			
			if ( condition != null ) {
				
				if ( condition.Confirm () ) {
					
					this.gameOver = true;
					
					return CURRENT_LOST;
				}
			}
			
			Piece king = this.getPieceByType ( this.currentId, Pieces.KING );
			
			System.out.println ( "Has King " + this.hasPiece ( king ) );
			
			if ( ! this.hasPiece ( king ) ) {

				this.gameOver = true;
					
				return CURRENT_LOST;
			}
				
			return NEXT_TURN;
			
		} catch ( Exception e ) {
			
			e.printStackTrace ();
		}
		
		return FAILED;
	}
	
	private boolean hasPiece ( Piece piece ) {
		
		for ( int x = 0; x < 8; x++ ) {
			
			for ( int y = 0; y < 8; y++ ) {
				
				if ( piece == this.board.getSlot(x, y).getPiece() ) {
					
					return true;
				}
			}
		}
		
		return false;
	}

	public Piece getPieceByType ( int team, Pieces type ) {

		for ( int x = 0; x < 8; x++ ) {
			
			for ( int y = 0; y < 8; y++ ) {
				
				Piece piece = this.board.getSlot(x, y).getPiece();
				
				if ( piece == null ) { continue; }
				
				if ( piece.getType() == type ) {
					
					if ( piece.getTeam() == team ) {
						
						return piece;
					}
				}
			}
		}
		
		return null;
	}
	
	public ArrayList < Slot > getMoves ( int x, int y ) {

		this.unselect ();
		
		try {
			
			Slot selectedSlot = this.board.getSlot ( x, y );
			
			if ( selectedSlot == null ) { return new ArrayList < Slot > (); }
			
			Piece selectedPiece = selectedSlot.getPiece ();
			
			if ( selectedPiece == null ) { return new ArrayList < Slot > (); }
			if ( this.currentId != selectedPiece.getTeam () ) { return new ArrayList < Slot > (); }

			System.out.println("Team " + ( this.currentId == selectedPiece.getTeam () ) );
			
			this.selectedSlot = selectedSlot;	
			this.selectedPiece = selectedPiece;
			this.selectedPieceMoves.addAll ( this.selectedPiece.getMoves ( this.board, this.selectedSlot ) );
			return this.selectedPieceMoves;
			
		} catch ( Exception e ) {
			
			e.printStackTrace ();
		}
		
		return new ArrayList < Slot > ();
	}
	
	public int doMove ( Slot goal ) {
		
		try {
			
			if ( this.gameOver ) { return GAME_OVER; }
			
			if ( this.selectedSlot == null ) { return FAILED; }
			if ( this.selectedPiece == null ) { return FAILED; }
			//if ( ! this.selectedPieceMoves.contains ( goal ) ) { return NON_VALID; }
			
			Axis goalAxis = this.board.getSlotPos(goal);
			
			if ( goalAxis == null ) { return FAILED; }
			
			ArrayList < Axis > axis = this.getAxis ( this.selectedPieceMoves );
			
			if ( ! selectedPieceMoves.contains ( goal ) ) { return NON_VALID; }
			//if ( ! this.containsAxis ( goalAxis, axis ) ) { return NON_VALID; }
			
			Piece dead = goal.getPiece ();
			
			this.killPiece ( dead, this.current );
			
			this.selectedSlot.setPiece ( null );
			goal.setPiece ( this.selectedPiece );
			
			this.selectedPiece.onMove ();
			
			Condition condition = this.current.winCondition;
			
			if ( condition != null ) {
				
				if ( condition.Confirm () ) {
					
					this.gameOver = true;
					this.game.setWinnerId ( currentId );
					
					return CURRENT_WON;
				}
			}
			
			if ( dead == this.white ) {

				this.gameOver = true;
				this.game.setWinnerId ( currentId );
				
				return CURRENT_WON;
			}
			
			if ( dead == this.black ) {

				this.gameOver = true;
				this.game.setWinnerId ( currentId );
				
				return CURRENT_WON;
			}
			
			return this.next ();
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
		
		return FAILED;
	}

	private ArrayList < Axis > getAxis ( ArrayList < Slot > slots ) {
		
		try {
			
			ArrayList < Axis > axis = new ArrayList < Axis > ();
			
			for ( int index = 0; index < slots.size(); index++ ) {
				
				axis.add ( this.board.getSlotPos ( slots.get ( index ) ) );
			}
			
			return axis;
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
		
		return new ArrayList < Axis > ();
	}
	
	public void killPiece ( Piece dead, Player owner ) {
		
		if ( dead == null ) { return; }
		if ( owner == null ) { return; }
		
		owner.killPiece ( dead );
	}

	public TurnGame getGame () {
		
		return this.game;
	}
}
