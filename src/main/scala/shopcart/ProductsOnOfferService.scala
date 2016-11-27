package shopcart

class ProductsOnOfferService extends ProductsOnOffer {
  private val productsOnOffer: Map[Product, Offer] = Map(Apple -> BuyOneGetOneFree(Apple))

  override def offerFor(product: Product): Option[Offer] = productsOnOffer.get(product)
}
