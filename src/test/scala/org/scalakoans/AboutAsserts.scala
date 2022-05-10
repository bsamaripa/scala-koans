package org.scalakoans

import support.KoanSuite
import support.BlankValues._

// meditate on AboutAsserts to see how the Scala Koans work
class AboutAsserts extends KoanSuite {

  koan("asserts can take a boolean argument") {
    assert(true) // should be true
  }

  koan("asserts can include a message") {
    assert(true, "This should be true")
  }

  koan("true and false values can be compared with should matchers") {
    true should be(true) // should be true
  }

  koan("booleans in asserts can test equality") {
    val v1 = 4
    val v2 = 4
    assert(v1 === v2)
  }

  koan("sometimes we expect you to fill in the values") {
    assert(2 == 1 + 1)
  }
}
