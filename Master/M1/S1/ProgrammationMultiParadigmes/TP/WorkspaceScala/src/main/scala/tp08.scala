import scala.{Array => $}

class Fonctionnelle {
  def parcours_1(i_ : Int = 0) : Tuple2[Int, Int] = (i_ / 9, i_ % 9)

  def parcours_2(i_ : Int = 0) : Unit = {
    parcours_1(i_) match {
      case(8 , 8) => println("")
      case(x, y) => {
        println(x + " " + y)
        parcours_2(i_ + 1)
      }
    }
  }
}

object Main {
  def main(args: $[String]) : Unit = {
    val table: $[$[Int]] = $(
      $(5, 3, 0, 0, 7, 0, 0, 0, 0),
      $(6, 0, 0, 1, 9, 5, 0, 0, 0),
      $(0, 9, 8, 0, 0, 0, 0, 6, 0),
      $(8, 0, 0, 0, 6, 0, 0, 0, 3),
      $(4, 0, 0, 8, 0, 3, 0, 0, 1),
      $(7, 0, 0, 0, 2, 0, 0, 0, 6),
      $(0, 6, 0, 0, 0, 0, 2, 8, 0),
      $(0, 0, 0, 4, 1, 9, 0, 0, 5),
      $(0, 0, 0, 0, 8, 0, 0, 7, 9)
    )
//    println(table.map(_.mkString(" ")).mkString("\n"))
//    println("\n")
//    val ntable = table.updated(3, table(3).updated(4, 22))
//    println(ntable.map(_.mkString(" ")).mkString("\n"))
    var fonc : Fonctionnelle = new Fonctionnelle()
//    println(fonc.parcours_1())
//    for(i <- 0 to 80) {
//      print(fonc.parcours_1(i) + " ")
//    }
    fonc.parcours_2()
  }
}
