package com.highbar.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.stream.Collectors;

public class Strings {
    @NotNull
    @Contract(pure = true)
    public static <T> String toString(@NotNull Collection<T> ts, @NotNull String delimiter) {
        return ts.stream().map(T::toString).collect(Collectors.joining(delimiter));
    }
}
