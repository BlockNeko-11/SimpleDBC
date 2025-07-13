package io.github.blockneko11.simpledbc.api.action.insert;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.TableAction;
import org.jetbrains.annotations.NotNull;

/**
 * 表示一个插入数据到表的 SQL 操作。
 * @see TableAction
 * @author BlockNeko-11
 * @since 1.1.3
 */
public interface InsertAction extends TableAction {
    /**
     * 设置插入数据时是否忽略已存在的数据。
     * @param ignore 是否忽略已存在数据
     * @return 当前插入操作
     */
    InsertAction ignore(boolean ignore);

    /**
     * 添加一组数据，其中包含列名和列值。
     * <p>
     * 如果你使用的是 {@link Database#valueInsert(String)} 方法创建的插入操作，则列名将被忽略。
     * @param column 列名
     * @param value 列值
     * @return 当前插入操作
     */
    InsertAction value(String column, @NotNull Object value);
}
