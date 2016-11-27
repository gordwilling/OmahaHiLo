package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FourOfAKind extends RankedHand<FourOfAKind> {

    private SortedSet<Card> four;
    private Card kicker;

    public FourOfAKind(@NotNull List<Card> four, @NotNull Card kicker, @NotNull Comparator<Card> comparator) {
        super(HandRank.FourOfAKind, comparator);
        this.four = new TreeSet<>(comparator);
        this.four.addAll(four);
        this.kicker = kicker;
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull FourOfAKind that) {
        return compare(this.four.first(), that.four.first());
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