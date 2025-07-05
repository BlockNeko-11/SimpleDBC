package io.github.blockneko11.simpledbc.api.statement;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * 包装后的 SQL 语句。
 * @author BlockNeko-11
 * @since 1.0.0
 */
@Getter
public final class SQLStatement {
    /**
     * SQL 语句。
     */
    @NotNull
    private final String sql;

    /**
     * SQL 参数。
     */
    private final Object[] args;

    /**
     * 构造函数。
     * @param sql SQL 语句
     * @param args SQL 参数
     */
    public SQLStatement(@NotNull String sql, Object... args) {
        this.sql = sql;
        this.args = args;
    }
}
