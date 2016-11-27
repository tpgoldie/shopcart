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

    val products =  productFilter.filteredProducts

    val size = products.size

    val quotient = size / 2
    val quotientIsPositive = quotient > 0

    val remainder = size % 2
    val remainderIsPositive = remainder > 0

    val price = (quotientIsPositive, remainderIsPositive) match {
      case (false, false) => Zero
      case (false, true) => product.price
      case (true, false) => product.price * quotient
      case (true, true) => product.price * (quotient+1)
    }

    price
  }
}
