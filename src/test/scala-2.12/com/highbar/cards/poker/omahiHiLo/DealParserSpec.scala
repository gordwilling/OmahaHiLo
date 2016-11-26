package com.highbar.cards.poker.omahiHiLo

import com.highbar.cards.Deck
import com.highbar.cards.poker.Dealer
import com.highbar.cards.poker.omahaHiLo.DealParser
import org.scalatest.FunSpec
import org.scalatest.Matchers

class DealParserSpec extends FunSpec with Matchers {

  val deck = new Deck
  val dealer = new Dealer

  describe("Deal Parser") {
    it("Parses a string representing two hands and a board") {
      val in = dealer.omahaHiLo(deck)
      val out = DealParser.parse(in.toString)
      out shouldEqual in
    }
  }
}