package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.CardRank;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

import static com.highbar.cards.CardRank.Ace;

public class CardComparators {

    /**
     * @return a comparator that compares cards using 'Ace is high
     */
    @NotNull
    @Contract(pure = true)
    public static Comparator<Card> aceHigh() {
        return (x, y) -> {
            if (both(x, y).are(Ace) || both(x, y).areNot(Ace)) {
                return x.rank().ordinal() - y.rank().ordinal();
            } else return x.rank() == Ace ? 1 : -1;
        };
    }

    /**
     * @return a comparator that compares cards using 'Ace is low' rule
     */
    @NotNull
    @Contract(pure = true)
    public static Comparator<Card> aceLow() {
        return Comparator.comparingInt(x -> x.rank().ordinal());
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
