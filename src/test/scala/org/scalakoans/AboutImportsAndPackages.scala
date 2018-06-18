package org.scalakoans


import support.KoanSuite
import support.BlankValues.__

class AboutImportsAndPackages extends KoanSuite  {
  koan("An import can be placed in a method, hint this koan is a method") {
    import scala.collection.mutable.ArrayBuffer
    val arrayBuffer = ArrayBuffer.range(2, 10)
    arrayBuffer(0) should be(__)
    arrayBuffer(1) should be(__)
  }
}

class Artist(val firstName: String, val lastName: String)

package subpackage {

class AboutImportsAndPackagesInSubpackages extends KoanSuite  {
  koan("A package can be included in a file with an established established package, " +
          "and can encapsulate it's contents with a {} block") {
    val luther = new Artist("Luther", "Vandross")
    luther.lastName should be(__)
  }
}

}

package album {

class Album(val name: String, val year: Short, val artist: Artist)

}

package media {


class AboutReferencingAbsolutePackages extends KoanSuite  {

  import album.Album

  // <<< Note the import style
  koan("A import can be done based from absolute package heirarchy") {
    val stLouisBlues = new Album("St. Louis Blues", 1940, new Artist("Louie", "Armstrong"))
    stLouisBlues.getClass.getCanonicalName should be(__)
  }
}

class AboutReferencingAbsoluteRootPackages extends KoanSuite  {

  import _root_.org.scalakoans.album.Album

  // <<< Note the import style
  koan("A import can be done based from absolute root package heirarchy using _root_") {
    val stLouisBlues = new Album("St. Louis Blues", 1940, new Artist("Louie", "Armstrong"))
    stLouisBlues.getClass.getCanonicalName should be(__)
  }
}

class AboutReferencingRelativePackages extends KoanSuite  {

  import album.Album

  // <<< Note the import style
  koan("A import can be done based from relative packaging") {
    val stLouisBlues = new Album("St. Louis Blues", 1940, new Artist("Louie", "Armstrong"))
    stLouisBlues.getClass.getCanonicalName should be(__)
  }
}

}

package music_additions {

class Genre(val name: String)

class Producer(val firstName: String, lastName: String)

class Distributor(val name: String)

}

class AboutImportingTechniques extends KoanSuite  {
  koan("To import all classes of a package, use _ as a wildcard") {
    import music_additions._
    val genre = new Genre("Jazz")
    val producer = new Producer("Joe", "Oliver")
    val distributor = new Distributor("RYKO Classic Music")

    genre.name should be(__)
    producer.firstName should be(__)
    distributor.name should be(__)
  }

  koan("To import all classes of a package, use can also use {_} as a wildcard") {
    import music_additions.{_}
    val genre = new Genre("Jazz")
    val producer = new Producer("Joe", "Oliver")
    val distributor = new Distributor("RYKO Classic Music")

    genre.name should be(__)
    producer.firstName should be(__)
    distributor.name should be(__)
  }

  koan("To import a select group of classes of a package, use {className1, className}") {
    import music_additions.{Genre, Distributor}
    val genre = new Genre("Jazz")
    val distributor = new Distributor("RYKO Classic Music")

    genre.name should be(__)
    distributor.name should be(__)
  }

  koan("You can rename a class by using => and create an alias") {
    import music_additions.{Genre => MusicType, Distributor}
    val musicType = new MusicType("Jazz")
    val distributor = new Distributor("RYKO Classic Music")

    musicType.name should be(__)
    distributor.name should be(__)
  }

  koan("You can rename a class by using =>, and also import all other classes in a package keeping their name") {
    import music_additions.{Genre => MusicType, _}
    val musicType = new MusicType("Jazz")
    val producer = new Producer("Joe", "Oliver")
    val distributor = new Distributor("RYKO Classic Music")

    musicType.name should be(__)
    producer.firstName should be(__)
    distributor.name should be(__)
  }

  koan("You can also refuse classes from being imported using => _") {
    import music_additions.{Producer => _, _}
    val musicType = new Genre("Jazz")
    val distributor = new Distributor("RYKO Classic Music")

    musicType.name should be(__)
    distributor.name should be(__)
  }

  koan("You can just import the package themselves,so you can give it a verbose identity") {
    import scala.collection.mutable
    val arrayBuffer = mutable.ArrayBuffer.range(2, 10) //sounds better: A Mutable ArrayBuffer
    arrayBuffer(0) should be(__)
    arrayBuffer(1) should be(__)
  }

  koan("You can just import the package themselves, and give it an alias!") {
    import scala.collection.{mutable => changeable}
    val arrayBuffer = changeable.ArrayBuffer.range(2, 10)
    arrayBuffer(0) should be(__)
    arrayBuffer(1) should be(__)
  }
}

