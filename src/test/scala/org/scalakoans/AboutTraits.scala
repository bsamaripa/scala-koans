package org.scalakoans


import support.KoanSuite
import support.BlankValues.__

class AboutTraits extends KoanSuite  {
  koan("A class can use the \'extends\' keyword to mixin a trait if it is the only relationship the class inherits") {
    case class Event(name: String, source: Any)

    trait EventListener {
      def listen(event: Event): Unit
    }

    class MyListener extends EventListener {
      def listen(event: Event): Unit = {
        event match {
          case Event("Moose Stampede", _) => println("An unfortunate moose stampede occured")
          case _ => println("Nothing of importance occured")
        }
      }
    }

    val evt = Event("Moose Stampede", this)
    val myListener = new MyListener()
    myListener.listen(evt)
  }

  koan("""A class can only \'extends\' from _one_ class or trait, any subsequent extension
          |  should use the keyword \'with\'""") {

    case class Event(name: String, source: Any)

    trait EventListener {
      def listen(event: Event): Unit
    }

    class OurListener

    class MyListener extends OurListener with EventListener {
      def listen(event: Event): Unit = {
        event match {
          case Event("Woodchuck Stampede", _) => println("An unfortunate woodchuck stampede occured")
          case _ => println("Nothing of importance occured")
        }
      }
    }

    val evt = Event("Woodchuck Stampede", this)
    val myListener = new MyListener()
    myListener.listen(evt)
  }


  koan("""Protip: For many; using \'extends\' gives the impression of a \'is-a\' relationship and many don't want to
          |   extend from a trait that doesn't follow that relationship. If a class
          |   has no need to extend from a parent class but you want to mixin one or more traits, and would rather use
          |   \'with\' with all traits instead of one trait with extends and the others with \'with\' you can
          |   \'extends\' from an Object so that all traits can be used with \'with\'""") {

    case class Event(name: String, source: Any)

    trait EventListener {
      def listen(event: Event): Unit
    }

    trait MouseListener {
      def listen(mouseEvent: Event): Unit
    }

    class MyListener extends Object with MouseListener with EventListener {
      //Nice line here
      def listen(event: Event): Unit =  {
        event match {
          case Event("Woodchuck Stampede", _) => println("An unfortunate woodchuck stampede occured")
          case _ => println("Nothing of importance occured")
        }
      }
    }

    val evt = Event("Woodchuck Stampede", this)
    val myListener = new MyListener()
    myListener.listen(evt)
  }

  koan("Traits are polymorphic. Any type can be referred to by another type if related by extension or trait") {

    case class Event(name: String, source: Any)

    trait EventListener {
      def listen(event: Event): Unit
    }

    class MyListener extends EventListener {
      def listen(event: Event): Unit = {
        event match {
          case Event("Moose Stampede", _) => println("An unfortunate moose stampede occured")
          case _ => println("Nothing of importance occured")
        }
      }
    }

    val evt = Event("Moose Stampede", this)
    val myListener = new MyListener()

    myListener.isInstanceOf[MyListener] should be(__)
    myListener.isInstanceOf[EventListener] should be(__)
    myListener.isInstanceOf[Any] should be(__)
    myListener.isInstanceOf[AnyRef] should be(__)
  }

  koan("""Traits can have concrete method implementations that can be mixed
          |   into concrete classes with it's own state""") {

    trait ListLog {
      var logCache = List[String]()

      def log(value: String): Unit = {
        logCache = logCache :+ value
      }
    }

    class Welder extends ListLog {
      def weld(): Unit = {
        log("welding pipe")
      }
    }

    class Baker extends ListLog {
      def bake(): Unit = {
        log("baking cake")
      }
    }

    val welder = new Welder
    welder.weld()


    val baker = new Baker
    baker.bake()

    welder.logCache.size should be(__)
    baker.logCache.size should be(__)

    welder.logCache.head should be(__)
    baker.logCache.head should be(__)
  }

  koan("""Traits can have concrete implementations, but can still be overridden by classes that
          |  mixin the trait""") {

    trait ListLog {
      var logCache = List[String]()

      def log(value: String): Unit = {
        logCache = logCache :+ value
      }
    }

    class Welder extends Object with ListLog {
      def weld(): Unit = {
        log("welding pipe")
      }

      override def log(value: String): Unit = {
        super.log("Weld Log : " + value)
      }
    }

    class Baker extends ListLog {
      def bake(): Unit = {
        log("baking cake")
      }

      override def log(value: String): Unit = {
        super.log("Bake Log : " + value)
      }
    }

    val welder = new Welder
    welder.weld() //Parenthesis are used to make calls on methods with side-effects

    val baker = new Baker
    baker.bake() //Parenthesis are used to make calls on methods with side-effects

    welder.logCache.size should be(__)
    baker.logCache.size should be(__)

    welder.logCache.head should be(__)
    baker.logCache.head should be(__)
  }

