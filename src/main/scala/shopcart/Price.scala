package shopcart

case class Price(value: Integer) {
  def +(that: Price): Price = Price(this.value + that.value)

  override def toString: String = f"£${value / 100.0}%.2f"
}

object Price {
  implicit def toPrice(value: Int): Price = Price(value)
}