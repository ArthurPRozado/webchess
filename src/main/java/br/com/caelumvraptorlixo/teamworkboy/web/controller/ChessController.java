package br.com.caelumvraptorlixo.teamworkboy.web.controller;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.caelumvraptorlixo.teamworkboy.web.view.BoardView;
import web.chess.GameController;
import web.chess.Slot;
import web.chess.TurnFlow;
import web.chess.other.Axis;

@Controller
public class ChessController {

	@Inject
	private Result result;
	/*
	@Inject
	private ChessManager manager;
	*/
	@Inject
	private static GameController gc = new GameController ();

	public void init() {

		gc.newGame();
		
		Slot[][] slots = this.getSlots ();
		
		//.include("gc", gc)
		result.include("slots", slots).include("gc", gc);

		result.redirectTo(ChessController.class).board();
	}
	
	public void board() {
		
		//GameController gc = (GameController) result.included().get("gc");
		//Slot[][] slots = (Slot[][]) result.included().get("slots");
		Slot [][] slots = this.getSlots ();
		BoardView bv = new BoardView();
		
		result.include("slots", slots).include("bv", bv).include("gc", gc);
	}
	
	private Slot [][] getSlots () {

		Slot[][] slots = new Slot[8][8]; 
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
			
				slots[j][i] = gc.board.getSlot(i, j);
			}
		}
		
		return slots;
	}
	
	@Get("/chess/moves/{x}/{y}")
	public void moves(int x, int y) {

		if(gc == null) {
			result.redirectTo(ChessController.class).init();
		}

		TurnFlow turnFlow = gc.getTurnFlow();
		
		if ( turnFlow.hasSelection () ) {
			
			this.doMove ( x, y );
			
		} else {
			
			this.getMoves ( x, y );
		}
	}
	
	private void getMoves ( int x, int y ) {

		if(gc == null) {
			result.redirectTo(ChessController.class).init();
		}
		
		TurnFlow turnFlow = gc.getTurnFlow();
		
		ArrayList<Slot> slots = new ArrayList<Slot> ();
		
		if ( turnFlow != null ) {
		
			ArrayList<Slot> temp = turnFlow.getMoves(x, y); 
			
			if ( temp != null ) {
				
				if ( temp.size() > 0 ) {

					slots.addAll ( temp );	
				}
			}
		}
		
		ArrayList<Axis> axis = new ArrayList<>(); 
		
		for (Slot slot : slots) {
			
			axis.add(gc.board.getSlotPos(slot));
		
		}
		
		if ( axis.size() > 0 ) {
		
			result.use(Results.json()).from(axis).serialize();
		}
	}
	
	private void doMove ( int x, int y ) {

		if(gc == null) {
			result.redirectTo(ChessController.class).init();
		}
		
		TurnFlow turnFlow = gc.getTurnFlow();
		
		Slot goal = gc.board.getSlot ( x, y ); 
		
		System.out.println( "Turn Flow Valids: " + turnFlow.selectedPieceMoves.size());
		
		int msg = turnFlow.doMove ( goal );
		turnFlow.unselect ();
		
		System.out.println( "Turn Flow MSG: " + msg);
		
		if ( msg < 0 ) {
		
			this.getMoves ( x, y );
		}

		Slot [][] slots = this.getSlots ();
		BoardView bv = new BoardView();
		
		result.include("slots", slots).include("bv", bv).include("gc", gc).include("msg", msg);
	
	}
}
