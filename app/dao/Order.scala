package dao

object Order extends cagette.Cageot[models.Order, Long] {
  override def initialData = Seq(
    models.Order(1, 1, 1, 2),
    models.Order(2, 2, 1, 1),
    models.Order(3, 3, 1, 9),
    models.Order(4, 4, 1, 8),
    models.Order(5, 3, 1, 6),
    models.Order(6, 2, 2, 7),
    models.Order(7, 3, 3, 1),
    models.Order(8, 4, 4, 6),
    models.Order(9, 5, 4, 5),
    models.Order(10, 5, 5, 3))

} 