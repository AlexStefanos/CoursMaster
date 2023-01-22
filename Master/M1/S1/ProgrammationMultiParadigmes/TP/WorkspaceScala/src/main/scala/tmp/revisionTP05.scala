import scala.reflect.ClassTag

object AnsiTest {
  def code(code_ : String): String = {
    if(code_ == "black") "\u001B[40m"
    else if(code_ == "red") "\u001B[31m"
    else if(code_ == "green") "\u001B[32m"
    else if(code_ == "yellow") "\u001B[33m"
    else if(code_ == "blue") "\u001B[44m"
    else if(code_ == "cyan") "\u001B[36m"
    else if(code_ == "white") "\u001B[37m"
    else if(code_ == "reset") "\u001B[0m"
    else "\u001B[0m"
  }
}

object Ansi {
  def black: String = "\u001B[40m"
  def red: String = "\u001B[31m"
  def green: String = "\u001B[32m"
  def yellow: String = "\u001B[33m"
  def blue: String = "\u001B[44m"
  def cyan: String = "\u001B[36m"
  def white: String = "\u001B[37m"
  def reset: String = "\u001B[0m"
}

class Echiquier[Piece:ClassTag](cote_ : Int = 8) {
  private var plateau: Array[Array[Option[Piece]]] = Array.fill(cote_, cote_)(Option[Piece])
  def update(coupleXY_ : Tuple2[Int, Int], piece_ : Piece) : Unit = this.update(coupleXY_, piece_)
  def placerEn(piece_ : Option[Piece], x_ : Int, y_ : Int) : Unit = this.plateau(x_).update(y_, piece_)
}

trait Piece {
  def longueur : Int
}

class PieceCol(etiquette_ : String, codeAnsi_ : String) {
  private var etiquette = etiquette_
  private var codeAnsi = codeAnsi_
  def this(etiquette_ : String) = this(etiquette_, Ansi.black)
  def this() = this("hey", Ansi.blue)
  override def toString : String = {
    var affich = codeAnsi_
    var eti = etiquette_.slice(0, this.longueur)
    eti += " " * (this.longueur - eti.length)
    affich += eti + Ansi.reset
    affich
  }
  def longueur : Int = 5
}

class CavalierEuler(cote_ : Int = 8) {
  private var vue : Echiquier[PieceCol] = new Echiquier[PieceCol](cote_)
  private var modele : Array[Array[Option[Int]]] = Array.fill(cote_, cote_)(None)
}

object Main {
  def main(args: Array[String]): Unit = {
//    println("Coucou" + Ansi.blue + Ansi.red + "abcdef" + Ansi.reset)
    val cavalierRouge = new PieceCol("caval", Ansi.red)
    println(cavalierRouge)
    val cavalier = new PieceCol("caval")
    println(cavalier)
    val test = new PieceCol()
    println(test)
  }
}