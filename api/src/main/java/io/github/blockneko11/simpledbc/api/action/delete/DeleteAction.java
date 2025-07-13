package io.github.blockneko11.simpledbc.api.action.delete;

import io.github.blockneko11.simpledbc.api.action.TableAction;
import org.jetbrains.annotations.Nullable;

/**
 * 表示一个删除数据或整个表的 SQL 操作。
 * @see TableAction
 * @author BlockNeko-11
 * @since 1.1.3
 */
public interface DeleteAction extends TableAction {
    /**
     * 设置删除数据的条件。当条件为 null 时，表示删除整个表。
     * @param condition 条件
     * @return 当前对象
     */
    DeleteAction condition(@Nullable String condition);
}
