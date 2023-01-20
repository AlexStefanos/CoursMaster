class Sudoku(startConfig_ : Array[Array[Int]]) {
  private val cote : Int = 9

  def solver(): List[Array[Array[Int]]] = {
    var grid : Array[Array[Int]] = startConfig_.clone()

    def isPossibleAt(number_ : Int, x_ : Int, y_ : Int) : Boolean = {
      for(nb <- 0 to (cote - 1)) {
        if(grid(nb)(y_) == number_) return false
      }
    }
  }
}