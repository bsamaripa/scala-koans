package org.scalakoans

import support.KoanSuite
import support.BlankValues.__

class AboutTypeSignatures extends KoanSuite  {
  koan("In Java you declare a generic type within a <>, in Scala it is []") {
    val z: List[String] = "Do" :: "Re" :: "Mi" :: "Fa" :: "So" :: "La" :: "Te" :: "Do" :: Nil
    z(3) should be(__)
  }

  koan("Most of the time, Scala will infer the type and [] are optional") {
    val z = "Do" :: "Re" :: "Mi" :: "Fa" :: "So" :: "La" :: "Te" :: "Do" :: Nil //Infers that the list assigned to variable is of type List[String]
  }

  koan("A trait can be declared containing a type, where a concrete implmenter will satisfy the type") {
    trait Randomizer[A] {
      def draw: A
    }

    class IntRandomizer extends Randomizer[Int] {
      def draw = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand = new IntRandomizer
    intRand.draw should be < Int.MaxValue
  }

  koan("Class meta-information can be retrieved by class name by using classOf[className]") {
    classOf[String].getCanonicalName should be(__)
    classOf[String].getSimpleName should be(__)
  }

  koan("Class meta-information can be derived from an object reference using getClass()") {
    val zoom = "zoom"
    zoom.getClass should be(__)
    zoom.getClass.getCanonicalName should be(__)
    zoom.getClass.getSimpleName should be(__)
  }

  koan("isInstanceOf[className] is used to determine the if an object reference is an instance of given class") {
    trait Randomizer[A] {
      def draw: A
    }

    class IntRandomizer extends Randomizer[Int] {
      override def draw = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand = new IntRandomizer
    intRand.draw.isInstanceOf[Int] should be(__)
  }

  koan("asInstanceOf[className] is used to cast one reference to another") {
    trait Randomizer[A] {
      def draw: A
    }

    class IntRandomizer extends Randomizer[Int] {
      override def draw = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand = new IntRandomizer
    val rand = intRand
    val intRand2 = rand.asInstanceOf[IntRandomizer]
  }

  koan("""asInstanceOf[className] will throw a ClassCastException if a class derived from
         |   and the class target aren't from the same inheritance branch""") {
    trait Randomizer[A] {
      def draw: A
    }

    class IntRandomizer extends Randomizer[Int] {
      def draw = {
        import util.Random
        Random.nextInt()
      }
    }

    val intRand = new IntRandomizer

    intercept[ClassCastException] {
      intRand.asInstanceOf[String] //intRand cannot be cast to String
    }
  }

  koan("null.asInstanceOf[className] can be used to generate basic default values") {
    null.asInstanceOf[String] should be(__)
    null.asInstanceOf[Int] should be(__)
    null.asInstanceOf[Short] should be(__)
  }


  /* TODO: This probably needs to move to another category,
     TODO: since this class is supposed to be about type signatures  */
  koan("""Classes can be abstract. Abstract classes can define some methods
         |   concretely or may rely on it\'s subclasses to implement.
         |   If a method has no body and is in
         |   an abstract class, the method is considered abstract.""") {
    abstract class Parent {
      def add(x: Int): Int

      //this is considered abstract
    }

    class Child extends Parent {
      def add(x: Int): Int = x + 3
    }

    new Child().add(3) should be(__)
  }

  /* TODO:  This probably needs to move to another category,
     TODO:  since this class is supposed to be about type signatures  */
  koan("""Same koan as above. Except that concrete methods
         |   can have the modifier override to designate that it overrides a parent class.""") {
    abstract class Parent {
      def add(x: Int): Int

      //this is considered abstract
    }

    class Child extends Parent {
      override def add(x: Int): Int = x + 3

      //explicitly
    }

    new Child().add(3) should be(__)
  }
}

