package shopcart

trait PriceSelector extends Constants {
  def products: Seq[Product]

  def selectPrice(item: String): Price = {
    products.filter( _.name == item.toLowerCase ) match {
      case x::y => x.price
      case List() => Zero
    }
  }
}
