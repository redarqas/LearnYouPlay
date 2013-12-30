package models.templating

case class Order(productName: String, amount: Double)
object Order {
    def all() : List[Order] = Nil
}