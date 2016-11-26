package com.highbar.cards

import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class CardRankSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {

  describe("Parsing a CardRank from a String") {
    it("Succeeds when the input String represents a valid value") {
      forAll(genCardRankString) {
        (s: String) => {
          val rank = CardRank.from(s)
          CardRank.values.contains(rank) shouldEqual true
        }
      }
    }
  }

  describe("Converting a CardRank to a String") {
    it("'A'  represents Ace") {
      CardRank.Ace.toString shouldEqual "A"
    }

    it("'2'  represents Two") {
      CardRank.Two.toString shouldEqual "2"
    }

    it("'3'  represents Three") {
      CardRank.Three.toString shouldEqual "3"
    }

    it("'4'  represents Four") {
      CardRank.Four.toString shouldEqual "4"
    }

    it("'5'  represents Five") {
      CardRank.Five.toString shouldEqual "5"
    }

    it("'6'  represents Six") {
      CardRank.Six.toString shouldEqual "6"
    }

    it("'7'  represents Seven") {
      CardRank.Seven.toString shouldEqual "7"
    }

    it("'8'  represents Eight") {
      CardRank.Eight.toString shouldEqual "8"
    }

    it("'9'  represents Nine") {
      CardRank.Nine.toString shouldEqual "9"
    }

    it("'T' represents Ten") {
      CardRank.Ten.toString shouldEqual "T"
    }

    it("'J'  represents Jack") {
      CardRank.Jack.toString shouldEqual "J"
    }

    it("'Q'  represents Queen") {
      CardRank.Queen.toString shouldEqual "Q"
    }

    it("'K'  represents King") {
      CardRank.King.toString shouldEqual "K"
    }
  }
}