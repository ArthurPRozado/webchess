package web.chess;

import java.util.ArrayList;

import web.chess.other.Axis;

public class TurnFlow {

	public static final int NEXT_TURN = 0;
	public static final int CURRENT_WON = 10;
	public static final int CURRENT_LOST = 20;
	
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
	}
	
	public int next () {
		
		try {

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
					
					return CURRENT_LOST;
				}
			}
			
			return NEXT_TURN;
			
		} catch ( Exception e ) {
			
			e.printStackTrace ();
		}
		
		return FAILED;
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
					
					return CURRENT_WON;
				}
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
	
	private boolean containsAxis ( Axis other, ArrayList < Axis > axis ) {

		try {
			
			System.out.println( "Other " + other.x + " " + other.y );
			
			for ( int index = 0; index < axis.size(); index++ ) {
				
				System.out.println( "Axis " + axis.get ( index ).x + " " + axis.get ( index ).y );
				if ( other.compare ( axis.get ( index ) ) ) { return true; }
			}
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
		}
		
		return false;
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
