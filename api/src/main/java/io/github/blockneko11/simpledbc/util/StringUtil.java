package io.github.blockneko11.simpledbc.util;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class StringUtil {
    public static boolean isEmpty(@Nullable String str) {
        return str == null || str.isEmpty();
    }

    public static List<String> repeat(int times, String str) {
        List<String> result = new ArrayList<>(times);
        for (int i = 0; i < times; i++) {
            result.add(str);
        }
        return result;
    }

    private StringUtil() {
        throw new UnsupportedOperationException();
    }
}
