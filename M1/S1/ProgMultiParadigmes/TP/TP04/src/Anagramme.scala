object Anagramme{
  def main(args: Array[String]): Unit = {
    println("Entrez le mot dont vous souhaitez trouver les anagrammes")
    val search = scala.io.StdIn.readLine()
    val stringBuilder = new StringBuilder(search)
    val result = stringBuilder.permutations
    while(result.hasNext) {
      print(result.next())
      if(result.hasNext) {
        print(", ")
      }
    }
  }
}