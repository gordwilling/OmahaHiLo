package com.highbar.cards.poker.omahaHiLo;

import com.highbar.cards.Card;
import com.highbar.cards.CardRank;
import com.highbar.cards.Suit;
import com.highbar.cards.poker.CardComparators;
import com.highbar.cards.poker.HandEvaluator;
import com.highbar.cards.poker.RankedHand;

public class OmahaHiLo {
    public static void main(String[] args) {
        HandEvaluator aceHighEvaluator = new HandEvaluator(CardComparators.aceHigh());

        RankedHand hand = aceHighEvaluator.rank(
                new Card(CardRank.Three, Suit.Diamonds),
                new Card(CardRank.Two, Suit.Spades),
                new Card(CardRank.Three, Suit.Spades),
                new Card(CardRank.Two, Suit.Hearts),
                new Card(CardRank.Two, Suit.Clubs));
    }
}
