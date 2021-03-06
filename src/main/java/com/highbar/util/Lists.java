package com.highbar.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lists {
    /**
     * @return a List of all possible combinations of two elements from the given list
     */
    @Contract(pure = true)
    @NotNull
    public static <T> List<List<T>> nChoose2(@NotNull List<T> xs) {
        List<T> copy = new ArrayList<>(xs);
        return combine2(copy.remove(0), copy, new ArrayList<>());
    }

    @NotNull
    private static <T> List<List<T>> combine2(@NotNull T t, @NotNull List<T> xs, @NotNull List<List<T>> result) {
        if (xs.size() == 0) return result;
        else {
            xs.forEach(x -> {
                List<T> list = new ArrayList<>();
                list.add(t);
                list.add(x);
                result.add(list);
            });
            return combine2(xs.remove(0), xs, result);
        }
    }

    // On paper I could recursively define `n choose 3` in terms
    // of `(n - 1) choose 2` and I'm onto it here... but didn't
    // quite make it :(

    public static <T> List<List<T>> choose3(List<T> xs) {
        List<T> copy = new ArrayList<>(xs);
        List<List<T>> _2 = combine3(copy);

        copy = new ArrayList<>(xs);
        copy.remove(0);
        List<List<T>> _4 = combine3(copy);

        copy = new ArrayList<>(xs);
        copy.remove(0);
        copy.remove(0);
        List<List<T>> _6 = combine3(copy);

        List<List<T>> results = new ArrayList<>();
        results.addAll(_2);
        results.addAll(_4);
        results.addAll(_6);

        return results;
    }

    private static <T> List<List<T>> combine3(List<T> xs) {
        T a = xs.remove(0);
        List<List<T>> _1 = combine2(xs.remove(0), xs, new ArrayList<>());
        return _1.stream().map(x -> {
            x.add(a);
            return x;
        }).collect(Collectors.toList());
    }
}
