package io.github.tjheslin1.essential

import java.util.Date

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._

object CatsShow extends App {

  /*
    The call of `show` throws an error as Int and String don't have the method `show`.

          // this true?
              The compiler tries to resolve this by looking for an implicit class which takes an Int/String as an argument
              and has a method `show` to call.

    `import cats.syntax.show._` gives us an implementation of `Show.ToShowOps` with a method `ToShowOps#toShow` which
    requires an implicit instance of `Show[A]`.

    `cats.instances.int._` and `cats.instances.string._` give us implicit instances of `Show[Int]`
    and `Show[String]` respectively.

    Our `Show.ToShowOps` gives us a `Show.Ops[A]` which has the `show` method. This delegates to `fromToString` which
    is the implementation provided by `cats.instances.int._` and `cats.instances.string._`.
   */
  val shownInt = 123.show
  println(s"shownInt: $shownInt")

  val shownString = "123".show
  println(s"showString: $shownString")

  // Since `Show` only has one method to implement the boiler plate code of `new Show[Date]... can be simplified.`
  implicit val dateShow: Show[Date] = { date: Date => s"${date.getTime}ms since the epoch."}
  //    new Show[Date] {
  //      def show(date: Date): String =
  //        s"${date.getTime}ms since the epoch."
  //    }

  val shownDate = new Date().show
  println(s"now: $shownDate")

  final case class Cat(name: String, age: Int, color: String)

  implicit val catShow: Show[Cat] = {cat: Cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."}

  val shownCat = Cat("ginger", 7, "orange").show
  println(s"shownCat: $shownCat")
}
