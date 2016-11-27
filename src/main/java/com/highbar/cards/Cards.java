package com.highbar.cards;

import com.highbar.util.Strings;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class Cards {

    @Contract(pure = true)
    @NotNull
    public static <T> String toString(@NotNull Collection<T> ts) {
        return Strings.toString(ts, "-");
    }

    @Contract(pure = true)
    public static int compare(@NotNull List<Card> xs, @NotNull List<Card> ys, Comparator<Card> comparator) {
        List<Card2Tuple> cards = zip(xs, ys);
        Iterator<Card2Tuple> i = cards.iterator();
        int result = 0;
        while (result == 0 && i.hasNext()) {
            Card2Tuple tuple = i.next();
            result = comparator.compare(tuple.x, tuple.y);
        }
        return result;
    }

    @NotNull
    @Contract(pure = true)
    private static List<Card2Tuple> zip(@NotNull List<Card> xs, @NotNull List<Card> ys) {
        if (xs.size() != ys.size()) throw new UnsupportedOperationException();
        Iterator<Card> ix = xs.iterator();
        Iterator<Card> iy = ys.iterator();
        return xs.stream().map(z -> new Card2Tuple(ix.next(), iy.next())).collect(Collectors.toList());
    }

    private static class Card2Tuple {
        private Card x;
        private Card y;

        private Card2Tuple(@NotNull Card x, @NotNull Card y) {
            this.x = x;
            this.y = y;
        }

        @NotNull
        @Contract(pure = true)
        public String toString() {
            return x.toString() + ", " + y.toString();
        }
    }
}