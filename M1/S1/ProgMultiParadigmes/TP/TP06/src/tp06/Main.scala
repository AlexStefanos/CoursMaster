import tp05._

class CavalierEuler[P <: Piece](cote_ : Int = 8) {
  def modele : Echiquier(cote_)
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