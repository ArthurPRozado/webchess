package web.chess;

import java.util.ArrayList;

public class TurnGame {

	private int winnerId = -1;
	
	private ArrayList < Player > players;
	private ArrayList < Turn > turns = new ArrayList < Turn > ();
	
	public TurnGame ( ArrayList < Player > players ) {
		
		this.players = players;
	}
	
	public void addTurn ( Turn turn ) {
		
		if ( turn == null ) { return; }
		
		this.turns.add ( turn );
	}

	public ArrayList < Player > getPlayers () {
		
		return this.players;
	}

	public ArrayList < Turn > getTurns () {
		
		return this.turns;
	}

	public int getWinnerId() {
		return winnerId;
	}

	public void setWinnerId(int winnerId) {
		this.winnerId = winnerId;
	}
}
