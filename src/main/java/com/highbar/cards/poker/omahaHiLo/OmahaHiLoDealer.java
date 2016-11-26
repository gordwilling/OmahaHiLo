package com.highbar.cards.poker.omahaHiLo;

import com.highbar.cards.Card;
import com.highbar.cards.Deck;

import java.util.*;

public interface OmahaHiLoDealer {
    default Deal omahaHiLo(Deck deck) {
        List<Card> cards = new ArrayList<>(deck.cards());
        Collections.shuffle(cards);
        Set<Card> handA = new HashSet<>(cards.subList(0, 4));
        Set<Card> handB = new HashSet<>(cards.subList(4, 8));
        Set<Card> board = new HashSet<>(cards.subList(8, 13));
        return new Deal(handA, handB, board);
    }
}
