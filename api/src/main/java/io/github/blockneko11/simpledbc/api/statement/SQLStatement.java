package io.github.blockneko11.simpledbc.api.statement;

import io.github.blockneko11.simpledbc.impl.statement.SQLStatementImpl;
import org.jetbrains.annotations.NotNull;

/**
 * 包装后的 SQL 语句。
 * @author BlockNeko-11
 * @since 1.1.0
 */
public interface SQLStatement {
    /**
     * 获取 SQL 语句。
     */
    @NotNull
    String getSQL();

    /**
     * 获取 SQL 参数。
     */
    @NotNull
    Object[] getArgs();

    static SQLStatement of(@NotNull String sql, @NotNull Object... args) {
        return new SQLStatementImpl(sql, args);
    }
}
