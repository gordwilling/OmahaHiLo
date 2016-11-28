package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class FullHouse extends RankedHand {

    private List<Card> triplet;
    private List<Card> pair;

    FullHouse(@NotNull List<Card> triplet, @NotNull List<Card> pair, @NotNull Comparator<Card> comparator) {
        super(HandRank.FullHouse, comparator);
        this.triplet = new ArrayList<>(triplet);
        this.pair = new ArrayList<>(pair);
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull RankedHand r) {
        int result = super.compareTo(r);
        if (result == 0) {
            FullHouse that = (FullHouse) r;
            result = compare(this.triplet.get(0), that.triplet.get(0));
        }
        return result;
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