import org.scalatest.{FunSpec, GivenWhenThen, Matchers}
import shopcart.{Checkout, Price}

class CheckoutTest extends FunSpec with GivenWhenThen with Matchers {
  describe("checkout") {
    it("calculates the total cost of a list of items") {
      Given("a list of apples and oranges")
      val checkout = new Checkout
      val items = Seq("Apple", "Apple", "Orange", "Apple")

      When("the checkout receives a list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items")
      actual should be(Price(205))
      actual.toString should be("£2.05")
    }
  }
}
