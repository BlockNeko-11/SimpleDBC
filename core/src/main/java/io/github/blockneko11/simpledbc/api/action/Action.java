package io.github.blockneko11.simpledbc.api.action;

import java.sql.SQLException;

/**
 * 表示一个执行后返回整数的 SQL 操作。
 * @author BlockNeko-11
 * @since 1.1.3
 */
public interface Action {
    /**
     * 执行 SQL 操作。
     * @return 执行结果
     * @throws SQLException SQL 异常
     */
    int execute() throws SQLException;
}
