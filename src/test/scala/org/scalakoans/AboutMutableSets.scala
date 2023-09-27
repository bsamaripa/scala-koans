package org.scalakoans

import support.KoanSuite
import support.BlankValues._

import java.util.Date
import scala.collection.mutable

class AboutMutableSets extends KoanSuite  {

  koan("Mutable sets can be created easily") {
    val mySet = mutable.Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    mySet.size should be(__)
    mySet += "Oregon"
    mySet contains "Oregon" should be(__)
  }

  koan("Mutable sets can have elements removed") {
    val mySet = mutable.Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    mySet -= "Ohio"
    mySet contains "Ohio" should be(__)
  }

  koan("Mutable sets can have tuples of elements added") {
    val mySet = mutable.Set("Michigan", "Wisconsin")
    mySet += ("Iowa", "Ohio")
    mySet contains "Ohio" should be(__)
    mySet.size should be(__)
  }

  koan("Mutable sets can have Lists of elements added") {
    val mySet = mutable.Set("Michigan", "Wisconsin")
    mySet ++= List("Iowa", "Ohio")
    mySet contains "Ohio" should be(__)
    mySet.size should be(__)
  }

  koan("Mutable sets can have Lists of elements removed") {
    val mySet = mutable.Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    mySet --= List("Iowa", "Ohio")
    mySet contains "Ohio" should be(__)
    mySet.size should be(__)
  }

  koan("Mutable sets can be cleared") {
    val mySet = mutable.Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    mySet.clear() // Convention is to use parens if possible when method called changes state
    mySet contains "Ohio" should be(__)
    mySet.size should be(__)
  }

}
