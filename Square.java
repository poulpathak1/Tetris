import java.awt.Color;
import java.awt.Graphics;

/**
 * One Square on our Tetris Grid or one square in our Tetris game piece
 * 
 * 
 */
public class Square {
	private Grid grid; // the environment where this Square is

	private int row, col; // the grid location of this Square

	private boolean ableToMove; // true if this Square can move

	private Color color; // the color of this Square

	// possible move directions are defined by the Game class

	// dimensions of a Square
	public static final int WIDTH = 20;
	public static final int HOLD_WIDTH = 5;
	public static final int HEIGHT = 20;
	public static final int HOLD_HEIGHT = 5;
	/**
	 * Creates a square
	 * 
	 * @param g
	 *            the Grid for this Square
	 * @param row
	 *            the row of this Square in the Grid
	 * @param col
	 *            the column of this Square in the Grid
	 * @param c
	 *            the Color of this Square
	 * @param mobile
	 *            true if this Square can move
	 * 
	 * @throws IllegalArgumentException
	 *             if row and col not within the Grid
	 */
	public Square(Grid g, int row, int col, Color c, boolean mobile) {
		if (row < 0 || row > Grid.HEIGHT - 1)
			throw new IllegalArgumentException("Invalid row =" + row);
		if (col < 0 || col > Grid.WIDTH - 1)
			throw new IllegalArgumentException("Invalid column  = " + col);
		
		

		// initialize instance variables
		grid = g;
		this.row = row;
		this.col = col;
		color = c;
		ableToMove = mobile;
	}

	/**
	 * Returns the row for this Square
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Returns the column for this Square
	 */
	public int getCol() {
		return col;
	}
	
	
	public void setRow(int a) {
		row=a;
	}
	
	public void setCol(int a) {
		col=a;
	}
	
	

	/**
	 * Returns true if this Square can move 1 spot in direction d
	 * 
	 * @param direction
	 *            the direction to test for possible move
	 */
	public boolean canMove(Direction direction) {
		if (!ableToMove)
			return false;

		boolean move = true;
		// if the given direction is blocked, we can't move
		// remember to check the edges of the grid
		switch (direction) {
		case DOWN:
			if (row == (Grid.HEIGHT - 1) || grid.isSet(row + 1, col))
				move = false;
			break;

		// currently doesn't support checking LEFT or RIGHT
		// MODIFY so that it correctly returns if it can move left or right
		case LEFT:
                        if(col==0 || grid.isSet(row, col-1)) 
                            move=false;
                        break;
		case RIGHT:
                        // INSERT YOUR CODE HERE
						if(col==WIDTH-1 || grid.isSet(row, col+1)) 
							move=false;
                        break;
		}
		return move;
	}

	public boolean canRotate() {
		// TODO Auto-generated method stub
		if (!ableToMove)
			return false;
		
		boolean move=true;
		
		if (row == (Grid.HEIGHT - 1) || grid.isSet(row + 1, col)) {
			move = false;
		}
		
		if(col==0 || grid.isSet(row, col)  ||  col==(Grid.WIDTH-1) || grid.isSet(row, col)  ) {
            move=false;
		}
		
		
		return move;
		
		
	}
	
	
	
	/**
	 * moves this square in the given direction if possible.
	 * 
	 * The square will not move if the direction is blocked, or if the square is
	 * unable to move.
	 * 
	 * If it attempts to move DOWN and it can't, the square is frozen and cannot
	 * move anymore
	 * 
	 * @param direction
	 *            the direction to move
	 */
	public void move(Direction direction) {
		if (canMove(direction)) {
			switch (direction) {
			case DOWN:
				row++;
				break;
            case LEFT:
                 col--;
                 break;
            case RIGHT:
                 col++;           //INSERT YOUR CODE HERE
                 break;
			}
		}
	}

	
	
	public void rotate(int a, int b) {
		if (canRotate()) {
			boolean canRotate= true;
			
			int index1RowPos=a;
			int index1ColPos=b;

			
				
					
					int finalRow=this.row;
					int finalCol=this.col;
					
					
					int newRow = index1RowPos;
					int newCol=index1ColPos;
					
					if (this.getRow()==index1RowPos-1) {
						newCol=index1ColPos+1;
						
					}
					
					if (this.getCol()==index1ColPos-1) {
						newRow=index1RowPos-1;
					}
					
					if (this.getRow()==index1RowPos+1) {
						newCol=index1ColPos-1;
						
					}
					
					if (this.getCol()==index1ColPos+1) {
						newRow=index1RowPos+1;
					}
					
					if (this.getCol()==index1ColPos+2) {
						newRow=index1RowPos+2;
					}
					
					if (this.getCol()==index1ColPos-2) {
						newRow=index1RowPos-2;
					}
					
					if (this.getRow()==index1RowPos+2) {
						newCol=index1ColPos-2;
						
					}
					
					if (this.getRow()==index1RowPos+2) {
						newCol=index1ColPos-2;
						
					}
					
					
					if ( canRotate()) {
						
						finalRow=newRow;
						finalCol=newCol;
					}
					
					
					
					
					
					this.setRow(finalRow);
					this.setCol(finalCol);
					
				
					
		}
			
			
		
		
	}
	
	
	
	/**
	 * Changes the color of this square
	 * 
	 * @param c
	 *            the new color
	 */
	public void setColor(Color c) {
		color = c;
	}

	/**
	 * Gets the color of this square
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Draws this square on the given graphics context
	 */
	public void draw(Graphics g) {

		// calculate the upper left (x,y) coordinate of this square
		int actualX = Grid.LEFT + col * WIDTH;
		int actualHoldX = Grid.HOLD_LEFT + col*HOLD_WIDTH;
		int actualY = Grid.TOP + row * HEIGHT;
		int actualHoldY = Grid.TOP + row * HOLD_HEIGHT;
		
		
		
		g.setColor(color);
		g.fillRect(actualX, actualY, WIDTH, HEIGHT);
		g.fillRect(actualHoldX, actualHoldY, HOLD_WIDTH, HOLD_HEIGHT);
		
		// black border (if not empty)
		if (!color.equals(Grid.EMPTY)) {
			g.setColor(Color.BLACK);
			g.drawRect(actualX, actualY, WIDTH, HEIGHT);
		}
		
		
		
		if (!color.equals(Grid.EMPTY)) {
			g.setColor(Color.BLACK);
			g.drawRect(actualHoldX, actualHoldY, HOLD_WIDTH, HOLD_HEIGHT);
		}
	
		
	}

	

	
	
}
