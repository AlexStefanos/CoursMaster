package tp06

import tp05._

class CavalierEuler[P <: Piece](cote_ : Int = 8) {
  var vue: Echiquier[PieceCol] = new Echiquier[PieceCol](cote_)
  var modele: Array[Array[Int]] = Array.fill(cote_, cote_)(0)
  val heuristique = Array.fill(cote_, cote_)(0)

  def controleur(x_ : Int, y_ : Int): Unit = {
    def trouveDeplcementsCavalier(xy_ : Tuple2[Int, Int]): List[Tuple2[Int, Int]] = {
      var liste: List[Tuple2[Int, Int]] = List()

      liste = liste :+ (xy_._1 - 2, xy_._2 + 1)
      liste = liste :+ (xy_._1 - 1, xy_._2 + 2)
      liste = liste :+ (xy_._1 + 1, xy_._2 + 2)
      liste = liste :+ (xy_._1 + 2, xy_._2 + 1)
      liste = liste :+ (xy_._1 - 2, xy_._2 - 1)
      liste = liste :+ (xy_._1 - 1, xy_._2 - 2)
      liste = liste :+ (xy_._1 + 1, xy_._2 - 2)
      liste = liste :+ (xy_._1 + 2, xy_._2 - 1)
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

  override def toString(): String = {
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