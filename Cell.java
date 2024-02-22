public class Cell {

  //constants for ease of changing out what is stored in each spot
  private final String EMOJI = "\uD83E\uDD0E";
  private final String EMPTY = "\uD83E\uDD0D ";
  //boardSpace is the current status of each space, whether it be an EMOJI or EMPTY
  private String boardSpace;
  private boolean isAlive;
  
  public Cell(boolean isAlive) {
    setIsAlive(isAlive);
    
  }

  public void setIsAlive(boolean isAlive) {
    this.isAlive = isAlive;
    setEmoji();
  }

  public boolean getIsAlive() {
    return isAlive;
  }

  private void setEmoji() {
    if (this.isAlive) {
      boardSpace = EMOJI;
    }
    else {
      boardSpace = EMPTY;
    }
  }

  public String toString() {
    return boardSpace;
  }
}
