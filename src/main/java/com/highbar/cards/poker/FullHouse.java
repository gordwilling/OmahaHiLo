package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ThreeOfAKind extends RankedHand<ThreeOfAKind> {

    private SortedSet<Card> three;
    private SortedSet<Card> kickers;

    public ThreeOfAKind(@NotNull Set<Card> three, @NotNull Set<Card> kickers, @NotNull Comparator<Card> comparator) {
        super(HandRank.ThreeOfAKind, comparator);
        this.three = new TreeSet<>(comparator);
        this.kickers = new TreeSet<>(comparator);
        this.three.addAll(three);
        this.kickers.addAll(kickers);
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull ThreeOfAKind that) {
        return this.three.first().rank().compareTo(that.three.first().rank());
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        List<Card> cards = new ArrayList<>(three);
        cards.addAll(kickers);
        return Cards.toString(cards);
    }
}