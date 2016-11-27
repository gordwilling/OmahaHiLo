package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FourOfAKind extends RankedHand<FourOfAKind> {

    private SortedSet<Card> four;
    private Card kicker;

    public FourOfAKind(@NotNull Set<Card> four, @NotNull Card kicker, @NotNull Comparator<Card> comparator) {
        super(HandRank.FourOfAKind, comparator);
        this.four = new TreeSet<>(comparator);
        this.four.addAll(four);
        this.kicker = kicker;
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull FourOfAKind that) {
        return this.four.first().rank().compareTo(that.four.first().rank());
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        List<Card> cards = new ArrayList<>(four);
        cards.add(kicker);
        return Cards.toString(cards);
    }
}