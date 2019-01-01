package com.xmllondon

import org.scalatest.FunSuite

class OrderSuite extends FunSuite
{

  /** Standard Bill **/
  test("Pass in a list of purchased items that produces a total bill") {
    val order = Order(Cola, Coffee, CheeseSandwich)
    assert(order.subTotal == 3.5, "Items should add up to £3.50")
  }

  /** Service Charge **/
  test("When all purchased items are drinks no service charge is applied") {
    val order = Order(Cola, Coffee)

    assert(order.serviceChargePercentage == 0f)
    assert(order.total == order.subTotal)
  }

  test("When purchased items include any food apply a service charge " +
    "of 10% to the total bill (rounded to 2 decimal places)") {
    val order = Order(Cola, Coffee, CheeseSandwich)

    assert(order.serviceChargePercentage == 0.1f)

    val serviceCharge = order.subTotal * order.serviceChargePercentage
    val expectedTotal = order.subTotal + serviceCharge
    assert(order.total == expectedTotal)
  }

  test("Service Charge - When purchased items include any hot food apply a " +
    "service charge of 20% to the total bill") {
    val order = Order(Cola, Coffee, CheeseSandwich, SteakSandwich)

    assert(order.serviceChargePercentage == 0.2f)

    val serviceCharge = order.subTotal * order.serviceChargePercentage
    val expectedTotal = order.subTotal + serviceCharge
    assert(order.total == expectedTotal)
  }

  test("Service Charge - Enforce a maximum service charge of £20") {

    val manySteakSandwiches = List.tabulate(100)(_=>SteakSandwich)

    val order = Order(manySteakSandwiches: _*)

    assert(order.serviceCharge == 20f)
  }

  test("Empty order, no total") {

    val emptySeq=List.empty[Item]

    val emptyOrder = Order(emptySeq: _*)

    assert(emptyOrder.total == 0)
  }

  test("Take a list of item names, including bad names") {
    val nameList=List("Cola", "Coffee", "Cheese Sandwich", "**unknown**")

    val items=nameList.flatMap(Menu.menuMap.get)

    val order=Order(items: _*)

    assert(order.total == 3.85f)
  }

}
