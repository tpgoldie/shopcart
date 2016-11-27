package shopcart

sealed trait Offer {
  def product: Product

  def applyTo(items: Seq[String]): Price
}

case class BuyOneGetOneFree(override val product: Product) extends Offer with Constants {
  override def applyTo(items: Seq[String]): Price = {
    val productFilter = product match {
      case Apple => AppleFilter(items)
      case Orange => OrangeFilter(items)
    }

    val size =  productFilter.filteredProducts.size

    val quotient = size / 2

    val remainder = size % 2

    val price = (quotient > 0, remainder > 0) match {
      case (false, false) => Zero
      case _ => product.price * (quotient+remainder)
    }

    price
  }
}
