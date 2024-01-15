/** 
 *  Game of Life.
 *  Usage: "java GameOfLife fileName"
 *  The file represents the initial board.
 *  The file format is described in the homework document.
 */

public class GameOfLife {

	public static void main(String[] args) {
		String fileName = args[0];
		//// Uncomment the test that you want to execute, and re-compile.
		//// (Run one test at a time).
		test1(fileName);
		//// test2(fileName);
	    //test3(fileName, 3);
		 //play(fileName);
		//count function test square.dat
		//int [][] tester = read (fileName);
		//System.out.println(count(tester,0,0));//1
		//System.out.println(count(tester,2,2));//3
		//System.out.println(count(tester,2,0));//2
		//cellvalue test
		//System.out.println(cellValue(tester,0,0));//
		//System.out.println(cellValue(tester,2,2));//
		//System.out.println(cellValue(tester,2,0));//

	}
	
	// Reads the data file and prints the initial board.
	public static void test1(String fileName) {
		int[][] board = read(fileName);
		print(board);
	}
		
	// Reads the data file, and runs a test that checks 
	// the count and cellValue functions.
	public static void test2(String fileName) {
		int[][] board = read(fileName);
		//// Write here code that tests that the count and cellValue functions
		//// are working properly, and returning the correct values.
	}
		
	// Reads the data file, plays the game for Ngen generations, 
	// and prints the board at the beginning of each generation.
	public static void test3(String fileName, int Ngen) {
		int[][] board = read(fileName);
		for (int gen = 0; gen < Ngen; gen++) {
			System.out.println("Generation " + gen + ":");
			print(board);
			board = evolve(board);
		}
	}
		
	// Reads the data file and plays the game, for ever.
	public static void play(String fileName) {
		int[][] board = read(fileName);
		while (true) {
			show(board);
			board = evolve(board);
		}
	}
	
	// Reads the initial board configuration from the file whose name is fileName, uses the data
	// to construct and populate a 2D array that represents the game board, and returns this array.
	// Live and dead cells are represented by 1 and 0, respectively. The constructed board has 2 extra
	// rows and 2 extra columns, containing zeros. These are the top and the bottom row, and the leftmost
	// and the rightmost columns. Thus the actual board is surrounded by a "frame" of zeros. You can think
	// of this frame as representing the infinite number of dead cells that exist in every direction.
	// This function assumes that the input file contains valid data, and does no input testing.
	public static int[][] read(String fileName) {
		In in = new In(fileName); // Constructs an In object for reading the input file
		int rows = Integer.parseInt(in.readLine());
		int cols = Integer.parseInt(in.readLine());
		int[][] board = new int[rows][cols]; //builds a proper 2D array
		int N = board.length; // number of rows
        int M = board[0].length; // number of columns
		//reads the file line and updating the board
        while (!in.isEmpty())//while loop until the reading of the ducument ends
         { 
        	for (int i = 0; i < N; i++) //fills the 2D array
        	{
        		String row = in.readLine();// takes the next string from the file
        		if (row != null && !row.isEmpty())
        		{
        		    for (int j = 0; j < row.length(); j++) // checks characters on the string
        		    {
        			  char ch = row.charAt(j);//checks the value of the char in the string
        			  if (ch == '.') //dead cell check
        			  {
        				board [i][j] = 0;
        			  }
        			  else if (ch == 'x') //live cell check
        			  {
        				board [i][j] = 1;
        			  }
        			  else 
        			  {
        				board [i][j] = 0;
        			  }
        		    }
        		 }
        		 else //covers the option for an empty row 
        		 {
        		    for (int j = 0; j < M; j++) 
        		    {
        		        board[i][j] = 0;
	
        		    }
        		 }
        		  
        	}
	      }
	    return board;
	  	}

	
	
	// Creates a new board from the given board, using the rules of the game.
	// Uses the cellValue(board,i,j) function to compute the value of each 
	// cell in the new board. Returns the new board.
	public static int[][] evolve(int[][] board) {
		int rows = board.length;
		int columns = board[0].length;
		int [][] nextgen = new int[rows][columns];
		 for (int i = 0; i < rows; i++)
		         {
		         	for (int j = 0; j < columns; j++)
		         	{
		         		nextgen[i][j] = cellValue(board,i,j);
		         	}
		         	
		         }

		return nextgen;
	}

