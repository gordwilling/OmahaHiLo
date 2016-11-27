package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.highbar.cards.CardRank.*;

public class Straight extends RankedHand {

    private List<Card> cards;

    Straight(@NotNull HandRank rank, @NotNull List<Card> cards, @NotNull Comparator<Card> comparator) {
        super(rank, comparator);
        this.cards = new ArrayList<>(cards);
        this.cards.sort(comparator.reversed());
    }

    Straight(@NotNull List<Card> cards, @NotNull Comparator<Card> comparator) {
        this(HandRank.Straight, cards, comparator);
    }

    // Independent of high or low hand rules, Aces can be high or low in a Straight. As
    // a result, there is some extra logic here in the comparison and toString functions.

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull RankedHand r) {
        int result = super.compareTo(r);
        if (result == 0) {
            Straight that = (Straight) r;
            if (isHighest(this) && isHighest(that)) {
                result = 0;
            } else if (isLowest(this) && isLowest(that)) {
                result = 0;
            } else if (isHighest(this) || isLowest(that)) {
                result = 1;
            } else if (isLowest(this) || isHighest(that)) {
                result = -1;
            } else {
                result = compare(this.cards.get(0), that.cards.get(0));
            }
        }
        return result;
    }

    @Contract(pure = true)
    @NotNull
    @Override
    public String toString() {

        // if there's an Ace, getting it into the correct
        // position for display requires manhandling

        if (isHighest(this)) {
            List<Card> cards = new ArrayList<>(this.cards);
            int indexOfAce = indexOfAce(cards);
            Card ace = cards.remove(indexOfAce);
            cards.add(0, ace);
            return Cards.toString(cards);
        } else if (isLowest(this)) {
            List<Card> cards = new ArrayList<>(this.cards);
            int indexOfAce = indexOfAce(cards);
            Card ace = cards.remove(indexOfAce);
            cards.add(ace);
            return Cards.toString(cards);
        } else {
            return Cards.toString(cards);
        }
    }

    private boolean isHighest(Straight straight) {
        return straight.cards.stream()
                .map(Card::rank)
                .filter(x -> x == Ace || x == King)
                .count() == 2;
    }

    private boolean isLowest(Straight straight) {
        return straight.cards.stream()
                .map(Card::rank)
                .filter(x -> x == Ace || x == Two)
                .count() == 2;
    }

    private int indexOfAce(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).rank() == Ace) {
                return i;
            }
        }
        throw new IllegalStateException("Ace not found");
    }
}