package org.scalakoans

import support.KoanSuite
import support.BlankValues._

class AboutAbstractTypes extends KoanSuite {

  koan("A type can be declared anywhere") {
    type G = Int
    val x: G = 3
    val y: G = 5
    (x + y) should be(8)
  }

  koan("""Abstract types are types that are declared in an abstract class, and
         | since it is in abstract class they don't need to be defined until
         | they are extended.""") {

    abstract class Generator {
      type F

      def generate: F
    }

    class StringGenerator extends Generator {
      type F = String

      def generate = "Scooters are fun"
    }

    new StringGenerator().generate should be("Scooters are fun")
  }

  koan("Abstract types can also be included in a trait") {
    trait Counter {
      type C
      def count(c: C): Int
    }

    class CharacterCounter extends Counter {
      type C = String
      override def count(c: String) = c.size
    }

    new CharacterCounter().count("Boolean") should be(7)
  }

  koan("""Abstract types are also by default public, the can be locked down with an
         |  access modifier""") {
    trait Counter {
      protected type C
      def count(c: C): Int
    }

    class CharacterCounter extends Counter {
      protected type C = String
      override def count(c: String) = c.size
    }

    new CharacterCounter().count("Awesome") should be(7)
  }


  koan("""Perhaps the best reason for abstract types is the
         |  ability to refine types so it can make sense. The example often used
         |  is creating an Animal class, and refining the
         |  type of Food that can be eaten. This koan will also show use of a type bound
         |  as an abstract type""") {

    trait Food
    class DogFood extends Food {
      override def toString = "Dog Food"
    }
    class Hay extends Food {
      override def toString = "Hay"
    }

    trait Animal {
      type C <: Food //Animal has to eat some sort of food, depends on animal
      def feedMe(c:C): Unit
    }

    class Cow {
      type C = Hay //A good choice
      def feedMe(c:C) = "Nom Nom, I am eating " + c
    }

    class Dog {
      type C = DogFood //Again, a good choice
      def feedMe(c:C) = "Nom Nom, I am eating " + c
    }

    //new Cow().feedMe(new DogFood)  this wont compile, because it's not right
    new Cow().feedMe(new Hay()) should be ("Nom Nom, I am eating Hay")
    new Dog().feedMe(new DogFood()) should be ("Nom Nom, I am eating Dog Food")
  }

  koan("""Abstract Types can be any type even a type with type parameter""") {
    trait Counter {
      type T <: Traversable[_] //The _ means it is existential, in this case
      //I don't care what kind of Traversable it is.
      def count(t:T) = t.size  //I know it's a Traversable but I'll define later which one.
    }

    class IntListCounter extends Counter {
      type T = List[Int]
      override def count(t:T) = t.sum //overriding implementation; Sum is
      //only available with Numeric types
    }

    new IntListCounter().count(List(1,2,3,4)) should be (10)
  }
}