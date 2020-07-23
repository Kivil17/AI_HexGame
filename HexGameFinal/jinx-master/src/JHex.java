

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import ai.Cella;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv.desktop.DLVDesktopService;

/**
 * The JHex class is the main class of the JHex hex game.
 * The JHex class can be used as either an applet or a
 * full application.  JHex creates all the necessary classes
 * to run JHex.
 */

public class JHex extends JApplet
{
	
	private static String encodingResource="encodings/HexGame";
	private static Handler handler;
	
	ButtonPanel buttonPanel;
	PlayerPanel playerPanel;
//	HexCanvas hexCanvas;

//	GameManager gameManager = null;
//	GameBoard gameBoard = null;

	public void init()
	{
		getContentPane().setLayout( new BorderLayout() );

		Globals.gameBoard = new GameBoard( 11, 11 );

		Globals.hexCanvas = new HexCanvas( 11, 11 );
		getContentPane().add( Globals.hexCanvas, "Center" );

		playerPanel = new PlayerPanel();
		getContentPane().add( playerPanel, "South" );

		Globals.gameManager = new GameManager( Globals.hexCanvas, playerPanel );
		Globals.gameManager.start();

		buttonPanel = new ButtonPanel( this );
		getContentPane().add( buttonPanel, "North" );

	}

	/**
	 * The main method is only called when JHex is run as a Java
	 * application.  The main method instantiates a new frame for
	 * JHex applet class and runs the applet in that frame.
	 */

	public static void main(String args[] ) {
		
		
		
		JHexFrame app = new JHexFrame("JHex");
		app.setSize( 600,600 );
		app.show();
		
		
	}
	
	

	/*public void undo() {
		Globals.gameManager.undo();
	}*/

	public void newGame( int a, int b ) {
		Globals.gameManager.stop();

		Globals.hexCanvas.reset( a, b );
		Globals.gameManager = new GameManager( Globals.hexCanvas, playerPanel );
		Globals.gameBoard.resize( a, b );
		Globals.gameManager.start();
	}

}



/**
 * The JHexFrame class exists soley to allow JHex to be
 * played as either an applet or as a application.  When
 * JHex is run as an application, a JHexFrame is created
 * to house the JHex applet class.
 */

class JHexFrame extends JFrame {
		
	public JHexFrame( String title ) {
		super( title );

		JHex applet = new JHex();

		applet.init();
		getContentPane().add( applet, "Center" );
		addWindowListener(
				new WindowAdapter() {
					public void windowClosing( WindowEvent event ) {
						dispose();
						System.exit(0);
					}
				});
		applet.start();
	}
}

