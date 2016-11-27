package shopcart

trait ProductsOnOffer {
  def offerFor(product: Product): Option[Offer]
}
