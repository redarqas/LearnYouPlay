package controllers.ch12

import play.api.libs.iteratee.Enumerator
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.mvc.ResponseHeader
import play.api.mvc.SimpleResult
import play.api.mvc.WebSocket
import play.api.libs.iteratee.Iteratee

object Exercises extends Controller {

  //get orders by customer 
  def basics(id: Long) = Action { request =>
    val customer = dao.Customer.findById(id).get
    val orders: Seq[(models.Order, models.Product)] = dao.Order.findBy(_.idCustomer == customer.id) map (order =>
      (order, dao.Product.findOneBy(_.id == order.idProduct).get))
    Ok(views.html.ch12.basics.render(customer, orders))
  }
  //Output raw content 
  def rawContent = Action {
    val myHtml: String = "<h1> show html as it is </h1>"
    Ok(views.html.ch12.rawcontent.render(myHtml))
  }
  //get orders by customer : using layou
  def orders(id: Long) = Action { request =>
    val customer = dao.Customer.findById(id).get
    val orders: Seq[(models.Order, models.Product)] = dao.Order.findBy(_.idCustomer == customer.id) map (order =>
      (order, dao.Product.findOneBy(_.id == order.idProduct).get))
    Ok(views.html.ch12.orders.render(customer, orders))
  }

}