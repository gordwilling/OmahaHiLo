package com.highbar.cards

import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class CardParserSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {

  describe("CardParser") {
    it("Parses a string representing a rank and a suit") {
      forAll(genCard) {
        (in: Card) => {
          val out = CardParser.parse(in.toString)
          out shouldEqual in
        }
      }
    }
  }
}