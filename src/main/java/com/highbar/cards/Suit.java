package com.highbar.cards;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;

public enum Suit implements HasSymbol {
    Clubs("c"), Diamonds("d"), Hearts("h"), Spades("s");

    private final String symbol;

    Suit(@NotNull String symbol) {
        this.symbol = symbol;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String symbol() {
        return symbol;
    }

    @NotNull
    @Contract(pure = true)
    public static Suit from(@NotNull String s) {
        Optional<Suit> opt = Arrays.stream(values()).filter(v -> v.symbol.equals(s)).findFirst();
        if (opt.isPresent()) return opt.get();
        throw new IllegalArgumentException(s);
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        return symbol;
    }
}