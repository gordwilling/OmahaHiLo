package com.highbar.cards

import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class CardSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {

  describe("A Card") {
    it("Is stringified by concatenating its rank and suit") {
      forAll(genCard) {
        (card: Card) => {
          val suit = card.suit
          val rank = card.rank
          card.toString shouldEqual (rank.toString + suit.toString)
        }
      }
    }
  }
}