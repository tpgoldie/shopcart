package shopcart

sealed trait ProductFilter {
  def product: Product

  def items: Seq[String]

  val filteredItems: Seq[String] = items.filter(_.toLowerCase == product.name)

  val filteredProducts: Seq[Product] = List.fill(filteredItems.size)(product)
}


case class OrangeFilter(override val items: Seq[String]) extends ProductFilter {
  override def product = Orange
}

case class AppleFilter(override val items: Seq[String]) extends ProductFilter {
  override def product = Apple
}