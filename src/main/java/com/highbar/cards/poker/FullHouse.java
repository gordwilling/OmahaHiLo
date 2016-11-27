package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FullHouse extends RankedHand<FullHouse> {

    private SortedSet<Card> triplet;
    private SortedSet<Card> pair;

    public FullHouse(@NotNull List<Card> triplet, @NotNull List<Card> pair, @NotNull Comparator<Card> comparator) {
        super(HandRank.FullHouse, comparator);
        this.triplet = new TreeSet<>(comparator);
        this.pair = new TreeSet<>(comparator);
        this.triplet.addAll(triplet);
        this.pair.addAll(pair);
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull FullHouse that) {
        return compare(this.triplet.first(), that.triplet.first());
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        List<Card> cards = new ArrayList<>(triplet);
        cards.addAll(pair);
        return Cards.toString(cards);
    }
}