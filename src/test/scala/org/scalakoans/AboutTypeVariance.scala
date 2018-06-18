package org.scalakoans

import support.KoanSuite
import support.BlankValues.__

class AboutTypeVariance extends KoanSuite {

  class Fruit

  abstract class Citrus extends Fruit

  class Orange extends Citrus

  class Tangelo extends Citrus

  class Apple extends Fruit

  class Banana extends Fruit

  koan("""Using type inference the type that you instantiate it will be the val or var reference type""") {
    class MyContainer[A](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def get = item

      def set(a: A): Unit = {
        item = a
      }

      def contents = manifest.runtimeClass.getSimpleName
    }

    val fruitBasket = new MyContainer(new Orange())
    fruitBasket.contents should be(__)
  }


  koan("""You can explicitly declare the type variable of the object during instantiation""") {
    class MyContainer[A](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def get = item

      def set(a: A): Unit = {
        item = a
      }

      def contents = manifest.runtimeClass.getSimpleName
    }

    val fruitBasket = new MyContainer[Fruit](new Orange())
    fruitBasket.contents should be(__)
  }

  koan("You can coerece your object to a type.") {
    class MyContainer[A](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def get = item

      def set(a: A): Unit = {
        item = a
      }

      def contents = manifest.runtimeClass.getSimpleName
    }

    val fruitBasket: MyContainer[Fruit] = new MyContainer(new Orange())
    fruitBasket.contents should be(__)
  }

// That one probably blew your mind. Now if you assign a type to the instantiation,
// that's different to the variable type, you'll have problems.  You may want to take time after this
// o compare this koan with the previous koan to compare and contrast. """) {
  

  koan("variable type must match assigned type") {
    class MyContainer[A](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def get = item

      def set(a: A): Unit = {
        item = a
      }

      def contents = manifest.runtimeClass.getSimpleName
    }

    // Uncomment the following line
    // val fruitBasket:MyContainer[Fruit] = new MyContainer[Orange](new Orange())
  }

// So, if you want to set a Fruit basket to an orange basket so how do we fix that? You make it covariant using +.
// This will allow you to set the your container to a either a variable with the same type or parent type.
// In other words, you can assign MyContainer[Fruit] or MyContainer[Citrus]."""
  koan("covariance lets you specify the container of that type or parent type") {

    class MyContainer[+A](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] val item = a

      def get = item

      def contents = manifest.runtimeClass.getSimpleName
    }

    val fruitBasket: MyContainer[Fruit] = new MyContainer[Orange](new Orange())
    fruitBasket.contents should be(__)
  }

// The problem with covariance is that you can't mutate, set, or change the object since
// it has to guarantee that what you put in has to be that type.  In other words the reference is a fruit basket,
// but we still have to make sure that no other fruit can be placed in our orange basket"""

  koan("mutating an object is not allowed with covariance") {

    class MyContainer[+A](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] val item = a

      def get = item

      def contents = manifest.runtimeClass.getSimpleName
    }

    val fruitBasket: MyContainer[Fruit] = new MyContainer[Orange](new Orange())
    fruitBasket.contents should be(__)

    class NavelOrange extends Orange //Creating a subtype to prove a point
    //    val navelOrangeBasket: MyContainer[NavelOrange] = new MyContainer[Orange](new Orange()) //Bad!
    //    val tangeloBasket: MyContainer[Tangelo] = new MyContainer[Orange](new Orange()) //Bad!
  }

// Declaring - indicates contravariance variance.  
// Using - you can apply any container with a certain type to a container with a superclass of that type.  
// This is reverse to covariant.  In our example, we can set a citrus basket to
// an orange or tangelo basket. Since an orange or tangelo basket is a citrus basket

  koan("contravariance is the opposite of covariance") {

    class MyContainer[-A](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def set(a: A): Unit = {
        item = a
      }

      def contents = manifest.runtimeClass.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)
    citrusBasket.contents should be(__)
    val orangeBasket: MyContainer[Orange] = new MyContainer[Citrus](new Tangelo)
    orangeBasket.contents should be(__)
    val tangeloBasket: MyContainer[Tangelo] = new MyContainer[Citrus](new Orange)
    tangeloBasket.contents should be(__)

    val orangeBasketReally: MyContainer[Orange] = tangeloBasket.asInstanceOf[MyContainer[Orange]]
    orangeBasketReally.contents should be(__)
    orangeBasketReally.set(new Orange())
  }

// Declaring contravariance variance with - also means that the container cannot be accessed with a getter or
// or some other accessor, since that would cause type inconsistency.  In our example, you can put an orange
// or a tangelo into a citrus basket. Problem is, if you have a reference to an orange basket,
// and if you believe that you have an orange basket then you shouldn't expect to get a
// tangelo out of it.
  koan("A reference to a parent type means you cannot anticipate getting a more specific type") {

    class MyContainer[-A](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def set(a: A): Unit = {
        item = a
      }

      def contents = manifest.runtimeClass.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)
    citrusBasket.contents should be(__)
    val orangeBasket: MyContainer[Orange] = new MyContainer[Citrus](new Tangelo)
    orangeBasket.contents should be(__)
    val tangeloBasket: MyContainer[Tangelo] = new MyContainer[Citrus](new Orange)
    tangeloBasket.contents should be(__)
  }

// Declaring neither -/+, indicates invariance variance.  You cannot use a superclass
// variable reference (\"contravariant\" position) or a subclass variable reference (\"covariant\" position)
// of that type.  In our example, this means that if you create a citrus basket you can only reference that
// that citrus basket with a citrus variable only.

  koan("invariance means you need to specify the type exactly") {

    class MyContainer[A](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def set(a: A): Unit = {
        item = a
      }

      def get = item

      def contents = manifest.runtimeClass.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)
    citrusBasket.contents should be(__)
  }


  koan("""Declaring a type as invariant also means that you can both mutate and access elements from an object of generic type""") {

    class MyContainer[A](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
      private[this] var item = a

      def set(a: A): Unit = {
        item = a
      }

      def get = item

      def contents = manifest.runtimeClass.getSimpleName
    }

    val citrusBasket: MyContainer[Citrus] = new MyContainer[Citrus](new Orange)

    citrusBasket.set(new Orange)
    citrusBasket.contents should be(__)

    citrusBasket.set(new Tangelo)
    citrusBasket.contents should be(__)
  }
}
