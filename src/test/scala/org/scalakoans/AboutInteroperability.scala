package org.scalakoans

import support.KoanSuite
import support.BlankValues.__

class AboutInteroperability extends KoanSuite  {
  koan("""You can interop with a java class and it's use of collections by importing
          |   scala.collection.JavaConversions and letting scala implicitly convert from a Scala collection type
          |   into a Java collection type.  See AboutImplicits Koan Suite for more details and see src/test/java for the
          |   SomeJavaClass file. This koan
          |   converts a scala List of String to java List of raw type.""") {

    import scala.collection.JavaConverters._
    val d = new SomeJavaClass
    val e = List("one", "two", "three")
    d.findSizeOfRawType(e.asJava) should be(__)
  }

  class Boat(size: Int, manufacturer: String)

  koan("""This koan converts a scala List of Boat (our own class) to java List of unknown <?> type.""") {
    import scala.collection.JavaConverters._
    val d = new SomeJavaClass
    val e = List(new Boat(33, "Skyway"), new Boat(35, "New Boat"))
    d.findSizeOfUnknownType(e.asJava) should be(__)
  }
}