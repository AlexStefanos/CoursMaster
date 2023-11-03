object Main {
  def main(args: Array[String]): Unit = {
    val l : Array[String] = Array("hey", "it", "works")
    println(l(2))
    println(l.map((elt : String) => elt).mkString(", "))
    println(l.mkString("", ", ", ""))
    val lNombres : Array[Int] = Array(1, 2, 3, 8, 9)
    println(lNombres.filter(_ / 2 == 0).mkString(", "))
    val tuple = Tuple2[Int, Int](4,8)
    val c = (4, 8)
    println("Tuple2 : " + tuple)
    println("c : " + c)
    val lNombresPartition = lNombres.partition(_ / 2 == 0)
    println("Partition (_1) : " + lNombresPartition._1.mkString(", "))
    println("Partition (_2) : " + lNombresPartition._2.mkString(", "))
    println(lNombres.mkString("Array(", ", ", ")"))
    println(lNombres.mkString("Array(", "-", ")"))
    val anagramme : String = "hey"
    println("Anagramme : " + anagramme.permutations.toSet.mkString(", "))
    val grosMots : Array[String] = Array("merde", "con", "boudin")
    val mots : Array[String] = Array("merde", "avion", "voiture", "eau", "con", "train")
    val resultGrosMots : Array[String] = mots.map((elem : String) => {
      if(!grosMots.contains(elem)) {
        elem
      } else {
        "***"
      }
    })
    println(resultGrosMots.mkString(", "))
    println("Reverse (String) : " + anagramme.reverse)
    println("Reverse (Collection ordonnée, Array) : " + l.reverse.mkString(", "))
    println("Anagramme reversed : " + anagramme.reverse.permutations.toSet.mkString(", "))
    val motsPalindromes : Array[String] = Array("radar", "Hannah", "rotor", "kayak", "ressasser", "bob", "exercice", "vacances", "anniversaire")
    println("Palindromes : " + motsPalindromes.map((elem : String) => elem.toLowerCase).filter((elem : String) => elem.equals(elem.reverse)).mkString(", "))
    println("Non Palindromes : " + motsPalindromes.map((elem : String) => elem.toLowerCase).filterNot((elem : String) => elem.equals(elem.reverse)).mkString(", "))
    val phrase : String = "Tu l'as trop écrasé, César, ce Port-Salut"
    val phrasePalindrome : String = "tulastropecrasecesarceportsalut"
    val phraseObtenue : String = phrase.toLowerCase
      .filterNot((elem: Char) => (elem == '\'') ||
        (elem == ',') || (elem == ' ') || (elem == '-'))
      .replace('é', 'e')
    println("Phrase : " + phrase)
    println("Phrase palindrome à obtenir : " + phrasePalindrome)
    println("Phrase obtenue : " + phraseObtenue)
    println("Est-ce la même String ? " + phrasePalindrome.equals(phraseObtenue))
  }
}