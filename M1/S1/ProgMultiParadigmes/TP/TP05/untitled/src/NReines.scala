class Chessboard(side_ : Int) {
  private var board : Array[Array[String]] = Array.fill[String](side_, side_) {"."}
  private def placedIn(piece_ : String, x_ : Int, y_ : Int): Unit = this.board(x_).update(y_, piece_)
  private def apply(x_ : Int, y_ : Int): String = this.board(x_)(y_)
  private def emptyIn(x_ : Int, y_ : Int): Unit = this.placedIn(".", x_, y_)
  private def empty(): Unit = {
    for(i <- 0 to (side_ - 1); j <- 0 to (side_ - 1)) {
      this.emptyIn(i, j)
    }
  }
  override def toString(): String = {
    var display : String = ""
    for(i <- 0 to (side_ - 1)) {
      for(j <- 0 to (side_ - 1)) {
        display += s"${board(i)(j).toString}"
      }
      display += "\n"
    }
    display
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val chessboard = new Chessboard(8)
  }
}