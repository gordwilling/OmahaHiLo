package com.highbar.cards;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Deck {
    private Set<Card> cards;

    public Deck() {
        Set<Card> cards = new HashSet<>();
        for (Suit suit : Suit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        this.cards = Collections.unmodifiableSet(cards);
    }

    @Contract(pure = true)
    @NotNull
    public Set<Card> cards() {
        return cards;
    }
}