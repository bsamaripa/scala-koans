package org.scalakoans

import support.KoanSuite
import support.BlankValues.__

class AboutValAndVar extends KoanSuite  {
  koan("vars may be reassigned") {
    var a = 5
    a should be(__)

    a = 7
    a should be(__)
  }

  koan("vals may not be reassigned") {
    val a = 5
    a should be(__)

    // What happens if you uncomment these lines?
    // a = 7
    // a should be (5)
  }

  koan("vals or vars can have the same name as a keyword as long as it's surrounded by `") {
    val `class` = "MyClassName"
    `class` should be(__)
  }


}
