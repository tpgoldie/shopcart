import shopcart._

class CheapestOfTwoFreeTest extends ProductsPricingTest {
  describe("Cheapest of two free") {
    it("buying one apple and one orange, orange is free") {
      val checkout = new Checkout(productsOnOffer = new ProductsOnOfferService(Map(Orange -> CheapestOfTwo(Orange, Apple))))

      Given("A list of one apple and one orange")
      And("Cheapest of two applies on oranges")
      val items = Seq("Apple","Orange")

      When("the checkout receives the list of items")
      val actual = checkout.calculateTotalCost(items)

      Then("it calculates the total cost of the items, with the offer applied")
      actual should be(Price(60))
      actual.toString should be("£0.60")
    }
  }
}
