package dao

object User extends cagette.Cageot[models.User, Long] {
  override def initialData = Seq (
      models.User(1, "Chaqouri","Jamal","j@j.j")
  ) 
}