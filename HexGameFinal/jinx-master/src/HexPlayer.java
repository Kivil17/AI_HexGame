/*
 * HexPlayer.java
 *
 * Michael Closson
 *
 */

import java.awt.Point;

import it.unical.mat.embasp.base.InputProgram;

/**
 * The HexPlayer interface defines the methods a hex player class must implement.
 */

public interface HexPlayer {
	/**
	 * nextMove is called by the GameManger, the player returns the next move
	 * they wish to play.
	 * @param program 
	 */

    public Point nextMove( GameBoard gb, GameState gs );

	/**
	 * undo is called by the GameManger when the player needs to undo a move,
	 * the player class doesn't need to update the hex canvas, only its internal
	 * state.
	 */

	public void undo();
}


