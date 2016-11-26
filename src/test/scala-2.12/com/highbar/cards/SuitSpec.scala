package com.highbar.cards

import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class SuitSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {

  describe("Parsing a Suit from a String") {
    it("Succeeds when the input String represents a valid value") {
      forAll(genSuitString) {
        (s: String) => {
          val suit = Suit.from(s)
          Suit.values.contains(suit) shouldEqual true
        }
      }
    }
  }

  describe("Converting a Suit to a String") {
    it("'c' represents Clubs") {
      Suit.Clubs.toString shouldEqual "c"
    }

    it("'d' represents Diamonds") {
      Suit.Diamonds.toString shouldEqual "d"
    }

    it("'h' represents Hearts") {
      Suit.Hearts.toString shouldEqual "h"
    }

    it("'s' represents Spades") {
      Suit.Spades.toString shouldEqual "s"
    }
  }
}