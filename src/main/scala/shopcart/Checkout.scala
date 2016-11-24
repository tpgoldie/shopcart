package shopcart

class Checkout {
  val products = Seq(Apple, Orange)

  def calculateTotalCost(items: Seq[String]): Price = {
    def selectProduct(item: String): Product = products.filter( _.name == item.toLowerCase ).head

    val prices = items.map { selectProduct(_).price }
    prices.fold(Price(0))(_ + _)
  }
}
