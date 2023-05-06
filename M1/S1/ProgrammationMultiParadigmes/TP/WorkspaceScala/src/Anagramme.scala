import scala.collection.mutable.HashSet
import scala.collection.mutable.StringBuilder

object Anagramme{
  def main(args: Array[String]): Unit = {
    println("Entrez le mot dont vous souhaitez trouver les anagrammes")
    val search = scala.io.StdIn.readLine()
    val result = trouveAnagrammesRec(search)
    println("Nombre de mots : " + result.size)
    println("Afficher tous les nombres (O/n)")
    val display = scala.io.StdIn.readChar()
    val verif = false
    while (verif == false) {
      if (display == 'O') {
        verif = true
        println("Mots : ")
        for (tmp <- result) {
          println(tmp)
        }
      }
      if (display == 'n') {
        return 0
      }
    }
    return 0
  }

  def anagrammePermut(word_ : String): Set[String] = {
    return word_.permutations.toSet
  }

  def trouveAnagrammesRec(word_ : String): HashSet[String] = {
    var result: HashSet[String] = new HashSet[String]()
    var nb = 0
    for (c <- word_) {
      var stringBuilder = new StringBuilder(word_)
      var rest = stringBuilder.deleteCharAt(nb).toString()
      if (rest.length < 1) {
        result += s"$c$rest"
        return rest
      }
      var rets = trouveAnagrammesRec(rest)
      for (tmp <- rets) {
        result += s"$c$tmp"
      }
      nb += 1
    }
    return result
  }
}