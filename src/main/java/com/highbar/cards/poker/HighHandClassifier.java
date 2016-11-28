package com.highbar.cards.poker;

import com.highbar.cards.Card;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HighHandClassifier extends AbstractHandClassifier {

    public HighHandClassifier() {
        super(CardComparators.aceHigh());
    }

    /**
     * @return the highest rank of the hand consisting of the five cards, using High Hand rules
     */
    @NotNull
    @Contract(pure = true)
    public RankedHand highHand(@NotNull List<Card> hand) {
        if (hand.size() != 5) throw new IllegalArgumentException();
        List<Card> cards = new ArrayList<>();
        cards.addAll(hand);

        Optional<? extends RankedHand> someOfAKind = someOfAKind(cards);

        return someOfAKind.isPresent()
                ? someOfAKind.get()
                : noneOfAKind(cards);
    }
}