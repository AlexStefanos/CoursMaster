class Sudoku(startConfig_ : Array[Array[Int]]) {
  private val nbSide : Int = 9
  def solver(): Array[Array[Int]] = {
    var sudokuMatrix : List[Array[Array[Int]]] = List()
    var workGrid : Array[Array[Int]] = startConfig_.clone

    def isInThisLine(number_ : Int, x_ : Int) : Boolean = {
      workGrid(x_).contains(number_)
    }

    def isInThisColumn(number_ : Int, y_ : Int) : Boolean = {
      var result = false
      for(i <- 0 to nbSide - 1) {
        if(!result)
          result = (workGrid(i)(y_) == number_)
      }
      result
    }

    def isInThisSquare(number_ : Int, x_ : Int, y_ : Int) : Boolean = {
      var result = false
      val line = (x_ / 3) * 3
      val column = (y_ / 3) * 3
      for(i <- line to line + 2) {
        for(j <- column to column + 2) {
          if (workGrid(i)(j) == number_) {
            result = true
          }
        }
      }
      result
    }

    def isFull(x_ : Int, y_ : Int) : Boolean = {
      workGrid(x_)(y_) != 0
    }

    def isPossibleAt(number_ : Int, x_ : Int, y_ : Int) : Boolean = {
      ((!isFull(x_, y_)) && (!isInThisLine(number_, x_)) && (!isInThisColumn(number_, y_)) && (!isInThisSquare(number_, x_, y_)))
    }

    def isDone() : Boolean = {
      var result : Boolean = true
      for(i <- 0 to nbSide - 1; j <- 0 to nbSide - 1) {
        if(workGrid(i)(j) == 0) {
          result = false
        }
      }
      result
    }

    def fillXY(x_ : Int, y_ : Int) : Unit = {
      val init : Array[Array[Int]] = workGrid.clone
      var result : Array[Array[Int]] = Array.fill(nbSide, nbSide)(0)
      if((x_ == nbSide - 1) && (y_ == nbSide - 1)) {
        if(!(isFull(x_, y_))) {
          for(i <- 1 to nbSide) {
            if(isPossibleAt(i, x_, y_)) {
              workGrid(x_)(y_) = 1
            }
          }
        }
        result = workGrid.clone
      } else {
        var nextX : Int = x_
        var nextY : Int = y_
        if(y_ < 8) {
          nextX = x_
          nextY = y_ + 1
        } else if(x_ < 8) {
          nextX = x_ + 1
          nextY = 0
        }
        if(!(isFull(x_, y_))) {
          for(i <- 1 to nbSide) {
            if(isPossibleAt(i, x_, y_)) {
              workGrid(x_)(y_) = i
              fillXY(nextX, nextY)
              if(isDone()) {
                result = workGrid.clone
              } else {
                workGrid(x_)(y_) = 0
              }
            }
          }
        } else {
          fillXY(nextX, nextY)
          if(isDone()) {
            result = workGrid.clone
          }
        }
      }
      if(isDone()) {
        workGrid = result.clone
      } else {
        workGrid = init.clone
      }
    }
    fillXY(0, 0)
    workGrid
  }

  override def toString() : String = {
    var result : String = " "
    for(i <- 0 to 8) {
      result += s" $i\n\n"
      for(j <- 0 to 8) {
        var value : Int = startConfig_(i)(j)
        result += s" $value "
      }
      result += "\n"
    }
    result
  }
}

object Main {
  def main(args: Array[String]) : Unit = {
    var grid : Array[Array[Int]] = Array (
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

    var sudo : Sudoku = new Sudoku(grid)
    val departChrono = System.currentTimeMillis()
    var result : Array[Array[Int]] = sudo.solver()
    for(i <- 0 to 8) {
      for(j <- 0 to 8) {
        print(result(i)(j))
      }
      print("\n")
    }
    val arretChrono = System.currentTimeMillis()
    println("Temps Chrono : " + (arretChrono - departChrono) + "ms")
  }
}
