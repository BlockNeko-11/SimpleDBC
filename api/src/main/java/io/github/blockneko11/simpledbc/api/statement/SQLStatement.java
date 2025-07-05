package io.github.blockneko11.simpledbc.api.statement;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public final class SQLStatement {
    @NotNull
    private final String sql;

    private final Object[] args;

    public SQLStatement(@NotNull String sql, Object... args) {
        this.sql = sql;
        this.args = args;
    }
}
