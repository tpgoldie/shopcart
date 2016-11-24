package shopcart

sealed abstract class Fruit(val name: String, val price: Integer)

final class Apple extends Fruit(name="apple", price=60)

final class Orange extends Fruit(name="orange", price=25)