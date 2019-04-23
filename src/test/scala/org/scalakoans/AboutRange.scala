package org.scalakoans

import scala.collection.immutable.List
import support.KoanSuite
import support.BlankValues._

class AboutRange extends KoanSuite {

  koan("Ranges can be converted to lists") {
    val range = Range(0, 3)
    range.toList should equal(List(__, __, __))
  }

  koan("Ranges are not inclusive of the end of the range by default") {
    val range = Range(0, 3)
    range.toList should equal(List(__))
  }

  koan("Ranges can be specified using 'until' function") {
    val range = 0 until 3
    range.toList should equal(List(__))
  }

  koan("Ranges can be inclusive of the end") {
    val range = Range(3, 7).inclusive
    range.toList should be(List(__))
  }

  koan("Inclusive Ranges can be created using 'to") {
    val range = 3 to 7
    range.toList should be(List(__))
  }

  koan("Range can specify a step value") {
    val range = Range(2, 10, 3)
    range.toList should be(List(__))
  }

  koan("Range can specify step value using 'by'") {
    val range = 2 to 10 by 3
    range.toList should be(List(__))
  }

  koan("Ranges can be tested for membership") {
    val range = 0 until 34 by 2
    range.contains(33) should be(__)
    range.contains(32) should be(__)
    range.contains(34) should be(__)
  }

  koan("Ranges are Iterable Sequences") {
    val range = Range(2, 10)
    range.isInstanceOf[Seq[Int]] should be(__)
    range.isInstanceOf[Iterable[Int]] should be(__)
  }

  koan("Because ranges are Iterable Sequences, all of the methods declared by these traits are available") {
    val range = Range(2, 5).map(_ * 2)
    range.toList should be(List(__))
  }
}
