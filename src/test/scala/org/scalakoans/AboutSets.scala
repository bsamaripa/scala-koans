package org.scalakoans

import support.KoanSuite
import support.BlankValues._

import java.util.Date

class AboutSets extends KoanSuite  {

  koan("Sets can be created easily") {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    mySet.size should be(__)
  }

  koan("Sets contain distinct values") {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Michigan")
    mySet.size should be(__)
  }

  koan("Sets can be added to easily") {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet + "Illinois"

    aNewSet.contains("Illinois") should be(__)

  }

  koan("Sets may be of mixed type") {
    val mySet = Set("Michigan", "Ohio", 12)

    mySet.contains(12) should be(__)

    mySet.contains("MI") should be(__)

  }

  koan("Sets may be accessed") {
    val mySet = Set("Michigan", "Ohio", 12)

    mySet(12) should be(__)
    mySet("MI") should be(__)

  }

  koan("Set elements can be removed easily") {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet - "Michigan"

    aNewSet.contains("Michigan") should be(__)
  }

  koan("Set elements can be removed in multiple") {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet -- List("Michigan", "Ohio")

    aNewSet.contains("Michigan") should be(__)
    aNewSet.contains("Wisconsin") should be(__)
    aNewSet.size should be(__)
  }

  koan("Attempted removal of nonexistent elements from a set is handled gracefully") {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet - "Minnesota"

    aNewSet.equals(mySet) should be(__)
  }

  koan("Sets can be iterated easily") {
    val mySet = Set(1, 3, 4, 9)
    var sum = 0
    for (i <- mySet)
      sum = sum + i

    sum should be(__)
  }

  koan("Two sets can be intersected easily") {
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan", "Minnesota")
    val aNewSet = mySet1 intersect mySet2
    // NOTE: Scala 2.7 used **, deprecated for & or intersect in Scala 2.8

    aNewSet.equals(Set("Michigan", "Wisconsin")) should be(__)

  }

  koan("Two sets can be joined as their union easily") {
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan", "Minnesota")
    val aNewSet = mySet1 union mySet2 // NOTE: You can also use the "|" operator

    aNewSet.equals(Set("Michigan", "Wisconsin", "Ohio", "Iowa", "Minnesota")) should be(__)

  }

  koan("A set is either a subset of another set or it isn't") {
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan", "Minnesota")
    val mySet3 = Set("Wisconsin", "Michigan")

    mySet2 subsetOf mySet1 should be(__)
    mySet3 subsetOf mySet1 should be(__)

  }

  koan("The difference between two sets can be obtained easily") {
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan")
    val aNewSet = mySet1 diff mySet2 // Note: you can use the "&~" operator if you *really* want to.

    aNewSet.equals(Set("Ohio", "Iowa")) should be(__)
  }
  koan("Set equivalency is independent of order") {
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan", "Ohio", "Iowa")

    mySet1.equals(mySet2) should be(__)
  }

}
