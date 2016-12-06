import shopcart.{Checkout, Price}

class BuyOneGetOneFreeTest extends ProductsPricingTest {
  describe("buy one get one free offer") {
    val checkout = new Checkout

    it("handles an empty list") {
      Given("An empty list")
      And("BOGOF applies on apples")
      val items = Seq()

      When("the checkout receives the list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items, no offer applied")
      actual should be(Price(0))
      actual.toString should be("£0.00")
    }

    it("buying one item of a product BOGOF is not applied") {
      Given("A list of one apple")
      And("BOGOF applies on apples")
      val items = Seq("Apple")

      When("the checkout receives the list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items, no offer applied")
      actual should be(Price(60))
      actual.toString should be("£0.60")
    }

    it("buying two items of the same product BOGOF is applied") {
      Given("A list of two apples")
      And("BOGOF applies on apples")
      val items = Seq("Apple","Apple")

      When("the checkout receives the list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items, with the offer applied")
      actual should be(Price(60))
      actual.toString should be("£0.60")
    }

    it("buying three items of mixed products BOGOF is applied for one product") {
      Given("A list of two apples and an orange")
      And("BOGOF applies on apples")
      val items = Seq("Apple", "Orange", "Apple")

      When("the checkout receives the list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items, with the offer applied")
      actual should be(Price(85))
      actual.toString should be("£0.85")
    }

    it("buying lots of items of mixed products BOGOF is applied for one product") {
      Given("A list of mixed items")
      And("BOGOF applies on apples")
      val items = Seq("Apple", "Apple", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple")

      When("the checkout receives the list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items, with the offer applied")
      actual should be(Price(255))
      actual.toString should be("£2.55")
    }
  }
}
