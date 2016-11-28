package com.highbar.util;

import org.jetbrains.annotations.Contract;

public class Parameters {
    @Contract("null -> fail")
    public static void require(Object... os) {
        for (Object o : os) if (o == null) throw new NullPointerException();
    }
}