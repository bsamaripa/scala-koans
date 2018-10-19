package org.scalakoans


import support.KoanSuite
import support.BlankValues.__

/**
 * Created by Daniel Hinojosa
 * User: Daniel Hinojosa
 * Date: 3/6/11
 * Time: 9:50 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */

class Monkey


class AboutManifests extends KoanSuite  {
  koan("""The JVM erases generic type information after compiling.
         | Manifests can be used to determine a type at runtime by using an implicit manifest argument.""") {
    def inspect[T](l: List[T])(implicit manifest: scala.reflect.Manifest[T]) = manifest.toString
    val list = 1 :: 2 :: 3 :: 4 :: 5 :: Nil
    inspect(list) should be(__)
  }

  koan("""Manifests can be attached to classes. Manifests have other meta-information about
          |  the type erased""") {
    class Barrel[T](implicit m: scala.reflect.Manifest[T]) {
      def +(t: T) = "1 %s has been added".format(m.runtimeClass.getSimpleName) //Simple-name of the class erased
    }
    val monkeyBarrel = new Barrel[Monkey]
    (monkeyBarrel + new Monkey) should be(__)
  }
}