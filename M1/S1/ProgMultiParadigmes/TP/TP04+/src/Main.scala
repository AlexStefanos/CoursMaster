class Chessboard(side_ : Int) {
  private val board : Array[Array[String]] = Array.fill[String](side_, side_) {"."}
  private def placedIn(piece_ : String, x_ : Int, y_ : Int): Unit = {
    if (x_ < 0) {
      if (y_ < 0) {
        this.board(0).update(0, piece_)
      } else if (y_ >= side_) {
        this.board(0).update(side_ - 1, piece_)
      } else {
        this.board(0).update(y_, piece_)
      }
    } else if (x_ >= side_) {
      if (y_ >= side_) {
        this.board(side_ - 1).update(side_ - 1, piece_)
      } else if (y_ < 0) {
        this.board(side_ - 1).update(0, piece_)
      } else {
        this.board(side_ - 1).update(y_, piece_)
      }
    } else {
      if (y_ < 0) {
        this.board(x_).update(0, piece_)
      } else if (y_ >= side_) {
        this.board(x_).update(side_ - 1, piece_)
      } else {
        this.board(x_).update(y_, piece_)
      }
    }
  }
  def apply(x_ : Int, y_ : Int): String = this.board(x_)(y_)
  private def emptyIn(x_ : Int, y_ : Int): Unit = this.placedIn(".", x_, y_)
  private def empty(): Unit = {
    for (i <- 0 until side_; j <- 0 until side_) {
      this.emptyIn(i, j)
    }
  }
  override def toString: String = {
    var display : String = "        y = 0\n"
    display += "          |\n"
    display += "          v\n"
    display += "x = 0 ->  "
    for(i <- 0 until side_) {
      for(j <- 0 until side_) {
        display += s"${board(i)(j)}  "
      }
      display += "\n"
      if(i < (side_ - 1)) {
        display += "          "
      }
    }
    display
  }
  private def placeableQueen(x_ : Int, y_ : Int): Any = {
    var answer:Option[Boolean] = None
    if(x_ < side_ && y_ < side_) {
      answer = Some(true)
      for(i <- 0 until side_) {
        if(board(x_)(i).equals("Q")) {
          answer = Some(false)
        }
        if(board(i)(y_).equals("Q")) {
          answer = Some(false)
        }
      }
      var xDiagLeft : Int = x_
      var yDiagLeft : Int = y_
      var xDiagRight : Int = x_
      var yDiagRight : Int = y_
      while((xDiagLeft > 0) && (yDiagLeft > 0)) {
        xDiagLeft -= 1
        yDiagLeft -= 1
      }
      while((xDiagRight > 0) && (yDiagRight < (side_ - 1))) {
        xDiagRight -= 1
        yDiagRight += 1
      }
      while((xDiagLeft < side_) && (yDiagLeft < side_)) {
        if(board(xDiagLeft)(yDiagLeft).equals("Q")) {
          answer = Some(false)
        }
        xDiagLeft += 1
        yDiagLeft += 1
      }
      while((xDiagRight < side_) && (yDiagRight > 0)) {
        if (board(xDiagRight)(yDiagRight).equals("Q")) {
          answer = Some(false)
        }
        xDiagRight += 1
        yDiagRight -= 1
      }
    }
    val finalResult = answer match {
      case None => None
      case Some(false) => false
      case Some(true) => true
    }
    finalResult
  }
  private def problemeNReines(placeInArray : Int): Int = {
    var firstTime: Int = 0
    var result : Int = 0
    println("placeInArray : " + placeInArray)
    val iStartAt : Int = placeInArray / side_
    val jStartAt : Int = placeInArray % side_
    println("iStartAt : " + iStartAt)
    println("jStartAt : " + jStartAt)
    for(i <- iStartAt until side_) {
      for(j <- jStartAt until side_) {
        if((!board(i)(j).equals("Q")) && placeableQueen(i, j).equals(true)) {
          placedIn("Q", i, j)
          if(firstTime == 0) {
            result = i * side_ + j
            firstTime += 1
          }
        }
      }
    }
    result
  }
  def problemeNReinesHandler(): Unit = {
    var display : String = ""
    var lastResult : Int = 0
    for (i <- 0 until (side_ * side_)) {
      println("i : " + i)
      lastResult = problemeNReines(lastResult)
      display = "        y = 0\n"
      display += "          |\n"
      display += "          v\n"
      display += "x = 0 ->  "
      for (i <- 0 until side_) {
        for (j <- 0 until side_) {
          display += s"${board(i)(j)}  "
        }
        display += "\n"
        if (i < (side_ - 1)) {
          display += "          "
        }
      }
      print(display)
      empty()
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    println("Il est préférable pour la compréhension de l'affichage " +
      "d'indiquer des valeurs de position de pions tel que : valeur = [0, side_[.")
    val chessboard = new Chessboard(8)
    chessboard.problemeNReinesHandler()
  }
}