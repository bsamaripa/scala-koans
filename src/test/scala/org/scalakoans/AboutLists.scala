package org.scalakoans

import support.KoanSuite
import support.BlankValues.__

class AboutLists extends KoanSuite  {

  koan("Nil lists are identical, even of different types") {
    val a: List[String] = Nil
    val b: List[Int] = Nil

    (a == Nil) should be(__)
    (b == Nil) should be(__)
    (a == b) should be(__)
    
  }

  koan("Lists are easily created") {
    val a = List(1, 2, 3)
    a should equal(__)
  }

  koan("Eq tests identity (same object)") {
    val a = List(1, 2, 3)
    val b = List(1, 2, 3)


    (a eq b) should be(__)
  }

  koan("Lists can be accessed via head and tail") {
    val a = List(1, 2, 3)
    a.head should equal(__)
    a.tail should equal(__)
  }

  koan("Lists can be accessed at random") {
    val a = List(1, 3, 5, 7, 9)
    a(0) should equal(__)
    a(1) should equal(__)
    a(4) should equal(__)
    intercept[IndexOutOfBoundsException] {
      println(a(5))
    }
  }

  koan("Lists are immutable") {
    val a = List(1, 3, 5, 7, 9)
    val b = a.filterNot(v => v == 5) // remove where value is 5

    a should equal(__)
    b should equal(__)
  }

  koan("Lists have many useful methods") {
    val a = List(1, 3, 5, 7, 9)

    // get the length of the list
    a.length should equal(__)

    // reverse the list
    a.reverse should equal(__)

    // convert the list to a string representation
    a.toString should equal(__)

    // map a function to double the numbers over the list
    a.map {
      v => v * 2
    } should equal(__)

    // filter out any values divisible by 3 in the list
    a.filter {
      v => v % 3 == 0
    } should equal(__)
  }

  koan("Functions over lists can use _ as shorthand") {
    val a = List(1, 2, 3)
    a.map {
      _ * 2
    } should equal(__)
    a.filter {
      _ % 2 == 0
    } should equal(__)
  }

  koan("Functions over lists can use () instead of {}") {
    val a = List(1, 2, 3)
    a.map(_ * 2) should equal(__)
    a.filter(_ % 2 != 0) should equal(__)
  }

  koan("Lists can be 'reduced' with a mathematical operation") {
    val a = List(1, 3, 5, 7)
    // note the two _s below indicate the first and second args respectively
    a.reduceLeft(_ + _) should equal(__)
    a.reduceLeft(_ * _) should equal(__)
  }

  koan("Foldleft is like reduce, but with an explicit starting value") {
    val a = List(1, 3, 5, 7)
    // foldLeft uses a form called currying that we will explore later
    a.foldLeft(0)(_ + _) should equal(__)
    a.foldLeft(10)(_ + _) should equal(__)
    a.foldLeft(1)(_ * _) should equal(__)
    a.foldLeft(0)(_ * _) should equal(__)
  }

  koan("You can create a list from a range") {
    val a = (1 to 5).toList
    a should be(__)
    val b = (1 until 5).toList
    b should be(__)
  }

  koan("Lists reuse their tails") {
    val d = Nil
    val c = 3 :: d
    val b = 2 :: c
    val a = 1 :: b

    a should be(__)
    a.tail should be(__)
    b.tail should be(__)
    c.tail should be(__)
  }

  //For more about what lists can do, visit AboutTraversables.scala koans
}
