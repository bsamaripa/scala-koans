package org.scalakoans

import support.KoanSuite
import support.BlankValues._


class AboutOptions extends KoanSuite  {

  koan("Option can have one of two values - Some or None") {
    val someValue: Option[String] = Some("I am wrapped in something")
    someValue.get should be(__)

    val nullValue: Option[String] = None
    nullValue should be(__)
  }

  koan("Represent null with None because null is a bad idea") {
    val value1 = maybeItWillReturnSomething(true)
    val value2 = maybeItWillReturnSomething(false)

    value1.get should be(__)
    intercept[java.util.NoSuchElementException] {
      value2.get
    }
  }

  koan("Provide a default value for None") {
    val value1 = maybeItWillReturnSomething(true)
    val value2 = maybeItWillReturnSomething(false)

    value1 getOrElse "No value" should be(__)
    value2 getOrElse "No value" should be(__)
    value2 getOrElse {
      "default function"
    } should be(__)

  }

  koan("checking whether option has value") {
    val value1 = maybeItWillReturnSomething(true)
    val value2 = maybeItWillReturnSomething(false)

    value1.isEmpty should be(__)
    value2.isEmpty should be(__)
  }

  koan("Option can also be used with pattern matching") {
    val someValue: Option[Double] = Some(20.0)
    val value = someValue match {
      case Some(v) => v
      case None => 0.0
    }
    value should be(__)
    val noValue: Option[Double] = None
    val value1 = noValue match {
      case Some(v) => v
      case None => 0.0
    }
    value1 should be(__)

  }

  koan("Option is more than just a replacement of null, its also a collection") {
    Some(10) map {
      _ + 10
    } should be(__)
    Some(10) filter {
      _ == 10
    } should be(__)
    Some(Some(10)) flatMap {
      _ map {
        _ + 10
      }
    } should be(__)

    var newValue1 = 0
    Some(20) foreach {
      newValue1 = _
    }
    newValue1 should be(__)

    var newValue2 = 0
    None foreach {
      newValue2 = _
    }
    newValue2 should be(__)
  }

  koan("Using Option to avoid if checks for null") {
    //the ugly version
    def makeFullName(firstName: String, lastName: String) = {
      if (firstName != null) {
        if (lastName != null) {
          firstName + " " + lastName
        } else {
          null
        }
      } else {
        null
      }
    }
    makeFullName("Nilanjan", "Raychaudhuri") should be(__)
    makeFullName("Nilanjan", null) should be(__)
    //the pretty version
    def makeFullNamePrettyVersion(firstName: Option[String], lastName: Option[String]) = {
      firstName flatMap {
        fname =>
          lastName flatMap {
            lname =>
              Some(fname + " " + lname)
          }
      }
    }
    makeFullNamePrettyVersion(Some("Nilanjan"), Some("Raychaudhuri")) should be(__)
    makeFullNamePrettyVersion(Some("Nilanjan"), None) should be(__)
  }

  koan("Using in for comprehension") {
    val values = List(Some(10), Some(20), None, Some(15))
    val newValues = for {
      someValue <- values
      value <- someValue
    } yield value
    newValues should be(__)
  }

  def maybeItWillReturnSomething(flag: Boolean): Option[String] = {
    if (flag) Some("Found value") else None
  }
}