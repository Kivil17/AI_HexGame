/*
 * RandomPlayer.java
 *
 * Michael Closson
 */

import java.awt.*;
import java.util.*;

import ai.Cella;
import it.unical.mat.embasp.base.InputProgram;


public class RandomPlayer implements HexPlayer {

	
	static Point p;
	static boolean trovato=false;
	
	public RandomPlayer() {
		p= new Point(5,5);
	}

	
	public static Point nextMove2( GameBoard gb, GameState gs, InputProgram program) {
		

		
		trovato=false;
		while( !trovato ) {
			
			
			try {
				for (int x = 0; x < gb.iRedWidth && !trovato; x++) //unire dlv 
	                for (int y = 0; y < gb.iBlueWidth; y++)
	                	if(!gb.isOccupied(x, y)) {
	                		program.addObjectInput(new Cella(x,y,gb.board[x][y]));
	                		p=  new Point(x,y);
	                		System.out.println(x+" "+y);
	                		trovato=true;
	                		break;
	                	}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return p;
	}

	public void undo() {}


	@Override
	public Point nextMove(GameBoard gb, GameState gs) {
		
		return p; //il gioco chiama questo che a sua volta chiama Nextmove2
	}

}


