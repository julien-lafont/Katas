package fr.studiodev.roman

import org.specs2.mutable._

class AppSpec extends Specification {

  "Roman converter" should {
    "transform 0 to  " in {
      Roman(0) mustEqual ""
    }
    "transform 1 to I" in {
      Roman(1) mustEqual "I"
    }
    "transform 2 to II" in {
      Roman(2) mustEqual "II"
    }
    "transform 5 to V" in {
      Roman(5) mustEqual "V"
    }
    "transform 6 to VI" in {
      Roman(6) mustEqual "VI"
    }
    "transform 4 to IV" in {
      Roman(4) mustEqual("IV")
    }
    "transform 9 to IX" in {
      Roman(9) mustEqual("IX")
    }
    "transform 4444 to MMMMCDXLIV" in {
      Roman(4444) mustEqual("MMMMCDXLIV")
    }
    "transform 4999 to MMMMCMXCIX" in {
      Roman(4999) mustEqual("MMMMCMXCIX")
    }

  }
}
