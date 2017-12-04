package web.chess;

import java.util.ArrayList;

public class Player {

	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	private ArrayList<Piece> dead = new ArrayList<Piece>();

	public Condition winCondition;
	public Condition lossCondition;
	public Condition kingCondition;
	
	public void addPiece ( Piece other ) {
		
		if ( other == null ) { return; }
		if ( this.pieces.contains(other) ) { return; }
		
		this.pieces.add(other);
	}

	public void addPieces ( ArrayList<Piece> others ) {
		
		if ( others == null ) { return; }
		if ( others.size() < 1 ) { return; }
		
		this.pieces.addAll(others);
	}
	
	public void killPiece ( Piece other ) {
		
		if ( other == null ) { return; }
		if ( ! this.pieces.contains(other) ) { return; }
		
		this.dead.add(other);
	}

	public void clearPieces () {
		
		this.pieces.clear();
	}

	public void clearDead () {
		
		this.dead.clear();
	}

	public Piece getPieceByType ( Pieces type ) {
		
		for ( int index = 0; index < this.pieces.size(); index++ ) {
			
			if ( type == this.pieces.get(index).getType() ) {
				
				return this.pieces.get(index);
			}
		}
		
		return null;
	}
	
	public ArrayList<Piece> getPieces () {
		
		return this.pieces;
	}

	public ArrayList<Piece> getDead () {
		
		return this.dead;
	}
	
}
