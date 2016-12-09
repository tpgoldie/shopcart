package shopcart

case class Checkout(productsOnOffer: ProductsOnOffer = new ProductsOnOfferService) extends PriceSelector {
  val products = Seq(Apple, Orange)

  def calculateTotalCost(items: Seq[String]): Price = {
    val products = Seq(Apple, Orange)
    val prices: Seq[Price] = products map { product => productsOnOffer.offerFor(product).map { offer => offer.applyTo(items) } getOrElse(Zero) }

    prices.fold(Zero)(_ + _)
  }
}
