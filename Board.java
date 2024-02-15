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
    if(i>0&&j>0) {
      if (board[i - 1][j - 1].getIsAlive()) {
        count += 1;
      }
    }
    if(i>0){
      if(board[i-1][j].getIsAlive()){
        count+=1;
      }
    }
    if(i>0&&j<board.length-1){
      if(board[i-1][j+1].getIsAlive()){
        count+=1;
      }
    }
    if(j>0){
      if(board[i][j-1].getIsAlive()){
        count+=1;
      }
    }
    if(j>0&&i<board.length-1){
      if(board[i+1][j-1].getIsAlive()){
        count+=1;
      }
    }
    if(i<board.length-1&&j<board.length-1){
      if(board[i+1][j+1].getIsAlive()){
        count+=1;
      }
    }
    if(j<board.length-1) {
      if (board[i][j + 1].getIsAlive()) {
        count += 1;
      }
    }
    if(i<board.length-1) {
      if (board[i + 1][j].getIsAlive()) {
        count += 1;
      }
    }

//    for(int x = j-1; x<j+1; j++) {
//      if(j == 0){
//        x = 1;
//      }
//      for (int c = i - 1; c < i + 1; i++) {
//        if(i==0){
//          c = 1;
//        }
//        if(x!=i||c!=j) {
//          if (board[x][c].getIsAlive()) {
//            count += 1;
//          }
//        }
//      }
//    }
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

	//for each space in the nextGenBoard:
	for(int r = 0; r<nextGenBoard.length; r++){
      for(int c = 0; c<nextGenBoard[r].length; c++){
        int number = countLiveNeighbors(r,c);
        if(board[r][c].getIsAlive()){
          if(number==2||number==3) {
            nextGenBoard[r][c].setIsAlive(true);
          }
//          System.out.print("hi");
        }
        else{
          if(number==3) {
            nextGenBoard[r][c].setIsAlive(true);
//            System.out.print("hi");
          }
        }
      }
    }
//    System.out.print("hi");
      //a live cell with 2-3 neighbors survives
      

      //a dead cell with 3 live neighbors becomes live

      //a live cell with 0, 1, or >=4 neighbors dies
  


    //copies all changes simultaneously. this line must be last
    board = nextGenBoard;
  }

}
