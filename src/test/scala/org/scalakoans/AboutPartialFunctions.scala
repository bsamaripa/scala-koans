package org.scalakoans

import support.KoanSuite
import support.BlankValues.__

class AboutPartialFunctions extends KoanSuite  {

  koan("""A partial function is a trait that when
          | implemented can be used as building blocks to determine
          | a solution.  The trait PartialFunction requires that the
          | the method isDefinedAt and apply be implemented.""") {

    val doubleEvens: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
      //States that this partial function will take on the task
      def isDefinedAt(x: Int) = x % 2 == 0

      //What we do if this does partial function matches
      def apply(v1: Int) = v1 * 2
    }

    val tripleOdds: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
      def isDefinedAt(x: Int) = x % 2 != 0

      def apply(v1: Int) = v1 * 3
    }

    val whatToDo = doubleEvens orElse tripleOdds //Here we chain the partial functions together

    whatToDo(3) should be(__)
    whatToDo(4) should be(__)
  }

  koan("""Case statements are a quick way to create partial functions. When you create a case
           | statement, the apply and isDefinedAt is created for you.""") {

    //The case statements are called case statements with guards
    val doubleEvens: PartialFunction[Int, Int] = {
      case x: Int if ((x % 2) == 0) => x * 2
    }
    val tripleOdds: PartialFunction[Int, Int] = {
      case x: Int if ((x % 2) != 0) => x * 3
    }

    val whatToDo = doubleEvens orElse tripleOdds //Here we chain the partial functions together
    whatToDo(3) should be(__)
    whatToDo(4) should be(__)
  }

  koan("""The result of partial functions can have an \'andThen\' function added to the end
          | of the chain""") {

    //These are called case statements with guards
    val doubleEvens: PartialFunction[Int, Int] = {
      case x: Int if ((x % 2) == 0) => x * 2
    }
    val tripleOdds: PartialFunction[Int, Int] = {
      case x: Int if ((x % 2) != 0) => x * 3
    }

    val addFive = (x: Int) => x + 5
    val whatToDo = doubleEvens orElse tripleOdds andThen addFive //Here we chain the partial functions together
    whatToDo(3) should be(__)
    whatToDo(4) should be(__)
  }

  koan("""The result of partial functions can have an \'andThen\' function added to the end
          | of the chain used to continue onto another chain of logic""") {

    val doubleEvens: PartialFunction[Int, Int] = {
      case x: Int if ((x % 2) == 0) => x * 2
    }
    val tripleOdds: PartialFunction[Int, Int] = {
      case x: Int if ((x % 2) != 0) => x * 3
    }

    val printEven: PartialFunction[Int, String] = {
      case x: Int if ((x % 2) == 0) => "Even"
    }
    val printOdd: PartialFunction[Int, String] = {
      case x: Int if ((x % 2) != 0) => "Odd"
    }

    val whatToDo = doubleEvens orElse tripleOdds andThen (printEven orElse printOdd)

    whatToDo(3) should be(__)
    whatToDo(4) should be(__)
  }
}