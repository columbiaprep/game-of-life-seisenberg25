public class Board {

  private Cell[][] board;

  public Board(int rows, int cols) {
    board = new Cell[rows][cols];
    clearBoard();
    placeFirstGen();
    displayBoard();
  }

  //loops through 2D array and sets every char to the default emoji
  public void clearBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = new Cell(false);
      }
    }
  }
  public boolean checkEnd(){
    boolean end = false;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if(board[i][j].getIsAlive()){
          end = true;
        }
      }
    }
    return end;
  }

  //prints the board
  public void displayBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + "\t");
      }
      System.out.println();
    }
    System.out.println();
  }

  //places first generation of bacteria on board
  public void placeFirstGen() {
    board[0][3] = new Cell(true);
    board[0][4] = new Cell(true);
    board[0][5] = new Cell(true);
    board[1][3] = new Cell(true);
    board[1][5] = new Cell(true);
    board[2][5] = new Cell(true);
    board[4][4] = new Cell(true);
    board[3][3] = new Cell(true);
    board[4][3] = new Cell(true);
    board[4][5] = new Cell(true);
    board[3][4] = new Cell(true);
  }

  //counts the number of neighbors who are alive, returns the result as an integer
	//counts all eighth neighboring spaces
  public int countLiveNeighbors(int i, int j) {
    int count = 0;
    int end = board.length - 1;
    // if row is bigger than 0
    if (i > 0) {
      // if column is also bigger than beggining, check if board part row-1 and column-1 is alive
      if (j > 0 && board[i - 1][j - 1].getIsAlive()) {count += 1;}
      // if row-1 and column is alive
      if (board[i - 1][j].getIsAlive()) {count += 1;}
      // if column is also smaller than end, check if board part row-1 and column+1 is alive
      if (j < end && board[i - 1][j + 1].getIsAlive()) {count += 1;}
    }
    // if column is bigger than 0
    if (j > 0) {
      // if row and column-1 is alive
      if (board[i][j - 1].getIsAlive()) {count += 1;}
      // if row is also smaller than end, check if board part row+1 and column-1 is alive
      if (i < end && board[i + 1][j - 1].getIsAlive()) {count += 1;}
    }
    // if row is less than end
    if(i<end){
      // and column is less than end, check if board part row+1 and column+1 is alive
      if(j<end && board[i+1][j+1].getIsAlive()){count+=1;}
      // check if board part row+1 and column is alive
      if(board[i + 1][j].getIsAlive()) {count += 1;}
    }
    // if column is less than end, check if board part row and column+1 is alive
    if(j<end && board[i][j + 1].getIsAlive()) {count += 1;}
    return count;
  }

  public void createNewGeneration() {
    //creates a blank board of same size called newGenBoard
    Cell[][] nextGenBoard = new Cell[board.length][board[0].length];
    for (int i = 0; i < nextGenBoard.length; i++) {
      for (int j = 0; j < nextGenBoard[i].length; j++) {
        nextGenBoard[i][j] = new Cell(false);
      }
    }
//    System.out.print("hi");

    //all changes should be reflected only on nextGenBoard, and we copy them over on the last line of the method

	//for each row in the nextGenBoard:
	for(int r = 0; r<nextGenBoard.length; r++){
      //for each column in the row
      for(int c = 0; c<nextGenBoard[r].length; c++){
        // get number of live neighbors
        int number = countLiveNeighbors(r,c);
        // if the spot in last generation is alive, and it has 2 or 3 live neighbors, the same spot it alive in next generation
        if(board[r][c].getIsAlive()){
          if(number==2||number==3) {
            nextGenBoard[r][c].setIsAlive(true);
          }
        }
        // if the spot in last generation is dead, and it has 3 or 6 live neighbors, the same spot is alive in next generation
        else{
          if(number==3) {
            nextGenBoard[r][c].setIsAlive(true);
          }
          if(number==6){
            nextGenBoard[r][c].setIsAlive(true);
          }
        }
      }
    }
    //copies all changes simultaneously. this line must be last
    board = nextGenBoard;
  }

}
