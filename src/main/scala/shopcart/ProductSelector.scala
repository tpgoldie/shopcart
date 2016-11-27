package shopcart

trait ProductSelector {
  def products: Seq[Product]

  def selectProduct(item: String): Option[Product] = {
    products.filter( _.name == item.toLowerCase ) match {
      case x::y => Some(x)
      case List() => None
    }
  }
}
