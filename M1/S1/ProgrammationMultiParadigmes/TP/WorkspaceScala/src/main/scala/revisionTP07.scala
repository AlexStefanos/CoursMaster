class Sudoku(startConfig_ : Array[Array[Int]]) {
  private val cote : Int = 9
  def parcours_2(i_ : Int) : Unit = {
    var i : Int = i_
    if(i < 81) {
      println(i + "->" + ((i / 9) + 1, (i % 9) + 1))
      i += 1
      return(parcours_2(i))
    }
  }

  def parcours_22(i_ : Int = 0) : Unit = {
    (i_ / 9, i_ % 9) match {
      case (8, 8) => println("Dernière case")
      case (x, y) => {
        println(x + " " + y)
        parcours_22(i_ + 1)
      }
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val table: Array[Array[Int]] = Array(
      Array(5, 3, 0, 0, 7, 0, 0, 0, 0),
      Array(6, 0, 0, 1, 9, 5, 0, 0, 0),
      Array(0, 9, 8, 0, 0, 0, 0, 6, 0),
      Array(8, 0, 0, 0, 6, 0, 0, 0, 3),
      Array(4, 0, 0, 8, 0, 3, 0, 0, 1),
      Array(7, 0, 0, 0, 2, 0, 0, 0, 6),
      Array(0, 6, 0, 0, 0, 0, 2, 8, 0),
      Array(0, 0, 0, 4, 1, 9, 0, 0, 5),
      Array(0, 0, 0, 0, 8, 0, 0, 7, 9)
    )
    var sud : Sudoku = new Sudoku(table)
    sud.parcours_22()
  }
}