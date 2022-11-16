import scala.reflect.ClassTag

object Ansi {
  def reset: String = "\u001B[0m"
}

class Echiquier[P <: Piece:ClassTag](cote_ : Int = 8) {
  private var plateau : Array[Array[Option[P]]] = Array.ofDim[Option[P]](cote_, cote_)
  def placerEn(piece_ : Option[P], x_ : Int, y_ : Int): Unit = this.plateau(x_).update(y_, piece_)
  def update(coupleXY_ : Tuple2[Int, Int], piece_ : P): Unit = this.update(coupleXY_, Some(piece_))
  def update(coupleXY_ : Tuple2[Int, Int], piece_ : Option[P]): Unit = {
    this.placerEn(piece_, coupleXY_._1, coupleXY_._2)
  }
  def vider(x_ : Int, y_ : Int): Unit = this.placerEn(None, x_, y_)
  def apply(x_ : Int, y_ : Int): Option[P] = this.plateau(x_)(y_)
  def apply(coupleXY_ : Tuple2[Int, Int]): Option[P] = this.plateau(coupleXY_._1)(coupleXY_._2)
  for(i <- 0 to cote_ - 1; j <- 0 to cote_ - 1)
    this.vider(i, j)
  override def toString() : String = {
    var affich : String = ""
    for(i <- 0 to cote_ - 1)
      affich += (" " * 5) + i
    affich += "\n " + "\u001B[34m"
    affich += (" " * 6 * cote_) + " "
    affich += Ansi.reset + "\n"
    for(i <- 0 to cote_ - 1) {
      affich += s"$i " + "\u001B[34m" + " ";
      for(j <- 0 to cote_ - 1) {
        if(this.plateau(i)(j) == None) {
          affich += "\u991B[30m" + "\u001B[37m" + (" " * 5)
        } else {
          val Some(piece) = this.plateau(i)(j)
          affich += "\u991B[30m" + "\u001B[37m" + s"$piece"
        }
        affich += "\u001B[34m" + " "
      }
      affich += Ansi.reset + "\n"
      affich += " " + "\u001B[34m"
      affich += (" " * 6 * cote_) + " "
      affich += Ansi.reset + "\n"
    }
    return(affich)
  }
}

trait Piece {
  def longueur: Int
}

class PieceCol(etiquette_ : String, codeAnsi_ : String) extends Piece {
  private var etiquette : String = etiquette_
  private var codeAnsi : String = codeAnsi_
  def this(etiquette_ : String) = this(etiquette_, "\u001B[30m")
  override def toString : String = {
    var affich = codeAnsi_
    var eti = etiquette_.slice(0, this.longueur)
    eti += " " * (this.longueur - eti.length)
    affich += eti + Ansi.reset
    affich
  }
  def longueur : Int = 5
}

object PieceColonne {
  def apply(etiquette_ : String, codeAnsi_ : String): PieceColonne = new PieceColonne(etiquette_, codeAnsi_)
  def apply(etiquette_ : String): PieceColonne = new PieceColonne(etiquette_)
}

object Cavalier {
  def apply(): PieceColonne = PieceCol("Cavalier", "\u001B[33m")
}

object Dame {
  def apply(): PieceColonne = PieceCol("Dame", "\u001B[31m")
}

object Fou {
  def apply(): PieceColonne = PieceCol("Fou", "\u001B[32m")
}

object Pion {
  def apply(): PieceColonne = PieceCol("Pion", "\u001B[36m")
}

object Rien {
  def apply() = None
}

object Main {
  def main(args: Array[String]): Unit = {
    val echiquier = new Echiquier[PieceColonne]()
    echiquier((0, 6)) = Cavalier()
    echiquier((3, 5)) = Dame()
    echiquier((6, 3)) = Fou()
    echiquier((5, 2)) = Pion()
    echiquier((5, 2)) = Rien()
    println(echiquier)
    println(echiquier(5, 2))
    println(echiquier(3, 5))
  }
}