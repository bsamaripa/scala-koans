package org.scalakoans

import support.KoanSuite
import support.BlankValues.__

class AboutConstructors extends KoanSuite {

  class AboutConstructorWithValParameter(val name: String) {
    // invoke auxilary constructor
    def this() {
      // what happens if you comment out the following line?
      this ("defaultname")
    }
  }

  class AboutClassWithNoClassParameter {
  }

  class AboutConstructorWithVarParameter(var name: String) {
  }

  class AboutConstructorWithPrivateClassParameter(name: String) {
  }

  koan("val in class definition defines read only property") {
    val aboutMe = new AboutConstructorWithValParameter("MyName")
    aboutMe.name should be(__)
  }

  koan("var in class definition defines read/write parameters") {
    val aboutMe = new AboutConstructorWithVarParameter("MyName")
    aboutMe.name = "YourName"
    aboutMe.name should be(__)
  }

  koan("private member data is not accessible") {
    val aboutMe = new AboutConstructorWithPrivateClassParameter("MyName")

    // what happens if you uncomment this line? why?
    // aboutMe.name = "Me"
  }

  koan("Primary constructor specified with a parameter requires that parameter to be passed in") {
    // val aboutMe = new AboutConstructorWithValParameter()
  }

  koan("Class with no class parameters is called with no arguments") {
    // add parameter to make this fail
    val aboutMe = new AboutClassWithNoClassParameter
  }
}
