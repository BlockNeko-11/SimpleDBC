package io.github.blockneko11.simpledbc.api.util;

import org.jetbrains.annotations.Nullable;

public final class StringUtil {
    public static boolean isEmpty(@Nullable String str) {
        return str == null || str.isEmpty();
    }

    private StringUtil() {
        throw new UnsupportedOperationException();
    }
}
