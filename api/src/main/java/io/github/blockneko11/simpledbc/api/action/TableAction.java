package io.github.blockneko11.simpledbc.api.action;

import org.jetbrains.annotations.NotNull;

/**
 * 表示一个依赖于表的，执行后返回整数的 SQL 操作。
 * @see Action
 * @author BlockNeko-11
 * @since 1.1.3
 */
public interface TableAction extends Action {
    /**
     * 获取表名。
     * @return 表名
     */
    @NotNull
    String getTable();
}
