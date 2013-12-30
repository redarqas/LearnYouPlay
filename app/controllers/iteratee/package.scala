package controllers

import play.api.libs.iteratee._
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits._

package object iteratee {
  implicit class EnumeratorOps[T](enum: Enumerator[T]) {
    def scanL[B](z: B)(f: (B, T) => B): Enumerator[B] = {
      val (pushTo, channel) = Concurrent.broadcast[B]
      enum |>> Iteratee.fold(z)((acc:B, e:T) => {
        val r = f(acc , e)
        channel push r
        r
      })
      pushTo
    }
  }
}