import scala.reflect.ClassTag

object Ansi{
  def black : String = "\u001B[40m"
  def red: String = "\u001B[31m"
  def green: String = "\u001B[32m"
  def yellow: String = "\u001B[33m"
  def blue: String = "\u001B[44m"
  def cyan: String = "\u001B[36m"
  def white: String = "\u001B[37m"
  def reset : String = "\u001B[0m"
}

class Echiquier[P <: Piece:ClassTag](cote_ : Int = 8) {
  private var plateau : Array[Array[Option[P]]] = Array.fill(cote_, cote_)(None)
  def placerEn(piece_ : Option[P], x_ : Int, y_ : Int): Unit = this.plateau(x_).update(y_, piece_)
  def update(coupleXY_ : Tuple2[Int, Int], piece_ : P): Unit = this.update(coupleXY_, Some(piece_))
  def update(coupleXY_ : Tuple2[Int, Int], piece_ : Option[P]): Unit = {
    this.placerEn(piece_, coupleXY_._1, coupleXY_._2)
  }
  def vider(x_ : Int, y_ : Int): Unit = this.placerEn(None, x_, y_)
  def apply(x_ : Int, y_ : Int): Option[P] = this.plateau(x_)(y_)
  def apply(coupleXY_ : Tuple2[Int, Int]): Option[P] = this.plateau(coupleXY_._1)(coupleXY_._2)
  override def toString() : String = {
    var affich : String = ""
    for(i <- 0 to cote_ - 1) {
      affich += (" " * 5) + i
    }
    affich += "\n " + Ansi.blue
    affich += (" " * 6 * cote_) + " "
    affich += Ansi.reset + "\n"
    for(i <- 0 to cote_ - 1) {
      affich += s"$i " + Ansi.blue + " ";
      for(j <- 0 to cote_ - 1) {
        if(this.plateau(i)(j) == None) {
          affich += Ansi.black + Ansi.white + (" " * 5)
        } else {
          val Some(piece) = this.plateau(i)(j)
          affich += Ansi.black + Ansi.white + s"$piece"
        }
        affich += Ansi.blue + " "
      }
      affich += Ansi.reset + "\n"
      affich += " " + Ansi.blue
      affich += (" " * 6 * cote_) + " "
      affich += Ansi.reset + "\n"
    }
    affich
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
    var affich = codeAnsi_
    var eti = etiquette_.slice(0, this.longueur)
    eti += " " * (this.longueur - eti.length)
    affich += eti + Ansi.reset
    affich
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

class CavalierEuler(cote_ : Int = 8) {
  private var vue : Echiquier[PieceCol] = new Echiquier[PieceCol](cote_)
  private var modele : Array[Array[/home/alexandre/Cours/Master/M1/S1/ProgrammationMultiParadigmes/TP/WorkspaceScala/src/main/scala/tmpInt]] = Array.fill(cote_, cote_)(0)
  private val heuristique = Array.fill(cote_, cote_)(0)

  def controleur(x_ : Int, y_ : Int): Unit = {
    def trouveDeplcementsCavalier(xy_ : Tuple2[Int, Int]): List[Tuple2[Int, Int]] = {
      var liste: List[Tuple2[Int, Int]] = List()

      liste = liste:+(xy_._1 - 2, xy_._2 + 1)
      liste = liste:+(xy_._1 - 1, xy_._2 + 2)
      liste = liste:+(xy_._1 + 1, xy_._2 + 2)
      liste = liste:+(xy_._1 + 2, xy_._2 + 1)
      liste = liste:+(xy_._1 - 2, xy_._2 - 1)
      liste = liste:+(xy_._1 - 1, xy_._2 - 2)
      liste = liste:+(xy_._1 + 1, xy_._2 - 2)
      liste = liste:+(xy_._1 + 2, xy_._2 - 1)
      liste.filter((xy: Tuple2[Int, Int]) => ((xy._1 >= 0) && (xy._1 < cote_) && (xy._2 >= 0) && (xy._2 < cote_)))
        .filter((xy: Tuple2[Int, Int]) => this.modele(xy._1)(xy._2) == 0)
        .sortBy((xy: Tuple2[Int, Int]) => heuristique(xy._1)(xy._2))
    }

    def trouvePositions(xy_ : Tuple2[Int, Int], etape_ : Int): Boolean = {
      var posRestante = trouveDeplcementsCavalier(xy_)
      modele(xy_._1)(xy_._2) = etape_
      if (etape_ == ((cote_ * cote_))) {
        return true
      }
      for (pos <- posRestante) {
        setHeuristique()
        if (trouvePositions(pos, etape_ + 1)) {
          return true
        }
      }
      modele(xy_._1)(xy_._2) = 0
      return false
    }

    def synchroniseVueAuModele(): Unit = {
      var max: Int = cote_ - 1
      for (i <- 0 to max; j <- 0 to max) {
        vue((i, j)) = PieceCol(modele(i)(j).toString)
      }
    }

    def setHeuristique(): Unit = {
      var max: Int = cote_ - 1
      for (i <- 0 to max; j <- 0 to max) {
        heuristique(i)(j) = trouveDeplcementsCavalier((i, j)).size
      }
    }
    trouvePositions((x_, y_), 1)
    synchroniseVueAuModele()
  }
  override def toString() : String = {
    vue.toString()
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val euler = new CavalierEuler(7)
    val departChrono = System.currentTimeMillis()
    euler.controleur(0, 0)
    val arretChrono = System.currentTimeMillis()
    println(euler)
    println("Temps Chrono : " + (arretChrono - departChrono) + "ms")
  }
}