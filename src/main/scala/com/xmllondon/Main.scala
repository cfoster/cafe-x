package com.xmllondon

object Main extends App {

  val order = Order(Cola, Coffee, CheeseSandwich)

  /**
    * Step 1 : Standard Bill
    * Pass in a list of purchased items that produces a total bill.
    * For example [“Cola”, “Coffee”, “Cheese Sandwich”] returns 3.5
    **/
  println(order)
  println("-----------------")
  println(s"Sub Total\t£${order.subTotal.formatted("%2.2f")}")

  /**
    * Step 2: Service Charge
    * Add support for a service charge:
    * 1. When all purchased items are drinks no service charge is applied
    * 2. When purchased items include any food apply a service charge of 10% to
    *    the total bill (rounded to 2 decimal places)
    * 3. When purchased items include any hot food apply a service charge of
    *    20% to the total bill with a maximum £20 service charge
   **/
  println(s"Service Fee\t£${order.serviceCharge.formatted("%2.2f")}")
  println(s"Total\t\t£${order.total.formatted("%2.2f")}")
}
