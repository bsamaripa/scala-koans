package org.scalakoans

import support.KoanSuite
import support.BlankValues.__

class AboutTypeProjections extends KoanSuite  {


  class Fruit

  class Citrus extends Fruit

  class Orange extends Citrus

  class Tangelo extends Citrus

  class Apple extends Fruit

  class Banana extends Fruit

  koan("In generic form3") {
    trait X {
      type Y <: Fruit
    }

    class TangeloBasket extends X {
      type Y = Tangelo
    }

    class AppleBasket extends X {
      type Y = Apple
    }
    classOf[TangeloBasket#Y].getSimpleName should be(__)
    classOf[AppleBasket#Y].getSimpleName should be(__)
  }

  koan("In generic form4") {
    trait X {
      type Y <: Fruit

      def whatsFeedingMe: String
    }

    val x = new X {
      type Y = Tangelo

      override def whatsFeedingMe = classOf[Y].getSimpleName
    }

    val z = new X {
      type Y = Orange

      override def whatsFeedingMe = classOf[Y].getSimpleName
    }

    println(x.whatsFeedingMe)
    println(z.whatsFeedingMe)
  }


  koan("In generic form65") {
    trait X {
      type Y
    }

    val x = new X {
      type Y = Tangelo
    }

    val z = new X {
      type Y = Orange
    }

    class AwesomeX extends X {
      type Y = String
    }

    println(classOf[AwesomeX#Y].getSimpleName)

    val r = new X {
      type Y = Apple
    }

    println(classOf[r.Y].getSimpleName)
  }



  /*
scala> val r = new X {
    |   type Y = String
    | }
<console>:9: error: overriding type Y in trait X, which equals String;
type Y needs `override' modifier
        type Y = String
             ^

scala> val r = new X {
    |   override type Y = Int
    | }
<console>:9: error: overriding type Y in trait X, which equals String;
type Y has incompatible type
        override type Y = Int
                      ^

  */
}