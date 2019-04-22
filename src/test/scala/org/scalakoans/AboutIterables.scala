package org.scalakoans

import support.KoanSuite
import support.BlankValues.__

class AboutIterables extends KoanSuite  {
  koan("""Iterable is a trait that has the ability to return an iterator of itself.
           | Some known iterators are Sets, Lists, Vectors, Stacks, and Streams. Iterator has two
           | important methods:  `hasNext`, which answers whether the iterator has another element
           | available. `next` which will return the next element in the iterator.""") {
    val list = List(3, 5, 9, 11, 15, 19, 21)
    val it = list.iterator
    if (it.hasNext) {
      it.next should be(__)
    }
  }

  koan("""`grouped` will return an fixed sized Iterable chucks of an Iterable""") {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    val it = list grouped 3
    it.next() should be(__)
    it.next() should be(__)
    it.next() should be(__)
  }

  koan("""`sliding` will return an Iterable that shows a sliding window of an Iterable.""") {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    val it = list sliding 3
    it.next() should be(__)
    it.next() should be(__)
    it.next() should be(__)
  }

  koan("""`sliding` can take the size of the window as well the size of the step during each
          | iteration""") {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    val it = list sliding (3, 3)
    it.next() should be(__)
    it.next() should be(__)
    it.next() should be(__)
  }

  koan("""`takeRight` is the opposite of 'take' in Traversable.  It retrieves the last elements
          | of an Iterable. """) {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    (list takeRight 3) should be(__)
  }

  koan("""`dropRight` will drop the number of elements from the right. """) {
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)
    (list dropRight 3) should be(__)
  }

  koan("""`zip` will stitch two iterables into an iterable of pairs of corresponding elements
          |  from both iterables. e.g. Iterable(x1, x2, x3) zip Iterable(y1, y2, y3) will
          |  return ((x1,y1), (x2, y2), (x3, y3))""") {
    val xs = List(3, 5, 9)
    val ys = List("Bob", "Ann", "Stella")
    (xs zip ys) should be(__)
  }

  koan("""if two Iterables aren't the same size, then `zip` will only zip what can only be paired.
          |  e.g. Iterable(x1, x2, x3) zip Iterable(y1, y2) will
          |  return ((x1,y1), (x2, y2))""") {
    val xs = List(3, 5, 9)
    val ys = List("Bob", "Ann")
    (xs zip ys) should be(__)
  }

  koan("""if two Iterables aren't the same size, then `zipAll` can provide fillers for what it couldn't
          |  find a complement for. e.g. Iterable(x1, x2, x3) zipAll (Iterable(y1, y2), x, y) will
          |  return ((x1,y1), (x2, y2, y))""") {
    val xs = List(3, 5, 9)
    val ys = List("Bob", "Ann")
    (xs zipAll (ys, -1, "?")) should be(__)
  }

  koan("""`zipWithIndex` will zip an Iterable with it's integer index""") {
    val xs = List("Manny", "Moe", "Jack")
    xs.zipWithIndex should be(__)
  }

  koan("""`sameElements` will return true if the two iterables have the same number of elements""") {
    val xs = List("Manny", "Moe", "Jack")
    val ys = List("Manny", "Moe", "Jack")
    (xs sameElements ys) should be (__)

    val xs1 = Set(3,2,1,4,5,6,7)
    val ys1 = Set(7,2,1,4,5,6,3)
    (xs1 sameElements ys1) should be (__)
  }
}