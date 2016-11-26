package com.highbar.cards;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;

public class Cards {
    @Contract(pure = true)
    @NotNull
    public static String toString(@NotNull Set<Card> cards) {
        Optional<String> opt = cards.stream()
                .map(Card::toString)
                .reduce((x, y) -> x + "-" + y);
        return opt.isPresent() ? opt.get() : "";
    }
}