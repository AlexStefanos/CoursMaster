package tp06

import tp05._

class CavalierEuler[P <: Piece](cote_ : Int = 8) {
  var vue : Echiquier[PieceCol] = new Echiquier[PieceCol](cote_)
  var modele : Array[Array[Int]] = Array.fill[Int](cote_, cote_)(0)
  def controleur(coord_ : (Int, Int)): Unit = {
    def trouveDeplacementsCavalier(xy_ : (Int, Int)): List[(Int, Int)] = {
      var liste : List[(Int, Int)] = List()

      liste = liste :+ (xy_._1 - 2, xy_._2 + 1)
      liste = liste :+ (xy_._1 - 1, xy_._2 + 2)
      liste = liste :+ (xy_._1 + 1, xy_._2 + 2)
      liste = liste :+ (xy_._1 + 2, xy_._2 + 1)
      liste = liste :+ (xy_._1 - 2, xy_._2 - 1)
      liste = liste :+ (xy_._1 - 1, xy_._2 - 2)
      liste = liste :+ (xy_._1 + 1, xy_._2 - 2)
      liste = liste :+ (xy_._1 + 2, xy_._2 - 1)
      liste.filter((tmpXY : (Int, Int)) => ((tmpXY._1 >= 0) && (tmpXY._1 < cote_) && (tmpXY._2 >= 0) && (tmpXY._2 < cote_)))
        .filter((tmpXY : (Int, Int)) => (this.modele(tmpXY._1)(tmpXY._2) == 0))
        //TODO : heuristique
    }

    def trouvePositions(xy_ : (Int, Int), etape_ : Int): Boolean = {
      var posRest = trouveDeplacementsCavalier(xy_)
      var saveModele = modele(xy_._1)(xy_._2)
      modele(xy_._1)(xy_._2) = etape_
      if(etape_ == ((cote_ * cote_))) {
        true
      }
      for (pos <- posRest) {
        //TODO : heuristique
        if (trouvePositions(pos, etape_ + 1)) {
          true
        }
      }
      modele(xy_._1)(xy_._2) = saveModele
      false
    }

    def synchroniseVueAuModele(): Unit = {

    }
  }

  override def toString : String = {

  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val echiquier = new Echiquier[PieceCol]()
    echiquier((0, 6)) = Cavalier()
    echiquier((3, 5)) = Dame()
    echiquier((6, 3)) = Fou()
    echiquier((5, 2)) = Pion()
    echiquier((5, 2)) = Rien()
    print(echiquier)
    println(echiquier(5, 2))
    println(echiquier(3, 5))
  }
}