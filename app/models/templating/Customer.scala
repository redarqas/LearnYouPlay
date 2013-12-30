package models.templating

case class Customer(firstName: String, lastName: String, age: Int)

object Customer {
  def all() : List[Customer] = Nil
}