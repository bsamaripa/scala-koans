package org.scalakoans

import support.KoanSuite
import support.BlankValues.__

class AboutInfixPrefixAndPostfixOperators extends KoanSuite {

  koan("""Simple: Infix Operators are available if an object
           |  has a method that takes one parameter.""") {

    val g: Int = 3
    (g + 4) should be(__) // + is an infix operator
    g.+(4) should be(__) // same result but not using the infix operator
  }

  koan("""Infix Operators do NOT work if an object
           |  has a method that takes two parameters.""") {
    val g: String = "Check out the big brains on Brad!"
    g indexOf 'o' should be(__) // can be used as an infix operator
    //g indexOf 'o', 4 should be (6) //indexOf(Char, Int) cannot be used an infix operator
    g.indexOf('o', 7) should be(__) // must use standard java/scala calls
  }

  koan("""Postfix operators work if an object
           |  has a method that takes no parameters.""") {
    val g: Int = 31
    (g.toHexString) should be(__) //toHexString takes no params therefore can be called
    //as a postfix operator. Hint: The answer is 1f
  }


  koan("""Prefix operators work if an object
           |  has a method name that starts with unary_ .""") {
    val g: Int = 31
    (-31) should be(__)
  }

  koan("""Here we create our own prefix operator for our own class.
          |   The only identifiers that can be used as prefix operators
          |   are +, -, !, and ~""") {

    class Stereo {
      def unary_+ = "on"

      def unary_- = "off"
    }

    val stereo = new Stereo
    (+stereo) should be(__)
    (-stereo) should be(__)
  }

}