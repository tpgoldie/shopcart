package shopcart

sealed trait Offer extends Constants {
  def product: Product

  def applyTo(items: Seq[String]): Price

  protected def selectFilter(items: Seq[String]): ProductFilter = {
    selectFilter(product, items)
  }

  protected def selectFilter(product: Product, items: Seq[String]): ProductFilter = {
    product match {
      case Apple => AppleFilter(items)
      case Orange => OrangeFilter(items)
    }
  }
}

case class NotOnOffer(override val product: Product) extends Offer {
  override def applyTo(items: Seq[String]): Price = {
    val productFilter = selectFilter(items)
    val size =  productFilter.filteredProducts.size

    product.price * size
  }
}

case class BuyOneGetOneFree(override val product: Product) extends Offer {
  override def applyTo(items: Seq[String]): Price = {
    val productFilter = selectFilter(items)

    val size =  productFilter.filteredProducts.size

    val factor = 2

    val quotient = size / factor

    val remainder = size % factor

    val price = (quotient > 0, remainder > 0) match {
      case (false, false) => Zero
      case _ => product.price * (quotient+remainder)
    }

    price
  }
}

case class ThreeForTwo(override val product: Product) extends Offer {
  override def applyTo(items: Seq[String]): Price = {
    val productFilter = selectFilter(items)

    val size =  productFilter.filteredProducts.size

    val factor = 3

    val priceFactor = 2

    val quotient = size / factor

    val remainder = size % factor

    val price = (quotient > 0, remainder > 0) match {
      case (true, false) => product.price * priceFactor
      case _ => Zero
    }

    price
  }
}

case class CheapestOfTwo(override val product: Product, expensiveProduct: Product) extends Offer {
  override def applyTo(items: Seq[String]): Price = {
    val cheapestProducts = selectFilter(product, items).filteredProducts

    val expensiveProducts = selectFilter(expensiveProduct, items).filteredProducts

    val pairs = expensiveProducts zip cheapestProducts

    val costOfExpensiveProducts = expensiveProduct.price * pairs.size

    val leftOverSize = cheapestProducts.size - expensiveProducts.size

    val leftOversCost = if (leftOverSize >= 0) { product.price * leftOverSize }
    else { expensiveProduct.price * Math.abs(leftOverSize) }

    costOfExpensiveProducts + leftOversCost
  }
}