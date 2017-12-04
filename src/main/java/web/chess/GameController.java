package web.chess;

import java.util.ArrayList;

public class GameController {

	public Board board = new Board ();

	private ArrayList<Player> players = new ArrayList<Player>();
	
	private TurnFlow turnFlow = new TurnFlow ();
	
	public void newGame () {
		
		this.players.clear();
		this.players.add(new Player());
		this.players.add(new Player());
		
		this.board.setBoard (this.players.get(0), this.players.get(1));
		
		this.turnFlow.setPlayers ( this.players );
		this.turnFlow.start ( this.board );
	}
	
	public TurnFlow getTurnFlow () {
		
		return this.turnFlow;
	}
	
	public void movePiece ( Piece piece, Board board, int fromX, int fromY, int goalX, int goalY ) {
		
		if ( piece == null ) { return; }
		if ( board == null ) { return; }
		
		Slot from = board.getSlot(fromX, fromY);
		Slot goal = board.getSlot(goalX, goalY);
		
		if ( from == null ) { return; }
		if ( goal == null ) { return; }
		
		if ( from.getPiece () != piece ) { return; }
		if ( goal.getPiece () == piece ) { return; }
		
		Piece dead = goal.getPiece ();
		
		this.killPiece(dead, this.getPlayer(dead.getTeam()));
		
		from.setPiece(null);
		goal.setPiece(piece);
		
	}
	
	public void killPiece ( Piece dead, Player owner ) {
		
		if ( dead == null ) { return; }
		if ( owner == null ) { return; }
		
		owner.killPiece(dead);
	}

	public void addPlayer ( Player other ) {
		
		if ( other == null ) { return; }
		
		this.players.add(other);
	}

	public void addPlayers ( ArrayList<Player> other ) {
		
		if ( other == null ) { return; }
		if ( other.size() < 1 ) { return; }
		
		this.players.addAll(other);
	}

	public Player getPlayer ( int index ) {
		
		return this.players.get(index);
	}

	public ArrayList<Player> getPlayers () {
		
		return this.players;
	}
}
