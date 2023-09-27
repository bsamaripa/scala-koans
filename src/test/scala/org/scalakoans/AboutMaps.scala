package org.scalakoans

import support.KoanSuite
import support.BlankValues._

import java.util.Date

class AboutMaps extends KoanSuite  {

  koan("Maps can be created easily") {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    myMap.size should be(__)
  }

  koan("Maps contain distinct pairings") {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Michigan")
    myMap.size should be(__)


  }

  koan("Maps can be added to easily") {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Michigan")

    val aNewMap = myMap + ("IL" -> "Illinois")

    aNewMap.contains("IL") should be(__)

  }

  koan("Map values can be iterated") {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Michigan")

    val mapValues = myMap.values

    mapValues.size should be(__)

    mapValues.head should be(__)

    val lastElement = mapValues.last
    lastElement should be(__)

    // for (mval <- mapValues) println(mval)

    // NOTE that the following will not compile, as iterators do not implement "contains"
    //mapValues.contains("Illinois") should be (true)
  }

  koan("Maps insertion with duplicate key updates previous entry with subsequent value") {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Meechigan")

    val mapValues = myMap.values

    mapValues.size should be(__)

    myMap("MI") should be(__)
  }

  koan("Map keys may be of mixed type") {
    val myMap = Map("Ann Arbor" -> "MI", 49931 -> "MI")
    myMap("Ann Arbor") should be(__)
    myMap(49931) should be(__)
  }

  koan("Mixed type values can be added to a map ") {
    val myMap = scala.collection.mutable.Map.empty[String, Any]
    myMap("Ann Arbor") = (48103, 48104, 48108)
    myMap("Houghton") = 49931

    myMap("Houghton") should be(__)
    myMap("Ann Arbor") should be(__)



    // what happens if you change the Any to Int

  }


  koan("Maps may be accessed") {

    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    myMap("MI") should be(__)
    myMap("IA") should be(__)

  }

  koan("Map elements can be removed easily") {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val aNewMap = myMap - "MI"
    aNewMap.contains("MI") should be(__)
  }

  koan("Accessing a map by key results in an exception if key is not found") {

    val myMap = Map("OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")

    intercept[NoSuchElementException] {
      myMap("MI") should be(__)
    }
  }

  koan("Map elements can be removed in multiple") {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")


    val aNewMap = myMap -- List("MI", "OH")

    aNewMap.contains("MI") should be(__)

    aNewMap.contains("WI") should be(__)
    aNewMap.size should be(__)
  }

  koan("Attempted removal of nonexistent elements from a map is handled gracefully") {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val aNewMap = myMap - "MN"

    aNewMap.equals(myMap) should be(__)
  }

  koan("Map equivalency is independent of order") {

    val myMap1 = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val myMap2 = Map("WI" -> "Wisconsin", "MI" -> "Michigan", "IA" -> "Iowa", "OH" -> "Ohio")


    myMap1.equals(myMap2) should be(__)
  }
}
