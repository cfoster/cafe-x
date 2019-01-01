package com.xmllondon

object Cola extends Drink with Cold{
  val label= "Cola"
  val price= 0.5f
}

object Coffee extends Drink with Hot{
  val label = "Coffee"
  val price = 1.00f
}

object CheeseSandwich extends Food with Cold{
  val label ="Cheese Sandwich"
  val price = 2.00f
}

object SteakSandwich extends HotFood{
  val label = "Steak Sandwich"
  val price= 4.50f
}

object Menu {
  private val items=List(Cola, Coffee, CheeseSandwich, SteakSandwich)
  val menuMap: Map[String, Item] =items.map(item=>item.label->item).toMap
}

trait Item {
  val label: String
  val price: Float
  def render: String=s"$label\t\t£${price.formatted("%2.2f")}"
}

sealed trait Temperature
trait Cold extends Temperature
trait Hot extends Temperature

sealed trait Consumable extends Item
trait Drink extends Consumable
trait Food extends Consumable
trait HotFood extends Hot with Food
