package com.highbar.cards.poker

import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class PokerRulesSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {
  describe("Poker Rankings for High Hand") {
    it("If two hands are the of same ranking, the winner is the one having the higher cards")(pending)


    it("If two hands with the same ranking are tied after checking the higher cards, the kicker determines the winner")(pending)


    it("In the event of hands being absolutely identical in ranking, it is a tie and the pot is split")(pending)


    it("The suit has no impact on ranking")(pending)


    it("And Ace can be used in straights as the highest card or the lowest card")(pending)


    it("When two hands of 'Full House' are compared, the three of a kind are compared first")(pending)


  }
}
