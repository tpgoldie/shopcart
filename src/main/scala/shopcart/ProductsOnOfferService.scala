package shopcart

class ProductsOnOfferService(productsOnOffer: Map[Product, Offer] = Map(Apple -> NotOnOffer(Apple), Orange -> NotOnOffer(Orange))) extends ProductsOnOffer {
  override def offerFor(product: Product): Option[Offer] = productsOnOffer.get(product)
}
