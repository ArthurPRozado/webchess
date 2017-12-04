package web.chess;

public class Turn {

	private Player player;
	private int playerId;
	private Board board;
	
	public Turn ( Player player, int playerId, Board board ) {
		
		this.player = player;
		this.playerId = playerId;
		this.board = board;
	}
	
	public Player getPlayer () {
		
		return this.player;
	}
	
	public int getPlayerId () {
		
		return this.playerId;
	}
	
	public Board getBoard () {
		
		return this.board;
	}
}
