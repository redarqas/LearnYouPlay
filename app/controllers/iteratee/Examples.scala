package controllers.iteratee
import play.api.mvc._
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future
import scala.concurrent.Future._
import play.api.libs.iteratee._

object Examples {
  //Done iteratee
  def createDoneIter[E, A](a: A, remain: Input[E]): Iteratee[E, A] = new Iteratee[E, A] {
    def done: Step[E, A] = Step.Done(a, remain)
    def fold[B](f: Step[E, A] => Future[B])(implicit ex: ExecutionContext): Future[B] = {
      f(done)
    }
  }
  //Return first input
  def creatFirstInput[E](): Iteratee[E, E] = new Iteratee[E, E] { self =>
    def first: Step[E, E] = Step.Cont {
      case Input.Empty => self
      case Input.EOF => Error("Empty stream", Input.EOF)
      case Input.El(x) => Done(x, Input.EOF)
    }
    def fold[B](f: Step[E, E] => Future[B])(implicit ex: ExecutionContext): Future[B] = {
      f(first)
    }
  }

}