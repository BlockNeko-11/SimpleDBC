package io.github.blockneko11.simpledbc.api.table;

import io.github.blockneko11.simpledbc.impl.table.ColumnImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * 数据库表的一列。
 * @author BlockNeko-11
 * @since 1.1.1
 */
public interface Column {
    /**
     * 获取列名。
     * @return 列名
     */
    @NotNull
    String getName();

    /**
     * 获取列的数据类型。
     * @return 列的数据类型
     */
    @NotNull
    String getType();

    /**
     * 获取列的属性。
     * @return 列的属性
     */
    @NotNull
    Set<Column.Feature> getFeatures();

    /**
     * 创建一个列的构建器。
     * @param name 列名
     * @param type 列的数据类型
     * @return 列的构建器。一个 {@link Column.Builder} 实例
     */
    static Column.Builder builder(@NotNull String name, @NotNull String type) {
        return new ColumnImpl.BuilderImpl(name, type);
    }

    /**
     * 列的构建器。
     */
    interface Builder {
        /**
         * 添加列的属性。
         * @param feature 列的属性
         * @return 自身
         */
        Column.Builder addFeature(Column.Feature feature);

        /**
         * 构建列。
         * @return 列。一个 {@link Column} 实例
         */
        Column build();
    }

    /**
     * 列的属性。
     */
    enum Feature {
        /**
         * 主键。
         */
        PRIMARY_KEY,

        /**
         * 自增。
         */
        AUTO_INCREMENT,

        /**
         * 非空。
         */
        NOT_NULL,

        /**
         * 唯一。
         */
        UNIQUE;

        @Override
        public String toString() {
            switch (this) {
                case PRIMARY_KEY:
                    return "PRIMARY KEY";
                case AUTO_INCREMENT:
                    return "AUTO_INCREMENT";
                case NOT_NULL:
                    return "NOT NULL";
                case UNIQUE:
                    return "UNIQUE";
                default:
                    throw new IllegalArgumentException("Unknown column feature: " + this);
            }
        }
    }
}
