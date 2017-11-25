package io.github.tjheslin1.essential

import java.util.Date

import cats.Eq
import cats.syntax.eq._
import cats.instances.int._
import cats.instances.option._

object CatsEq extends App {

  val eqInt = Eq[Int]

  eqInt.eqv(123, 123)
  eqInt.eqv(123, 234)

//  compile error: type mismatch
//  eqInt.eqv(123, "234")

  123 === 123
  123 =!= 234

//  compile error: type mismatch
//  123 === "123"

//  compile error: === is not a member of Some[Int]
//  Some(1) === None

  Option(1) === Option.empty[Int]

  import cats.syntax.option._

  1.some === none[Int]
  1.some =!= none[Int]


  import cats.instances.long._

  implicit val dateEq: Eq[Date] =
    Eq.instance[Date] { (date1, date2) =>
      date1.getTime === date2.getTime
    }

  val x = new Date()
  Thread.sleep(10)
  val y = new Date()

  val sameDate = x === x
  println(s"sameDate: $sameDate")
  val sameDateNot = x =!= x
  println(s"sameDateNot: $sameDateNot")

  val diffDates = x === y
  println(s"diffDates: $diffDates")
  val diffDatesNot = x =!= y
  println(s"diffDates: $diffDatesNot")

  final case class Cat(name: String, age: Int, color: String)

  val cat1 = Cat("Garfield",   38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  implicit val catEq: Eq[Cat] =
    Eq.instance[Cat] { (cat1, cat2) =>

      import cats.instances.string._

      cat1.name === cat2.name &&
      cat1.age === cat2.age &&
      cat1.color === cat2.color
    }

  val sameCat = cat1 === cat1
  println(s"sameCat: $sameCat")
  val sameCatNot = cat1 =!= cat1
  println(s"sameCatNot: $sameCatNot")

  val diffCats = cat1 === cat2
  println(s"diffCats: $diffCats")
  val diffCatsNot = cat1 =!= cat2
  println(s"diffCats: $diffCatsNot")

  val sameCatOpt = optionCat1 === optionCat1
  println(s"sameCatOpt: $sameCatOpt")
  val sameCatOptNot = optionCat1 =!= optionCat1
  println(s"sameCatOpt: $sameCatOptNot")

  val diffCatsOpt = optionCat1 === optionCat2
  println(s"diffCatsOpt: $diffCatsOpt")
  val diffCatsOptNot = optionCat1 =!= optionCat2
  println(s"diffCatsOpt: $diffCatsOptNot")
}
