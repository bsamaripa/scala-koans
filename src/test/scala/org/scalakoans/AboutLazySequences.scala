package org.scalakoans

import support.KoanSuite
import support.BlankValues._


class AboutLazySequences extends KoanSuite  {

  koan("Creating a lazy collection form a strict collection") {
    val strictList = List(10, 20, 30)
    val lazyList = strictList.view
    lazyList.head should be(__)
  }

  koan("Strict collection always processes it elements but lazy collection does on demand") {
    var x = 0
    def inc = {
      x += 1;
      x
    }
    var strictList = List(inc _, inc _, inc _)
    strictList.map(f => f()).head should be(__)
    x should be(__)
    strictList.map(f => f()).head
    x should be(__)

    x = 0
    val lazyList = strictList.view
    lazyList.map(f => f()).head should be(__)
    x should be(__)
    lazyList.map(f => f()).head should be(__)
    x should be(__)
  }

  koan("Lazy collection sometimes avoid processing errors") {
    val lazyList = List(2, -2, 0, 4).view map {
      2 / _
    }
    lazyList.head should be(__)
    lazyList(1) should be(__)
    intercept[ArithmeticException] {
      lazyList(2)
    }
  }

  koan("Lazy collections could also be infinite") {
    val infinite = Stream.from(1)
    infinite.take(4).sum should be(__)
    Stream.continually(1).take(4).sum should be(__)
  }

  koan("Always remember tail of a lazy collection is never computed unless required") {
    def makeLazy(value: Int): Stream[Int] = {
      Stream.cons(value, makeLazy(value + 1))
    }
    val stream = makeLazy(1)
    stream.head should be(__)
    stream.tail.head should be(__)
  }

}
