package io.github.blockneko11.simpledbc.api.action.table;

import io.github.blockneko11.simpledbc.api.action.TableAction;

/**
 * 表示一个创建表的 SQL 操作。
 * @see TableAction
 * @author BlockNeko-11
 * @since 1.1.3
 */
public interface TableCreateAction extends TableAction {
    /**
     * 添加一个列。
     * @param name 列名
     * @param type 列的数据类型
     * @return 当前创建表的操作
     */
    default TableCreateAction column(String name, String type) {
        return column(Column.builder(name, type).build());
    }

    /**
     * 添加一个列。
     * @param name 列名
     * @param type 列的数据类型
     * @param attributes 列的属性
     * @return 当前创建表的操作
     */
    default TableCreateAction column(String name, String type, Attribute... attributes) {
        Column.Builder builder = Column.builder(name, type);
        for (Attribute attribute : attributes) {
            builder.attribute(attribute);
        }

        return column(builder.build());
    }

    /**
     * 添加一个列。
     * @param column 列。一个 {@link Column} 实例
     * @return 当前创建表的操作
     */
    TableCreateAction column(Column column);
}
