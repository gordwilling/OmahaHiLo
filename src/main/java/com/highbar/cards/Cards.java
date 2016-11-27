package com.highbar.cards;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.stream.Collectors;

public class Strings {
    @Contract(pure = true)
    @NotNull
    public static <T> String toString(@NotNull Collection<T> ts) {
        return ts.stream().map(T::toString).collect(Collectors.joining("-"));
    }
}