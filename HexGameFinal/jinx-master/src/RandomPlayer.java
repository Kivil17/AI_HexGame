/*
 * RandomPlayer.java
 *
 * Michael Closson
 */

import java.awt.*;
import java.util.*;

import ai.Cella;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv.desktop.DLVDesktopService;

import it.unical.mat.embasp.base.Handler;



public class RandomPlayer implements HexPlayer {

	private static String encodingResource="HexEmbasp/encodings/HexGame";
	private static Handler handler;
	static Point p;
	static boolean trovato=false;
	
	Cella cella=null;
	
	public RandomPlayer() {
		p= new Point(5,5);
	}

	
	/*public static Point nextMove2(GameBoard gb, GameState gs, InputProgram program) {
		

		
		trovato=false;
		while( !trovato ) {
			
			
			
				for (int x = 0; x < gb.iRedWidth && !trovato; x++) //unire dlv 
	                for (int y = 0; y < gb.iBlueWidth; y++)
	                	if(!gb.isOccupied(x, y)) {
	                		try {
		                		program.addObjectInput(new Cella(x,y,gb.board[x][y]));
		                		//p=  new Point(x,y);
		                		//System.out.println(x+" "+y);
		                		trovato=true;
		                		break;
		                	} catch (Exception e) {
		                		// TODO Auto-generated catch block
		                		e.printStackTrace();
		                	}
	                	}
			
			
		}
		
		return p;
	}*/

	public void undo() {}


	@Override
	public Point nextMove(GameBoard gb, GameState gs) {
		
		int n=0;
		int x, y; 
		int t1=0;
		int t2=0;
		
		
		//DICHIARE TUTTO IL NECESSARIOPER DLV
		
		handler = new DesktopHandler(new DLVDesktopService("HexEmbasp/lib/dlv.mingw.exe"));
		
		InputProgram  program = new ASPInputProgram();
		program.addFilesPath(encodingResource);
		
		handler.addProgram(program);
		
		
		try {
			ASPMapper.getInstance().registerClass(Cella.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		
		//CHIAMA TROVA FATTI IN GAMEBOARD CHE DOVREBBE SALVARE I FATTI TROVATI DA HEXGAME NELLA CLASSE CELLA DI JAVA
		try {
			gb.trovaFatti(program, cella, gb, gs);
					
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//SCORRO TUTTE LE OCCORRENZE DELL'ATOMO CELLA
		for(AnswerSet a:answers.getAnswersets()){
			 System.out.println("AS n.: " + ++n + ": " + a);
			try {

				for(Object obj:a.getAtoms()){
					if(obj instanceof Cella)  {
						Cella cella = (Cella) obj;
						t1=cella.getX();
						t2=cella.getY();
						break;
					}
				}
				System.out.println();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		}
		
		

		// pull random numbers until an empty space is found.

		//ASSEGNO AD X E Y LE COORDINATE DEL PRIMO ANSWER SET UTILE TROVATO
		while( true ) {
			x = t1;
			y = t2;

			if( ! gb.isOccupied( x, y ) )
				return new Point( x, y ); //RITORNO IL POINT AL GAMEMANAGER CHE LO UTILIZZERA PER PIAZZARE LA MOSSA 
		}
		
		
		
		
	}


	/*public static  Point nextMove3(int x, int y) {
		
		p=new Point(x,y);
		
		return p;
	}*/

}


