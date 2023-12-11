object Perceptron {
  def f(x_ : Double) : Double = 1/(1 + Math.exp(-x_))
  def fp(x_ : Double) : Double = f(x_) * (1 - f(x_))
  def prod(p1_ : Array[Double], p2_ : Array[Double]) : Double = {
    require(p1_.length == p2_.length, "Le calcul d'un produit scalaire se fait avec des vecteurs de meme taille")
    p1_.zip(p2_)
      .map{case(a, b) => a*b}
      .sum
  }
  def errQuad(p1_ : Array[Double], p2_ : Array[Double]) : Double = {
    require(p1_.length == p2_.length, "Le calcul de l'erreur quadratique se fait avec des vecteurs de meme taille")
    p1_.zip(p2_)
      .map{case(a, b) => (a - b) * (a - b)}
      .sum
  }
  def apply(couches_ : Int*) : Perceptron = {
    new Perceptron(couches_.toArray)
  }
}

class Perceptron(couches_ : Array[Int]) {
  var outputI = couches_.indices.map(i => new Array[Double](couches_(i))).toArray
  var inputI = couches_.indices.map(i => new Array[Double](couches_(i))).toArray
  var dI = couches_.indices.map(i => new Array[Double](couches_(i))).toArray
  var poids = (1 until couches_.length)
    .map(i => Array.ofDim[Double](couches_(i), couches_(i - 1))).toArray
  def poidsHasard(): Unit = {
    for(c <- poids.indices) {
      for(n <- poids(c).indices) {
        for(p <- poids(c)(n).indices) {
          poids(c)(n)(p) = 1 - 2 * Math.random()
        }
      }
    }
  }
  def apply(in_ : Array[Double]) :
}

object Main {
  def main(args: Array[String]): Unit = {
    val monPerceptron = Perceptron(2, 3, 4, 1)
    val reponse = monPerceptron(Array(1.0, 2.0, 4.0))
  }
}