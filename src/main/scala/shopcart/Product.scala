package shopcart

import Price._

sealed abstract class Product(val name: String, val price: Price)

final object Apple extends Product("apple", 60)

final object Orange extends Product("orange", 25)