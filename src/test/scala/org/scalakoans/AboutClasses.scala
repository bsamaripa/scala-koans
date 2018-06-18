package org.scalakoans

import support.KoanSuite
import support.BlankValues.__

class AboutClasses extends KoanSuite  {


  // you can define class with var or val parameters
  class ClassWithVarParameter(var description: String) {

  }

  koan("val parameters in class definition define getter") {
    val aClass = new ClassWithValParameter("name goes here")
    aClass.name should be(__)
  }

  class ClassWithValParameter(val name: String) {

  }

  koan("var parameters in class definition define getter and setter") {
    val aClass = new ClassWithVarParameter("description goes here")
    aClass.description should be(__)

    aClass.description = "new description"
    aClass.description should be(__)
  }

  // you can define class with private fields
  class ClassWithPrivateFields(name: String) {

  }

  koan("fields defined internally are private to class") {
    val aClass = new ClassWithPrivateFields("name")

    // NOTE: aClass.name is not accessible
  }

}