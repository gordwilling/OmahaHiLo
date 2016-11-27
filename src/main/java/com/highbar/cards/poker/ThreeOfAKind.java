package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ThreeOfAKind extends RankedHand {

    private List<Card> triplet;
    private List<Card> kickers;

    public ThreeOfAKind(@NotNull List<Card> triplet, @NotNull List<Card> kickers, @NotNull Comparator<Card> comparator) {
        super(HandRank.ThreeOfAKind, comparator);
        this.triplet = new ArrayList<>(triplet);
        this.kickers = new ArrayList<>(kickers);
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull RankedHand r) {
        int result = super.compareTo(r);
        if (result == 0) {
            ThreeOfAKind that = (ThreeOfAKind) r;
            result = compare(this.triplet.get(0), that.triplet.get(0));
        }
        return result;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        List<Card> cards = new ArrayList<>(triplet);
        cards.addAll(kickers);
        return Cards.toString(cards);
    }
}