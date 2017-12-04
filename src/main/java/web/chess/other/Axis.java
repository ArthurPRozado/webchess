package web.chess.other;

public class Axis {

	public int x;
	public int y;
	
	public Axis () {}
	public Axis ( int x, int y ) {
		
		this.x = x;
		this.y = y;
	}
	
	public boolean compare ( Axis other ) {
		
		if ( other == null ) { return false; }
		
		return this.x == other.x && this.y == other.y;
	}
}
