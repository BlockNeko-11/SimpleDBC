package io.github.blockneko11.simpledbc.impl.statement;

import io.github.blockneko11.simpledbc.api.statement.SQLStatement;
import org.jetbrains.annotations.NotNull;

public class SQLStatementImpl implements SQLStatement {
    private final String sql;
    private final Object[] args;

    public SQLStatementImpl(@NotNull String sql, @NotNull Object... args) {
        this.sql = sql;
        this.args = args;
    }

    @NotNull
    @Override
    public String getSQL() {
        return sql;
    }

    @NotNull
    public Object[] getArgs() {
        return this.args;
    }
}
