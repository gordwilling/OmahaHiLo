package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.CardRank;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

import static com.highbar.cards.CardRank.Ace;

public class CardComparators {

    /**
     * @return a comparator that compares cards using High Hand rules (Ace is high)
     */
    @NotNull
    @Contract(pure = true)
    public static Comparator<Card> highHand() {
        return Comparator.comparingInt(x -> x.rank().ordinal());
    }

    /**
     * @return a comparator that compares cards using Low 8 rules (Ace is low)
     */
    @NotNull
    @Contract(pure = true)
    public static Comparator<Card> low8() {
        return (x, y) -> {
            if (both(x, y).are(Ace) || both(x, y).areNot(Ace)) {
                return x.rank().ordinal() - y.rank().ordinal();
            } else return x.rank() == Ace ? -1 : 1;
        };
    }

    /**
     * Some sugar, just for fun
     */
    @NotNull
    @Contract(pure = true)
    private static Both both(@NotNull Card x, @NotNull Card y) {
        return new Both(x, y);
    }

    private static class Both {
        private Card x;
        private Card y;

        Both(Card x, Card y) {
            this.x = x;
            this.y = y;
        }

        boolean are(CardRank rank) {
            return x.rank() == rank && y.rank() == rank;
        }

        boolean areNot(CardRank rank) {
            return x.rank() != rank && y.rank() != rank;
        }
    }
}
