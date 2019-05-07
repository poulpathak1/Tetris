import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * An L-Shape piece in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration:
 * 
 * Sq <br>
 * Sq <br>
 * Sq Sq <br>
 * 
 * The game piece "floats above" the Grid. The (row, col) coordinates are the
 * location of the middle Square on the side within the Grid
 * 
 * 
 */
public class SquareShape extends AbstractPiece {
	/*
	
	private boolean ableToMove; // can this piece move

	private Square[] square; // the squares that make up this piece

	// Made up of PIECE_COUNT squares
	private Grid grid; // the board this piece is on

	// number of squares in one Tetris game piece
	private static final int PIECE_COUNT = 4;


*/ 
	
	
	
	
	/**
	 * Creates an L-Shape piece. See class description for actual location of r
	 * and c
	 * 
	 * @param r
	 *            row location for this piece
	 * @param c
	 *            column location for this piece
	 * @param g
	 *            the grid for this game piece
	 * 
	 */
	public SquareShape(int r, int c, Grid g) {
		
		super(r,c,g);

		// Create the squares
		square[0] = new Square(g, r, c, Color.gray, true);  
		square[1] = new Square(g, r, c+1, Color.gray, true);      
		square[2] = new Square(g, r + 1, c, Color.gray, true);  
		square[3] = new Square(g, r + 1, c + 1, Color.gray, true);
		
	}

	
	

	

	
}
 