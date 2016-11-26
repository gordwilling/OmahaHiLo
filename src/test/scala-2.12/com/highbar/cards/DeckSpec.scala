package com.highbar.cards

import org.scalatest.FunSpec
import org.scalatest.Matchers

import scala.collection.JavaConverters._

class DeckSpec extends FunSpec with Matchers {

  describe("A deck of cards") {
    it("Contains 52 unique cards") {
      val deck = new Deck()
      deck.cards.size shouldEqual 52
    }

    it("Contains cards of 4 suits") {
      val deck = new Deck()
      deck.cards.asScala.map(_.suit).size shouldEqual 4
    }

    it("Contains cards of 13 ranks") {
      val deck = new Deck()
      deck.cards.asScala.map(_.rank).size shouldEqual 13
    }
  }
}