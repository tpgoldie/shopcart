package shopcart

case class Checkout(productsOnOffer: ProductsOnOffer = new ProductsOnOfferService) extends PriceSelector {
  val products = Seq(Apple, Orange)

  def calculateTotalCost(items: Seq[String]): Price = {
    val appleOffer = productsOnOffer.offerFor(Apple)
    val totalApplesCost = appleOffer.map { offer => offer.applyTo(items) } getOrElse(Zero)

    val totalOrangesCost = Orange.price * OrangeFilter(items).filteredProducts.size

    totalApplesCost + totalOrangesCost
  }
}
