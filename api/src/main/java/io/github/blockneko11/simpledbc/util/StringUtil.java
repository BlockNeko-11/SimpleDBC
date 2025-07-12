package io.github.blockneko11.simpledbc.util;

import org.jetbrains.annotations.NotNull;
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

    public static String toString(Object o) {
        if (o == null) {
            return "null";
        }

        return o.toString();
    }

    public static String format(@NotNull String str, @Nullable Object... args) {
        if (isEmpty(str)) {
            return str;
        }

        for (int i = 0; i < args.length; i++) {
            String s = toString(args[i]);
            str = str.replaceFirst("\\{}", s).replace("{" + i + "}", s);
        }

        return str;
    }

    private StringUtil() {
        throw new UnsupportedOperationException();
    }
}
