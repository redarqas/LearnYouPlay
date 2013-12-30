package controllers.iteratee

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future
import scala.concurrent.Future._
import play.api.libs.iteratee._

object Explaination {
  //Define foldM without   
  def foldM[E, A](state: A)(f: (A, E) => Future[A])(implicit ec: ExecutionContext): Iteratee[E, A] = {
    //Define how to manage steps 
    def step(s: A)(i: Input[E]): Iteratee[E, A] = i match {
      case Input.Empty => ContIter(step(s))
      case Input.EOF => DoneIter(s, Input.EOF)
      case Input.El(e) => {
        val fut = f(s, e)
        //We need to flatten future before to get the iter
        val futIter: Future[Iteratee[E, A]] = fut map { s1 => ContIter(step(s1)) }
        FutureIter(futIter)
      }
    }
    //Create the iteratee instance
    def k: Input[E] => Iteratee[E, A] = step(state)
    ContIter(k)
  }
  
  //Define Enumerator
  
}

object ContIter {
  def apply[E, A](k: Input[E] => Iteratee[E, A]): Iteratee[E, A] = new Iteratee[E, A] {
    def immediateUnflatten: Step[E, A] = Step.Cont(k)
    def fold[B](folder: Step[E, A] => Future[B])(implicit ec: ExecutionContext): Future[B] = {
      folder(immediateUnflatten)
    }
  }
}

object DoneIter {
  def apply[E, A](a: A, remain: Input[E]): Iteratee[E, A] = new Iteratee[E, A] {
    def immediateUnflatten: Step[E, A] = Step.Done(a, remain)
    def fold[B](folder: Step[E, A] => Future[B])(implicit ec: ExecutionContext): Future[B] = {
      folder(immediateUnflatten)
    }
  }
}

object ErrorIter {
  def apply[E, A](a: String, remain: Input[E]): Iteratee[E, A] = new Iteratee[E, A] {
    def immediateUnflatten: Step[E, A] = Step.Error(a, remain)
    def fold[B](folder: Step[E, A] => Future[B])(implicit ec: ExecutionContext): Future[B] = {
      folder(immediateUnflatten)
    }
  }
}

object FutureIter {
  def apply[E, A](futIter: Future[Iteratee[E, A]]): Iteratee[E, A] = new Iteratee[E, A] {
    def fold[B](folder: Step[E, A] => Future[B])(implicit ec: ExecutionContext): Future[B] = {
      futIter.flatMap(iter => iter.fold(folder))
    }
  }
}