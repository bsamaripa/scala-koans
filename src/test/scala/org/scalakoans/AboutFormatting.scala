package org.scalakoans


import support.KoanSuite
import support.BlankValues.__

class AboutFormatting extends KoanSuite  {

  koan("""Character Literals can either be an a single character,
          |   an escape sequence, a Unicode octal up to 255 or a hexadecimal""") {
    val a = 'a'
    val b = 'B'
    val c = '\u0061' //unicode for a
    val d = '\"'
    val e = '\\'

    //format(a) is a string format, meaning the "%c".format(x)
    //will return the string representation of the char.

    "%c".format(a) should be(__)
    "%c".format(b) should be(__)
    "%c".format(c) should be(__)
    "%c".format(d) should be(__)
    "%c".format(e) should be(__)
  }

}