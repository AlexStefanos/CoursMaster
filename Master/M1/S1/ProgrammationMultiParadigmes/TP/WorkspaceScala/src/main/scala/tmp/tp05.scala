//import scala.reflect.ClassTag
//
//object Ansi{
//  def black : String = "\u001B[40m"
//  def red: String = "\u001B[31m"
//  def green: String = "\u001B[32m"
//  def yellow: String = "\u001B[33m"
//  def blue: String = "\u001B[44m"
//  def cyan: String = "\u001B[36m"
//  def white: String = "\u001B[37m"
//  def reset : String = "\u001B[0m"
//}
//
//class Echiquier[P <: Piece:ClassTag](cote_ : Int = 8) {
//  private var plateau : Array[Array[Option[P]]] = Array.ofDim[Option[P]](cote_, cote_)
//  def placerEn(piece_ : Option[P], x_ : Int, y_ : Int): Unit = this.plateau(x_).update(y_, piece_)
//  def update(coupleXY_ : Tuple2[Int, Int], piece_ : P): Unit = this.update(coupleXY_, Some(piece_))
//  def update(coupleXY_ : Tuple2[Int, Int], piece_ : Option[P]): Unit = {
//    this.placerEn(piece_, coupleXY_._1, coupleXY_._2)
//  }
//  def vider(x_ : Int, y_ : Int): Unit = this.placerEn(None, x_, y_)
//  def apply(x_ : Int, y_ : Int): Option[P] = this.plateau(x_)(y_)
//  def apply(coupleXY_ : Tuple2[Int, Int]): Option[P] = this.plateau(coupleXY_._1)(coupleXY_._2)
//  for(i <- 0 to cote_ - 1; j <- 0 to cote_ - 1)
//    this.vider(i, j)
//  override def toString() : String = {
//    var affich : String = ""
//    for(i <- 0 to cote_ - 1)
//      affich += (" " * 5) + i
//    affich += "\n " + Ansi.blue
//    affich += (" " * 6 * cote_) + " "
//    affich += Ansi.reset + "\n"
//    for(i <- 0 to cote_ - 1) {
//      affich += s"$i " + Ansi.blue + " ";
//      for(j <- 0 to cote_ - 1) {
//        if(this.plateau(i)(j) == None) {
//          affich += Ansi.black + Ansi.white + (" " * 5)
//        } else {
//          val Some(piece) = this.plateau(i)(j)
//          affich += Ansi.black + Ansi.white + s"$piece"
//        }
//        affich += Ansi.blue + " "
//      }
//      affich += Ansi.reset + "\n"
//      affich += " " + Ansi.blue
//      affich += (" " * 6 * cote_) + " "
//      affich += Ansi.reset + "\n"
//    }
//    return(affich)
//  }
//}
//
//trait Piece {
//  def longueur: Int
//}
//
//class PieceCol(etiquette_ : String, codeAnsi_ : String) extends Piece {
//  private var etiquette : String = etiquette_
//  private var codeAnsi : String = codeAnsi_
//  def this(etiquette_ : String) = this(etiquette_, Ansi.black)
//  override def toString : String = {
//    var affich = codeAnsi_
//    var eti = etiquette_.slice(0, this.longueur)
//    eti += " " * (this.longueur - eti.length)
//    affich += eti + Ansi.reset
//    affich
//  }
//  def longueur : Int = 5
//}
//
//object PieceCol {
//  def apply(etiquette_ : String, codeAnsi_ : String): PieceCol = new PieceCol(etiquette_, codeAnsi_)
//  def apply(etiquette_ : String): PieceCol = new PieceCol(etiquette_)
//}
//
//object Cavalier {
//  def apply(): PieceCol = PieceCol("Cavalier", Ansi.yellow)
//}
//
//object Dame {
//  def apply(): PieceCol = PieceCol("Dame", Ansi.red)
//}
//
//object Fou {
//  def apply(): PieceCol = PieceCol("Fou", Ansi.green)
//}
//
//object Pion {
//  def apply(): PieceCol = PieceCol("Pion", Ansi.cyan)
//}
//
//object Rien {
//  def apply() = None
//}
//
//object Main {
//  def main(args: Array[String]): Unit = {
//    val echiquier = new Echiquier[PieceCol]()
//    echiquier((0, 6)) = Cavalier()
//    echiquier((3, 5)) = Dame()
//    echiquier((6, 3)) = Fou()
//    echiquier((5, 2)) = Pion()
//    echiquier((5, 2)) = Rien()
//    print(echiquier)
//    println(echiquier(5, 2))
//    println(echiquier(3, 5))
//  }
//}