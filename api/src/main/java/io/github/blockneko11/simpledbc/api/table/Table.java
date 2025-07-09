package io.github.blockneko11.simpledbc.api.table;

import io.github.blockneko11.simpledbc.impl.table.TableImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 数据库表。
 * @author BlockNeko-11
 * @since 1.1.1
 */
public interface Table {
    /**
     * 获取表名。
     * @return 表名
     */
    @NotNull
    String getName();

    /**
     * 获取列。
     * @return 列
     */
    @NotNull
    List<Column> getColumns();

    /**
     * 创建一个表的构建器。
     * @param name 表名
     * @return 表的构建器。一个 {@link Table.Builder} 实例
     */
    static Table.Builder builder(@NotNull String name) {
        return new TableImpl.BuilderImpl(name);
    }

    /**
     * 表的构建器。
     */
    interface Builder {
        /**
         * 添加列。
         * @param column 列
         * @return 自身
         */
        Table.Builder column(Column column);

        /**
         * 构建表。
         * @return 表。一个 {@link Table} 实例
         */
        Table build();
    }
}
