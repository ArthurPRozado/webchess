package web.chess;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Piece implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 183318410237931629L;
	private int team;
	private String name;
	private Pieces type;
	
	public abstract ArrayList < Slot > getMoves ( Board board, Slot slot );

	public void onMove () {}
	
	public int getTeam () {
		
		return team;
	}

	public void setTeam ( int team ) {
		
		this.team = team;
	}

	protected void setName ( String name ) {
		
		this.name = name;
	}

	public String getName () {
		
		return name;
	}

	protected void setType ( Pieces type ) {
		
		this.type = type;
	}
	
	public Pieces getType () {
		
		return this.type;
	}
}
