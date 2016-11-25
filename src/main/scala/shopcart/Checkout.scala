package shopcart

import shopcart.Checkout.Zero

class Checkout {
  val products = Seq(Apple, Orange)

  def calculateTotalCost(items: Seq[String]): Price = {
    def selectProduct(item: String): Price = {
      products.filter( _.name == item.toLowerCase ) match {
        case x::y => x.price
        case List() => Zero
      }
    }

    val prices = items.map { selectProduct _ }
    prices.fold(Zero)(_ + _)
  }
}

object Checkout {
  val Zero= Price(0)
}
