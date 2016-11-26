package com.highbar

import org.scalacheck.Gen

package object cards {
  val genCardRankString: Gen[String] = Gen.oneOf(CardRank.values.map(_.symbol))

  val genSuitString: Gen[String] = Gen.oneOf(Suit.values.map(_.symbol))

  val genCard: Gen[Card] = for {
    rank <- Gen.oneOf(CardRank.values)
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(rank, suit)

  val genAce: Gen[Card] = for {
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(CardRank.Ace, suit)

  val genTwo: Gen[Card] = for {
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(CardRank.Two, suit)

  val genThree: Gen[Card] = for {
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(CardRank.Three, suit)

  val genFour: Gen[Card] = for {
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(CardRank.Four, suit)

  val genFive: Gen[Card] = for {
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(CardRank.Five, suit)

  val genSix: Gen[Card] = for {
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(CardRank.Six, suit)

  val genSeven: Gen[Card] = for {
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(CardRank.Seven, suit)

  val genEight: Gen[Card] = for {
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(CardRank.Eight, suit)

  val genNine: Gen[Card] = for {
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(CardRank.Nine, suit)

  val genTen: Gen[Card] = for {
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(CardRank.Ten, suit)

  val genJack: Gen[Card] = for {
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(CardRank.Jack, suit)

  val genQueen: Gen[Card] = for {
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(CardRank.Queen, suit)

  val genKing: Gen[Card] = for {
    suit <- Gen.oneOf(Suit.values)
  } yield new Card(CardRank.King, suit)

  val genClub: Gen[Card] = for {
    rank <- Gen.oneOf(CardRank.values)
  } yield new Card(rank, Suit.Clubs)

  val genDiamond: Gen[Card] = for {
    rank <- Gen.oneOf(CardRank.values)
  } yield new Card(rank, Suit.Diamonds)

  val genHeart: Gen[Card] = for {
    rank <- Gen.oneOf(CardRank.values)
  } yield new Card(rank, Suit.Hearts)

  val genSpade: Gen[Card] = for {
    rank <- Gen.oneOf(CardRank.values)
  } yield new Card(rank, Suit.Spades)
}
