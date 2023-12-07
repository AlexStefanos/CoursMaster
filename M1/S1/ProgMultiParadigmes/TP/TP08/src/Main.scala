import scala.{Array => $}

class Fonctionnelle(startConfig_ : $[$[Int]]) {
  def parcours_1(i_ : Int = 0) : (Int, Int) = (i_ / 9, i_ % 9)

  def parcours_2(i_ : Int = 0) : Unit = {
    parcours_1(i_) match {
      case(8 , 8) => println("")
      case(x, y) => {
        println(x + " " + y)
        parcours_2(i_ + 1)
      }
    }
  }

  def parcours_3(i_ : Int = 0) : Unit = {
    parcours_1(i_) match {
      case(9, 0) => println("on arrete, cette case ne doit pas être analysée")
      case(x, y) => {
        println(x + " " + y)
        parcours_3(i_ + 1)
      }
    }
  }

  def parcours_5(i_ : Int = 0) : Unit = {
    parcours_1(i_) match {
      case(9, 0) => println("stop ! une case de trop")
      case(x, y) => {
        println(x + " " + y)
        def indicesAVoir(j_ : Int) = List((x, j_), (j_, y), (3 * (x/3) + (j_ / 3), (3 * (y/3)) + (j_ % 3)))
        println((0 until 9).flatMap(indicesAVoir).toSet)
        parcours_5(i_ + 1)
      }
    }
  }

  def parcours_7(i_ : Int = 0) : Unit = {
    parcours_1(i_) match {
      case(9, 0) => println("stop")
      case(x, y) => {
        println(x + " " + y)
        def nombresDejaPris(j_ : Int) = List((x, j_), (j_, y), (3 * (x/3) + j_ / 3, 3 * (y/3) + j_ % 3)).map((x) => startConfig_(x._1)(x._2))
        println("deja pris" + (0 until 9).flatMap(nombresDejaPris).toSet)
        parcours_7(i_ + 1)
      }
    }
  }

  def parcours_9(i_ : Int = 0) : Unit = {
    parcours_1(i_) match {
      case(9, 0) => println("dernier case")
      case(x, y) => {
        println(x + " " + y)
        def nombresDejaPris(j_ : Int) = List((x, j_), (j_, y), (3 * (x/3) + j_ / 3, 3 * (y/3) + j_ % 3)).map((x) => startConfig_(x._1)(x._2))
        val dejaPris = (0 until 9).flatMap(nombresDejaPris).toSet
        val aEssayer = (1 to 9).toSeq
          .diff(dejaPris.toSeq)
        println("a essayer : " + aEssayer)
        parcours_9(i_ + 1)
      }
    }
  }

  def parcours_11(t_ : $[$[Int]], i_ : Int = 0) : Option[$[$[Int]]] = {
    parcours_1(i_) match {
      case(9, 0) => Some(t_)
      case(x, y) => {
        parcours_11(t_.updated(x, t_(x).updated(y, i_)), i_ + 1)
      }
    }
  }

  def parcours_12(t_ : $[$[Int]], i_ : Int = 0) : Option[$[$[Int]]] = {
    (i_ / 9, i_ % 9) match {
      case(9, 0) => Some(t_)
      case(x, y) if t_(x)(y) != 0 => parcours_12(t_, i_ + 1)
      case(x, y) => {
        def nombresDejaPris(j_ : Int) = List((x, j_), (j_ , y), (3 * (x/3) + j_ / 3, 3 * (y/3) + j_ %3)).map((x) => t_(x._1)(x._2))
        val dejaPris = (0 until 9).flatMap(nombresDejaPris).toSet
        val aEssayer = (1 to 9).toSeq
          .diff(dejaPris.toSeq)
        //si on place le nombre j_ sur la case courante et qu'on avance ?
        def placer(j_ : Int) = parcours_12(t_.updated(x, t_(x).updated(y, j_)), i_ + 1)
        if(aEssayer.isEmpty) None
        else {
          val ts = aEssayer.map(placer).filterNot(_.isEmpty)
          if(ts.nonEmpty) ts(0) else None
        }
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

    val departChrono = System.currentTimeMillis()
    var fonc : Fonctionnelle = new Fonctionnelle(table)

    //    println(table.map(_.mkString(" ")).mkString("\n"))
    //    println("\n")
    //    val ntable = table.updated(3, table(3).updated(4, 22))
    //    println(ntable.map(_.mkString(" ")).mkString("\n"))

    System.out.println("Parcours_1 : ")
    println("Test sans argument : " + fonc.parcours_1())
    println("\n")

    println("Résultat complet : ")
    for(i <- 0 to 80) {
      print(fonc.parcours_1(i) + " ")
    }
    println("\n")

    println("Parcours_2 : ")
    fonc.parcours_2()

    println("Parcours_3 : ")
    fonc.parcours_3()

    //    println((0 until 9).map(v => (3, v)))
    //    println((0 until 9).map(v => (v, 3)))
    //    println((0 until 9).map(v => (v/3, v%3)))

    println("Parcours_5 : ")
    fonc.parcours_5()

    println("Parcours_7 : ")
    fonc.parcours_7()

    println("Parcours_9 : ")
    fonc.parcours_9()

    println("Parcours_11 : ")
    fonc.parcours_11(table) match {
      case Some(res) => println("11: \n" + res.map(_.mkString(" ")).mkString("\n"))
      case None => println("pas de solution")
    }

    println("Parcours_12 : ")
    fonc.parcours_12(table) match {
      case Some(res) => println("12: \n" + res.map(_.mkString(" ")).mkString("\n"))
      case None => println("pas de solution")
    }

    val arretChrono = System.currentTimeMillis()
    println("Temps Chrono : " + (arretChrono - departChrono) + "ms")
  }
}