	// Returns the value that cell (i,j) should have in the next generation.
	// If the cell is alive (equals 1) and has fewer than two live neighbors, it dies (becomes 0).
	// If the cell is alive and has two or three live neighbors, it remains alive.
	// If the cell is alive and has more than three live neighbors, it dies.
	// If the cell is dead and and has three live neighbors, it becomes alive.
	// Otherwise the cell does not change. 
	// Assumes that i is at least 1 and at most the number of rows in the board - 1. 
	// Assumes that j is at least 1 and at most the number of columns in the board - 1. 
	// Uses the count(board,i,j) function to count the number of alive neighbors.
	public static int cellValue(int[][] board, int i, int j) {
		int counter = count(board,i,j);//live cell around
		int value = board[i][j];//cell value
		if ((value == 1) && (counter < 2)) return 0;
		else if ((value == 1) && ((counter == 2) || (counter == 3))) return 1;
		else if ((value == 1) && (counter > 3)) return 0;
		else if ((value == 0) && (counter == 3)) return 1;
		else return value;
	}
	
	// Counts and returns the number of living neighbors of the given cell
	// (The cell itself is not counted).
	// Assumes that i is at least 1 and at most the number of rows in the board - 1. 
	// Assumes that j is at least 1 and at most the number of columns in the board - 1. 
	public static int count(int[][] board, int i, int j) {
	int count = 0;
	int rows = board.length;
	int columns = board[0].length;
	//row-1
	if ((i-1 >= 0) && (j-1 >= 0) && (j-1 < columns) && (board[i-1][j-1]== 1)) count++;//board[i-1][j-1]
	if ((i-1 >= 0) && (j < columns) && (board[i-1][j] == 1)) count++;//(board[i-1][j]
	if ((i-1 >= 0) && (j+1 < columns) && (board[i-1][j+1] == 1)) count++;//(board[i-1][j+1]
	//row
	if ((j-1 >= 0) && (board[i][j-1] == 1)) count++;//(board[i][j-1]
	if ((j+1 < columns) && (board[i][j+1] == 1)) count++;//board[i][j+1]
	//row+1
	if ((i+1 < rows) && (j-1 >= 0) && (board[i+1][j-1] == 1)) count++;//board[i+1][j-1]
	if ((i+1 < rows) && (j < columns) && (board[i+1][j] == 1)) count++;//board[i+1][j]
	if ((i+1 <rows) && (j+1 < columns) && (board[i+1][j+1] == 1)) count++;//board[i+1][j+1]
	return count;
		
	}
	
	// Prints the board. Alive and dead cells are printed as 1 and 0, respectively.
   public static void print(int[][] arr) {
		         int rows = arr.length;
		         int columns = arr[0].length;
		         for (int i = 0; i < rows; i++)
		         {
		         	for (int j = 0; j < columns; j++)
		         	{
		         		System.out.printf("%3s", arr[i][j]);
		         	}
		         	System.out.printf("%n%n");
		         }
	}
		
    // Displays the board. Living and dead cells are represented by black and white squares, respectively.
    // We use a fixed-size canvas of 900 pixels by 900 pixels for displaying game boards of different sizes.
    // In order to handle any given board size, we scale the X and Y dimensions according to the board size.
    // This results in the following visual effect: The smaller the board, the larger the squares
	// representing cells.
	public static void show(int[][] board) {
		StdDraw.setCanvasSize(900, 900);
		int rows = board.length;
		int cols = board[0].length;
		StdDraw.setXscale(0, cols);
		StdDraw.setYscale(0, rows);

		// Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
		
		// For each cell (i,j), draws a filled square of size 1 by 1 (remember that the canvas was 
		// already scaled to the dimensions rows by cols, which were read from the data file). 
		// Uses i and j to calculate the (x,y) location of the square's center, i.e. where it
		// will be drawn in the overall canvas. If the cell contains 1, sets the square's color
		// to black; otherwise, sets it to white. In the RGB (Red-Green-Blue) color scheme used by
		// StdDraw, the RGB codes of black and white are, respetively, (0,0,0) and (255,255,255).
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int color = 255 * (1 - board[i][j]);
				StdDraw.setPenColor(color, color, color);
				StdDraw.filledRectangle(j + 0.5, rows - i - 0.5, 0.5, 0.5);
			}
		}
		StdDraw.show();
		StdDraw.pause(100); 
	}
}
