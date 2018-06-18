package org.scalakoans

import support.KoanSuite
import support.BlankValues.__

class AboutPatternMatching extends KoanSuite  {

  //TODO:Lists
  //TODO:Guards

  koan("Pattern matching returns something") {

    val stuff = "blue"

    val myStuff = stuff match {
      case "red" => println("RED"); 1
      case "blue" => println("BLUE"); 2
      case "green" => println("GREEN"); 3
      case _ => println(stuff); 0
    }

    myStuff should be(__)

  }

  koan("Pattern matching can return complex somethings") {
    val stuff = "blue"

    val myStuff = stuff match {
      case "red" => (255, 0, 0)
      case "green" => (0, 255, 0)
      case "blue" => (0, 0, 255)
      case _ => println(stuff); 0
    }

    myStuff should be(__)

  }

  koan("Pattern matching can match complex expressions") {
    def goldilocks(expr: Any) = expr match {
      case ("porridge", "Papa") => "Papa eating porridge"
      case ("porridge", "Mama") => "Mama eating porridge"
      case ("porridge", "Baby") => "Baby eating porridge"
      case _ => "what?"
    }

    goldilocks(("porridge", "Mama")) should be(__)
  }

  koan("Pattern matching can wildcard parts of expressions") {
    def goldilocks(expr: Any) = expr match {
      case ("porridge", _) => "eating"
      case ("chair", "Mama") => "sitting"
      case ("bed", "Baby") => "sleeping"
      case _ => "what?"
    }

    goldilocks(("porridge", "Papa")) should be(__)
    goldilocks(("chair", "Mama")) should be(__)
  }

  koan("Pattern matching can substitute parts of expressions") {
    def goldilocks(expr: Any) = expr match {
      case ("porridge", bear) => bear + " said someone's been eating my porridge"
      case ("chair", bear) => bear + " said someone's been sitting in my chair"
      case ("bed", bear) => bear + " sais someone's been sleeping in my bed"
      case _ => "what?"
    }

    goldilocks(("porridge", "Papa")) should be(__)
    goldilocks(("chair", "Mama")) should be(__)
  }

  koan("Pattern matching can done on regular expression groups") {
    val EatingRegularExpression = """Eating Alert: bear=([^,]+),\s+source=(.+)""".r
    val SittingRegularExpression = """Sitting Alert: bear=([^,]+),\s+source=(.+)""".r
    val SleepingRegularExpression = """Sleeping Alert: bear=([^,]+),\s+source=(.+)""".r

    def goldilocks(expr: String) = expr match {
      case (EatingRegularExpression(bear, source)) => "%s said someone's been eating my %s".format(bear, source)
      case (SittingRegularExpression(bear, source)) => "%s said someone's been sitting on my %s".format(bear, source)
      case (SleepingRegularExpression(bear, source)) => "%s said someone's been sleeping in my %s".format(bear, source)
      case _ => "what?"
    }

    goldilocks("Eating Alert: bear=Papa, source=porridge") should be(__)
    goldilocks("Sitting Alert: bear=Mama, source=chair") should be(__)
  }

  koan("""A backquote can be used to refer to a stable variable in scope to create a case statement.
         | This prevents what is called \'Variable Shadowing\'""") {
    val foodItem = "porridge"

    def goldilocks(expr: Any) = expr match {
      case (`foodItem`, _) => "eating"
      case ("chair", "Mama") => "sitting"
      case ("bed", "Baby") => "sleeping"
      case _ => "what?"
    }

    goldilocks(("porridge", "Papa")) should be(__)
    goldilocks(("chair", "Mama")) should be(__)
    goldilocks(("porridge", "Cousin")) should be(__)
    goldilocks(("beer", "Cousin")) should be(__)
  }

  koan("A backquote can be used to refer to a method parameter as a stable variable to create a case statement.") {

    def patternEquals(i: Int, j: Int) = j match {
      case `i` => true
      case _ => false
    }
    patternEquals(3, 3) should be(__)
    patternEquals(7, 9) should be(__)
    patternEquals(9, 9) should be(__)
  }
}
