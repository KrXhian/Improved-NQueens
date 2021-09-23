public class optimizedVenerableNQueens 
{         
   public static void main(String[] args) 
   { 
      final double startTime=System.nanoTime();
      optimizedVenerableNQueens Queen = new optimizedVenerableNQueens(); 
   	Queen.checkNQ(); 
      double endTime=System.nanoTime();
      final double timeDuration=(endTime-startTime);
      System.out.println("\nTime Duration: " + timeDuration/1000000000 + "ms"); 
   }
   
   /** 
   * Size of the chessboard. 
   */
   static int N = 8;
   
   
   /** 
   * Helper array to check the row in the current position of a placed queen for a possible conflict.
   * The size of the array will be N+N to allot more storage spaces if needed.  
   */
   static int []checkRow = new int[N+N];
   
   /** 
   * Helper array to check the left to right diagonal in the current position of a placed queen for a possible conflict.
   * The size of the array will be N+N to allot more storage spaces if needed.  
   */ 
   static int []leftToRight = new int[N+N]; 
   
   /** 
   * Helper array to check the right to left diagonal in the current position of a placed queen for a possible conflict.
   * The size of the array will be N+N to allot more storage spaces if needed.  
   */
   static int []rightToLeft = new int[N+N];
   
   /** 
   * Uses the solve function to backtrack and find solutions to the puzzle.
   * Will only print one of the many feasible solutions.
   */
   static boolean checkNQ() 
   { 
   	int board[][] = new int[N][N];
   
   	if (!solve(board, 0)) 
   	{ 
   		System.out.printf("No Solution!"); 
   		return false; 
   	} 
   	printBoard(board); 
   	return true; 
   }
    
   /** 
   * Places and Removes Queens in a positon recursively. 
   * Used by the checkNQ as a to backtrack and reposition queens whenever an encounter occured.
   */     
   static boolean solve(int board[][], int column) 
   {
      /** 
      * Base case: whenever all the queens are placed in the safe spots. 
      */
   	if (column >= N) 
      {
   		return true; 
      }
   
   	for (int i = 0; i < N; i++) 
   	{  
         /** 
         * If a queen is not under attack then place the queen in the spot.
         */
   		if (noQueen(board, i, column)) 
   		{ 
            //Places the queen in the cell/tile.
   			board[i][column] = checkRow[i] = leftToRight[(i - column) + (N - 1)] = rightToLeft[i + column] = 1;
   			if (solve(board, column + 1))
            { 
   				return true; 
            }
            
            /** 
            * If a queen is under attack then remove the queen in the spot.
            */
            else
            {
               //Removes the queen in the cell/tile.
   			   board[i][column] = checkRow[i] = leftToRight[(i - column) + (N - 1)] = rightToLeft[i + column] = 0;
            }
   		} 
   	} 
   	return false; 
   }  
   
   /** 
   * The combined version of the RowIsClear, UpperDiagIsClear and LowerDiagIsClear function.
   * Checks the rows, upper and lower diagonal of a partially filled board and location.
   */   
   static boolean noQueen(int board[][], int row, int column) 
	{ 
		int i, j;
        
      /**
      * Checks the rows of a queen's specific location to check for possible attack.
      * uses the rows as an index when traversing the array. 
      */
      for (i = 0; i < column; i++)
      {
         if (checkRow[row] == 1)
         {
            return false;
         }
      }

      /**
      * Checks the upper left diagonal of a queen's specific location to check for possible attack.
      * Uses the difference of the row and column coordinate as an index, 
         Instead of relying on the incrementation or decrementation of the for loops to traverse the array.
      * Instantly jump into a specific coordinate instantly.
      */
      for (i = row, j = column; j >= 0 && i < N; i--, j--)
      {
         if (leftToRight[(row - column) + (N -1)] == 1)
         { 
      	   return false;
         } 
      }
            
      /**
      * Checks the lower left diagonal of a queen's specific location to check for possible attack.
      * Uses the sum of the row and column coordinate as an index, 
         Instead of relying on the incrementation or decrementation of the for loops to traverse the array.
      * Instantly jump into a specific coordinate instantly.
      */      
      for (i = row, j = column; i >= 0 && j >= 0; i++, j--)
      {
         if (rightToLeft[row + column] == 1)
         { 
      	   return false;
         }
      }
      
	   return true; 
	}
   
   /** 
   * Prints the chessboard with a specific solution placed.
   */   
   static void printBoard(int board[][]) 
   { 
   	for (int i = 0; i < N; i++) 
   	{ 
   		for (int j = 0; j < N; j++)
         { 
   			System.out.printf(" %d ", board[i][j]);
         } 
   		System.out.printf("\n"); 
   	} 
   } 
} 