package org.scalakoans

import support.KoanSuite
import support.BlankValues._

import java.util.Date

class AboutTuples extends KoanSuite  {

  koan("Tuples can be created easily") {
    val tuple = ("apple", "dog")
    tuple should be(__)
  }

  koan("Tuple items may be accessed individually") {
    val tuple = ("apple", "dog")
    val fruit = tuple._1
    val animal = tuple._2

    fruit should be(__)
    animal should be(__)
  }

  koan("Tuples may be of mixed type") {
    val tuple5 = ("a", 1, 2.2, new Date(), BigDecimal(5))

    tuple5._1.isInstanceOf[String]
    tuple5._2.isInstanceOf[Int]
    tuple5._3 should be(__)
    tuple5._4.isInstanceOf[Date]
    tuple5._5 should be(__)
  }


}
