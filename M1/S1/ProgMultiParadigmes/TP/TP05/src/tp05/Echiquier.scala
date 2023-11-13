package tp05

object Ansi{
  def black : String = "\u001B[40m"
  def red : String = "\u001B[31m"
  def green : String = "\u001B[32m"
  def yellow : String = "\u001B[33m"
  def blue : String = "\u001B[44m"
  def cyan : String = "\u001B[36m"
  def white : String = "\u001B[37m"
  def reset : String = "\u001B[0m"
}

class Echiquier[P <: Piece](cote_ : Int = 8) {
  private var plateau : Array[Array[Option[P]]] = Array.ofDim[Option[P]](cote_, cote_)
  def placerEn(piece_ : Option[P], x_ : Int, y_ : Int): Unit = this.plateau(x_).update(y_, piece_)
  def update(coupleXY_ : (Int, Int), piece_ : P): Unit = this.update(coupleXY_, Some(piece_))
  def update(coupleXY_ : (Int, Int), piece_ : Option[P]): Unit = {
    this.placerEn(piece_, coupleXY_._1, coupleXY_._2)
  }
  def vider(x_ : Int, y_ : Int): Unit = this.placerEn(None, x_, y_)
  def apply(x_ : Int, y_ : Int): Option[P] = this.plateau(x_)(y_)
  def apply(coupleXY_ : (Int, Int)): Option[P] = this.plateau(coupleXY_._1)(coupleXY_._2)
  for(i <- 0 until cote_; j <- 0 until cote_)
    this.vider(i, j)
  override def toString: String = {
    var display : String = ""
    for(i <- 0 until cote_)
      display += (" " * 5) + i
    display += "\n " + Ansi.blue
    display += (" " * 6 * cote_) + " "
    display += Ansi.reset + "\n"
    for(i <- 0 until cote_) {
      display += s"$i " + Ansi.blue + " "
      for(j <- 0 until cote_) {
        if(this.plateau(i)(j).isEmpty) {
          display += Ansi.black + Ansi.white + (" " * 5)
        } else {
          val Some(piece) = this.plateau(i)(j)
          display += Ansi.black + Ansi.white + s"$piece"
        }
        display += Ansi.blue + " "
      }
      display += Ansi.reset + "\n"
      display += " " + Ansi.blue
      display += (" " * 6 * cote_) + " "
      display += Ansi.reset + "\n"
    }
    display
  }
}

trait Piece {
  def longueur: Int
}

class PieceCol(etiquette_ : String, codeAnsi_ : String) extends Piece {
  private var etiquette : String = etiquette_
  private var codeAnsi : String = codeAnsi_
  def this(etiquette_ : String) = this(etiquette_, Ansi.black)
  override def toString : String = {
    var display = codeAnsi_
    var eti = etiquette_.slice(0, this.longueur)
    eti += " " * (this.longueur - eti.length)
    display += eti + Ansi.reset
    display
  }
  def longueur : Int = 5
}

object PieceCol {
  def apply(etiquette_ : String, codeAnsi_ : String): PieceCol = new PieceCol(etiquette_, codeAnsi_)
  def apply(etiquette_ : String): PieceCol = new PieceCol(etiquette_)
}

object Cavalier {
  def apply(): PieceCol = PieceCol("Cavalier", Ansi.yellow)
}

object Dame {
  def apply(): PieceCol = PieceCol("Dame", Ansi.red)
}

object Fou {
  def apply(): PieceCol = PieceCol("Fou", Ansi.green)
}

object Pion {
  def apply(): PieceCol = PieceCol("Pion", Ansi.cyan)
}

object Rien {
  def apply() = None
}