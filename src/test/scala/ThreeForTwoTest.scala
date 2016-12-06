import shopcart._

class ThreeForTwoTest extends ProductsPricingTest {
  describe("Three for two offer (TFT") {
    val checkout = new Checkout(new ProductsOnOfferService(Map(Apple -> NotOnOffer(Apple), Orange -> ThreeForTwo(Orange))))

    it("handles an empty list") {
      Given("An empty list")
      And("TFT applies on oranges")
      val items = Seq()

      When("the checkout receives the list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items, no offer applied")
      actual should be(Price(0))
      actual.toString should be("£0.00")
    }

    it("buying three items of the same product TFT is applied") {
      Given("A list of three oranges")
      And("TFT applies on oranges only")
      val items = Seq("Apple","Orange", "Orange", "Apple", "Orange")

      When("the checkout receives the list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items, with the offer applied")
      actual should be(Price(2 * 60 + 2 * 25))
      actual.toString should be("£1.70")
    }
  }
}
