package web.chess.other;

public class Dir {

	public static Axis getDirAxis ( Direction dir ) {
		
		if ( dir == Direction.U ) { return new Axis ( 0, 1 ); }
		if ( dir == Direction.D ) { return new Axis ( 0, -1 ); }
		if ( dir == Direction.L ) { return new Axis ( -1, 0 ); }
		if ( dir == Direction.R ) { return new Axis ( 1, 0 ); }
		
		if ( dir == Direction.UR ) { return new Axis ( 1, 1 ); }
		if ( dir == Direction.UL ) { return new Axis ( -1, 1 ); }
		if ( dir == Direction.DR ) { return new Axis ( 1, -1 ); }
		if ( dir == Direction.DL ) { return new Axis ( -1, -1 ); }
		
		return new Axis ();
	}
}
