package org.scalakoans

import support.KoanSuite
import support.BlankValues._

import java.util.Date

class AboutUniformAccessPrinciple extends KoanSuite  {

  class CalculatesAgeUsingMethod(var currentYear: Int, birthYear: Int) {

    def age = currentYear - birthYear

    // calculated when method is called
  }

  class CalculatesAgeUsingProperty(var currentYear: Int, birthYear: Int) {
    // does age stay up to date if defined as a var instead of a val?
    val age = currentYear - birthYear
    // calculated at instantiation, returns property when called
  }

  koan("Can access age as parameterless method") {
    val me = new CalculatesAgeUsingMethod(2010, 2003)
    me.age should be(__)
  }

  koan("Can access age as property") {
    val me = new CalculatesAgeUsingProperty(2010, 2003)
    me.age should be(__)
  }

  koan("Cannot add parameter to Method invocation") {
    val me = new CalculatesAgeUsingMethod(2010, 2003)
    // uncomment following line to see what happens if you try to access parameterless method with parens
    //me.age() should be (7)
  }
  koan("What happens when I update current year using property") {
    val me = new CalculatesAgeUsingProperty(2010, 2003)


    me.currentYear = 2011
    me.age should be(__)
  }

  koan("What happens when I update current year using method") {
    val me = new CalculatesAgeUsingMethod(2010, 2003)


    me.currentYear = 2011
    me.age should be(__)
  }


}