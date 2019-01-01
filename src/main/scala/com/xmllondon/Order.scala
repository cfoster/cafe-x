package com.xmllondon

case class Order(items: Item*) {

  def subTotal: Float = items.map(_.price).sum

  val hasFood: Boolean = items.exists { case _: Food => true; case _ => false }
  val justDrinks: Boolean = !hasFood
  val hasHotFood: Boolean = items.exists {case _: HotFood => true; case _ => false }

  val serviceChargePercentage: Float = {
    if (justDrinks) 0f
    else if (hasHotFood) 0.2f
    else if (hasFood) 0.1f
    else 0f
  }

  val serviceCharge: Float = Math.min(subTotal * serviceChargePercentage, 20f)

  val total: Float = subTotal + serviceCharge

  override def toString: String = items.map {
    _.render
  }.mkString("\n")
}