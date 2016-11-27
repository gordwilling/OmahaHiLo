package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FullHouse extends RankedHand<FullHouse> {

    private SortedSet<Card> three;
    private SortedSet<Card> two;

    public FullHouse(@NotNull Set<Card> three, @NotNull Set<Card> two, @NotNull Comparator<Card> comparator) {
        super(HandRank.ThreeOfAKind, comparator);
        this.three = new TreeSet<>(comparator);
        this.two = new TreeSet<>(comparator);
        this.three.addAll(three);
        this.two.addAll(two);
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull FullHouse that) {
        return this.three.first().rank().compareTo(that.three.first().rank());
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        List<Card> cards = new ArrayList<>(three);
        cards.addAll(two);
        return Cards.toString(cards);
    }
}