  koan("""Traits can be stacked with other traits to create customizable decorative abstractions for a class""") {

    trait Log {
      //A log
      def log(value: String): Unit
    }

    trait TimedLog extends Log {
      abstract override def log(value: String): Unit = {
        super.log("January, 12, 2025 : " + value)
      }
    }

    trait UserLog extends Log {
      abstract override def log(value: String): Unit = {
        super.log("Root said: " + value)
      }
    }

    trait ListLog extends Log {
      var logCache = List[String]()

      override def log(value: String): Unit = {
        logCache = logCache :+ value
        println(value)
      }
    }

    class Baker extends Object with ListLog with TimedLog with UserLog {
      def bake() : Unit ={
        log("baking cake")
      }
    }

    val baker = new Baker
    baker.bake()
    baker.logCache.head should be(__)

    class Welder extends Object with ListLog with TimedLog {
      // I don't want UserLog with a Welder
      def weld(): Unit = {
        log("welding pipe")
      }
    }

    val welder = new Welder
    welder.weld()
    welder.logCache.head should be(__)
  }

  koan("""Traits can be stacked with other traits to create customizable decorative
          |   abstractions for a class that weren't written in originally!""") {

    trait Log {
      //A log
      def log(value: String): Unit
    }

    trait TimedLog extends Log {
      abstract override def log(value: String): Unit = {
        super.log("January, 12, 2025 : " + value)
      }
    }

    trait UserLog extends Log {
      abstract override def log(value: String): Unit = {
        super.log("Root said: " + value)
      }
    }

    trait ListLog extends Log {
      var logCache = List[String]()

      override def log(value: String): Unit = {
        logCache = logCache :+ value
        println(value)
      }
    }

    case class Baker() //define a class
    val baker = new Baker with ListLog with UserLog with TimedLog //Pick and choose what traits to stack!
    baker.log("baked cake")
    baker.logCache.head should be(__) //Whoa!
  }


  koan("Traits are instantiated before a classes instantition") {
    var sb = List[String]()

    trait T1 {
      sb = sb :+ "In T1: x=%s".format(x)
      val x = 1
      sb = sb :+ "In T1: x=%s".format(x)
    }

    class C1 extends T1 {
      sb = sb :+ "In C1: y=%s".format(y)
      val y = 2
      sb = sb :+ "In C1: y=%s".format(y)
    }

    sb = sb :+ "Creating C1"
    new C1
    sb = sb :+ "Created C1"

    sb.mkString(";") should be(__)
  }


  koan("Traits are instantiated before a classes instantition from left to right") {
    var sb = List[String]()

    trait T1 {
      sb = sb :+ "In T1: x=%s".format(x)
      val x = 1
      sb = sb :+ "In T1: x=%s".format(x)
    }

    trait T2 {
      sb = sb :+ "In T2: z=%s".format(z)
      val z = 1
      sb = sb :+ "In T2: z=%s".format(z)
    }

    class C1 extends T1 with T2 {
      sb = sb :+ "In C1: y=%s".format(y)
      val y = 2
      sb = sb :+ "In C1: y=%s".format(y)
    }

    sb = sb :+ "Creating C1"
    new C1
    sb = sb :+ "Created C1"

    sb.mkString(";") should be(
      "Creating C1;In T1: x=0;In T1: x=1;In T2: z=0;In T2: z=1;In C1: y=0;In C1: y=2;Created C1")
  }

  koan("""Instantiations are tracked and will not allow a duplicate instantiation.
         |   Note T1 extends T2, and C1 also extends T2, but T2 is only instantiated twice.""") {

    var sb = List[String]()

    trait T1 extends T2 {
      sb = sb :+ "In T1: x=%s".format(x)
      val x = 1
      sb = sb :+ "In T1: x=%s".format(x)
    }

    trait T2 {
      sb = sb :+ "In T2: z=%s".format(z)
      val z = 1
      sb = sb :+ "In T2: z=%s".format(z)
    }

    class C1 extends T1 with T2 {
      sb = sb :+ "In C1: y=%s".format(y)
      val y = 2
      sb = sb :+ "In C1: y=%s".format(y)
    }

    sb = sb :+ "Creating C1"
    new C1
    sb = sb :+ "Created C1"

    sb.mkString(";") should
            be(__)
  }


  koan("The diamond of death (http://en.wikipedia.org/wiki/Diamond_problem) is avoided since " +
          "instantiations are tracked and will not allow multiple instantiations") {

    var sb = List[String]()

    trait T1 {
      sb = sb :+ "In T1: x=%s".format(x)
      val x = 1
      sb = sb :+ "In T1: x=%s".format(x)
    }

    trait T2 extends T1 {
      sb = sb :+ "In T2: z=%s".format(z)
      val z = 2
      sb = sb :+ "In T2: z=%s".format(z)
    }

    trait T3 extends T1 {
      sb = sb :+ "In T3: w=%s".format(w)
      val w = 3
      sb = sb :+ "In T3: w=%s".format(w)
    }

    class C1 extends T2 with T3 {
      sb = sb :+ "In C1: y=%s".format(y)
      val y = 4
      sb = sb :+ "In C1: y=%s".format(y)
    }

    sb = sb :+ "Creating C1"
    new C1
    sb = sb :+ "Created C1"

    sb.mkString(";") should be(__)
  }
}
