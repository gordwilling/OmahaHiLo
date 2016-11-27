package com.highbar.cards.poker

import com.highbar.cards.Card
import com.highbar.cards._
import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class CardComparatorSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {

  private val highCard = CardComparators.aceHigh
  private val low8 = CardComparators.aceLow

  describe("When Comparing Two Cards using High Hand Rules") {
    describe("An Ace is The Highest Card. It") {
      it("is higher than a Two") {
        forAll(genAce, genTwo) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genAce, genThree) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genAce, genFour) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genAce, genFive) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genAce, genSix) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Seven") {
        forAll(genAce, genSeven) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than an Eight") {
        forAll(genAce, genEight) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Nine") {
        forAll(genAce, genNine) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Ten") {
        forAll(genAce, genTen) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Jack") {
        forAll(genAce, genJack) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Queen") {
        forAll(genAce, genQueen) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a King") {
        forAll(genAce, genKing) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Ace") {
        forAll(genAce, genAce) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A King") {
      it("is higher than a Two") {
        forAll(genKing, genTwo) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genKing, genThree) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genKing, genFour) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genKing, genFive) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genKing, genSix) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Seven") {
        forAll(genKing, genSeven) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than an Eight") {
        forAll(genKing, genEight) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Nine") {
        forAll(genKing, genNine) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Ten") {
        forAll(genKing, genTen) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Jack") {
        forAll(genKing, genJack) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Queen") {
        forAll(genKing, genQueen) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("ties with a King") {
        forAll(genKing, genKing) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Queen") {
      it("is higher than a Two") {
        forAll(genQueen, genTwo) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genQueen, genThree) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genQueen, genFour) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genQueen, genFive) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genQueen, genSix) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Seven") {
        forAll(genQueen, genSeven) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than an Eight") {
        forAll(genQueen, genEight) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Nine") {
        forAll(genQueen, genNine) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Ten") {
        forAll(genQueen, genTen) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Jack") {
        forAll(genQueen, genJack) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Queen") {
        forAll(genQueen, genQueen) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Jack") {
      it("is higher than a Two") {
        forAll(genJack, genTwo) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genJack, genThree) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genJack, genFour) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genJack, genFive) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genJack, genSix) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Seven") {
        forAll(genJack, genSeven) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than an Eight") {
        forAll(genJack, genEight) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Nine") {
        forAll(genJack, genNine) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Ten") {
        forAll(genJack, genTen) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Jack") {
        forAll(genJack, genJack) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Ten") {
      it("is higher than a Two") {
        forAll(genTen, genTwo) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genTen, genThree) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genTen, genFour) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genTen, genFive) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genTen, genSix) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Seven") {
        forAll(genTen, genSeven) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than an Eight") {
        forAll(genTen, genEight) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Nine") {
        forAll(genTen, genNine) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Ten") {
        forAll(genTen, genTen) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Nine") {
      it("is higher than a Two") {
        forAll(genNine, genTwo) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genNine, genThree) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genNine, genFour) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genNine, genFive) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genNine, genSix) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Seven") {
        forAll(genNine, genSeven) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than an Eight") {
        forAll(genNine, genEight) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Nine") {
        forAll(genNine, genNine) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("An Eight") {
      it("is higher than a Two") {
        forAll(genEight, genTwo) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genEight, genThree) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genEight, genFour) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genEight, genFive) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genEight, genSix) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Seven") {
        forAll(genEight, genSeven) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Eight") {
        forAll(genEight, genEight) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Seven") {
      it("is higher than a Two") {
        forAll(genSeven, genTwo) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genSeven, genThree) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genSeven, genFour) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genSeven, genFive) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genSeven, genSix) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Seven") {
        forAll(genSeven, genSeven) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Six") {
      it("is higher than a Two") {
        forAll(genSix, genTwo) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genSix, genThree) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genSix, genFour) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genSix, genFive) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Six") {
        forAll(genSix, genSix) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Five") {
      it("is higher than a Two") {
        forAll(genFive, genTwo) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genFive, genThree) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genFive, genFour) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Five") {
        forAll(genFive, genFive) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Four") {
      it("is higher than a Two") {
        forAll(genFour, genTwo) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genFour, genThree) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Four") {
        forAll(genFour, genFour) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Three") {
      it("is higher than a Two") {
        forAll(genThree, genTwo) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Three") {
        forAll(genThree, genThree) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Two") {
      it("ties with another Two") {
        forAll(genTwo, genTwo) {
          (x: Card, y: Card) => {
            highCard.compare(x, y) shouldEqual 0
          }
        }
      }
    }
  }

  describe("When Comparing Two Cards using Low 8 Rules") {
    describe("A King") {
      it("is higher than an Ace") {
        forAll(genKing, genAce) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Two") {
        forAll(genKing, genTwo) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genKing, genThree) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genKing, genFour) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genKing, genFive) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genKing, genSix) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Seven") {
        forAll(genKing, genSeven) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than an Eight") {
        forAll(genKing, genEight) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Nine") {
        forAll(genKing, genNine) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Ten") {
        forAll(genKing, genTen) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Jack") {
        forAll(genKing, genJack) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Queen") {
        forAll(genKing, genQueen) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("ties with a King") {
        forAll(genKing, genKing) {
          (x: Card, y: Card) => {
            low8.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Queen") {
      it("is higher than an Ace") {
        forAll(genQueen, genAce) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Two") {
        forAll(genQueen, genTwo) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genQueen, genThree) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genQueen, genFour) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genQueen, genFive) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genQueen, genSix) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Seven") {
        forAll(genQueen, genSeven) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than an Eight") {
        forAll(genQueen, genEight) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Nine") {
        forAll(genQueen, genNine) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Ten") {
        forAll(genQueen, genTen) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Jack") {
        forAll(genQueen, genJack) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Queen") {
        forAll(genQueen, genQueen) {
          (x: Card, y: Card) => {
            low8.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Jack") {
      it("is higher than an Ace") {
        forAll(genJack, genAce) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Two") {
        forAll(genJack, genTwo) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genJack, genThree) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genJack, genFour) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genJack, genFive) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genJack, genSix) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Seven") {
        forAll(genJack, genSeven) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than an Eight") {
        forAll(genJack, genEight) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Nine") {
        forAll(genJack, genNine) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Ten") {
        forAll(genJack, genTen) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Jack") {
        forAll(genJack, genJack) {
          (x: Card, y: Card) => {
            low8.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Ten") {
      it("is higher than an Ace") {
        forAll(genTen, genAce) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Two") {
        forAll(genTen, genTwo) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genTen, genThree) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genTen, genFour) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genTen, genFive) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genTen, genSix) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Seven") {
        forAll(genTen, genSeven) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than an Eight") {
        forAll(genTen, genEight) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Nine") {
        forAll(genTen, genNine) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Ten") {
        forAll(genTen, genTen) {
          (x: Card, y: Card) => {
            low8.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Nine") {
      it("is higher than an Ace") {
        forAll(genNine, genAce) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Two") {
        forAll(genNine, genTwo) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genNine, genThree) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genNine, genFour) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genNine, genFive) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genNine, genSix) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Seven") {
        forAll(genNine, genSeven) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than an Eight") {
        forAll(genNine, genEight) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Nine") {
        forAll(genNine, genNine) {
          (x: Card, y: Card) => {
            low8.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("An Eight") {
      it("is higher than an Ace") {
        forAll(genEight, genAce) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Two") {
        forAll(genEight, genTwo) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genEight, genThree) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genEight, genFour) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genEight, genFive) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genEight, genSix) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Seven") {
        forAll(genEight, genSeven) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Eight") {
        forAll(genEight, genEight) {
          (x: Card, y: Card) => {
            low8.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Seven") {
      it("is higher than an Ace") {
        forAll(genSeven, genAce) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Two") {
        forAll(genSeven, genTwo) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genSeven, genThree) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genSeven, genFour) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genSeven, genFive) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Six") {
        forAll(genSeven, genSix) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Seven") {
        forAll(genSeven, genSeven) {
          (x: Card, y: Card) => {
            low8.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Six") {
      it("is higher than an Ace") {
        forAll(genSix, genAce) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Two") {
        forAll(genSix, genTwo) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genSix, genThree) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genSix, genFour) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Five") {
        forAll(genSix, genFive) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Six") {
        forAll(genSix, genSix) {
          (x: Card, y: Card) => {
            low8.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Five") {
      it("is higher than an Ace") {
        forAll(genFive, genAce) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Two") {
        forAll(genFive, genTwo) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genFive, genThree) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Four") {
        forAll(genFive, genFour) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Five") {
        forAll(genFive, genFive) {
          (x: Card, y: Card) => {
            low8.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Four") {
      it("is higher than an Ace") {
        forAll(genFour, genAce) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Two") {
        forAll(genFour, genTwo) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Three") {
        forAll(genFour, genThree) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Four") {
        forAll(genFour, genFour) {
          (x: Card, y: Card) => {
            low8.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Three") {
      it("is higher than an Ace") {
        forAll(genThree, genAce) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("is higher than a Two") {
        forAll(genThree, genTwo) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Three") {
        forAll(genThree, genThree) {
          (x: Card, y: Card) => {
            low8.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("A Two") {
      it("is higher than an Ace") {
        forAll(genTwo, genAce) {
          (x: Card, y: Card) => {
            low8.compare(x, y) should be > 0
          }
        }
      }

      it("ties with another Two") {
        forAll(genTwo, genTwo) {
          (x: Card, y: Card) => {
            low8.compare(x, y) shouldEqual 0
          }
        }
      }
    }

    describe("An Ace") {
      it("ties with another Ace") {
        forAll(genAce, genAce) {
          (x: Card, y: Card) => {
            low8.compare(x, y) shouldEqual 0
          }
        }
      }
    }
  }
}