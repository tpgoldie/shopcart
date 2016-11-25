import org.scalatest.{FunSpec, GivenWhenThen, Matchers}
import shopcart.{Checkout, Price}

class CheckoutTest extends FunSpec with GivenWhenThen with Matchers {
  describe("checkout") {
    it("calculates the total cost of a list of just apples and oranges") {
      Given("a list of apples and oranges")
      val checkout = new Checkout
      val items = Seq("Apple", "Apple", "Orange", "Apple")

      When("the checkout receives a list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items")
      actual should be(Price(205))
      actual.toString should be("£2.05")
    }

    it("calculates the total cost of a list of apples") {
      Given("a list of apples")
      val checkout = new Checkout
      val items = Seq("Apple", "Apple")

      When("the checkout receives a list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items")
      actual should be(Price(120))
      actual.toString should be("£1.20")
    }

    it("calculates the total cost of a list of oranges") {
      Given("a list of oranges")
      val checkout = new Checkout
      val items = Seq("Orange", "Orange", "Orange")

      When("the checkout receives a list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items")
      actual should be(Price(75))
      actual.toString should be("£0.75")
    }

    it("calculates the total cost of a empty list") {
      Given("an empty list")
      val checkout = new Checkout
      val items = Seq()

      When("the checkout receives a list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items")
      actual should be(Price(0))
      actual.toString should be("£0.00")
    }

    it("calculates the total cost of a list of apples and oranges and no other products") {
      Given("a list of apples, oranges, and bananas")
      val checkout = new Checkout
      val items = Seq("Apple", "Apple", "Orange", "Banana", "Apple", "Orange", "Banana")

      When("the checkout receives a list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items")
      actual should be(Price(230))
      actual.toString should be("£2.30")
    }
  }
}
