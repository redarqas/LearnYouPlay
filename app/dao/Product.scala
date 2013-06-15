package dao

object Product extends cagette.Cageot[models.Product, Long] {
  override def initialData = Seq (
      models.Product(1, "Patates", 1.25),
      models.Product(2, "Tomates", 1),
      models.Product(3, "Bananes", .75),
      models.Product(4, "Pommes", 2.05),
      models.Product(5, "Sandwich", 2.05)
  )
  
}