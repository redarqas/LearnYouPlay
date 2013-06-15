package dao

object Customer extends cagette.Cageot[models.Customer, Long] {
  override def initialData = Seq(
    models.Customer(1, "Chaqouri", "Jamal"),
    models.Customer(2, "Filin", "Marc"),
    models.Customer(3, "Xao", "Tano"),
    models.Customer(4, "Lionel", "Ago"),
    models.Customer(5, "Rodrigo", "Gomez"),
    models.Customer(6, "Fabrizio", "Visconci"))
} 