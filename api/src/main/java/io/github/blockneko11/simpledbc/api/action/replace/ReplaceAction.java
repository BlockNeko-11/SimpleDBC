package io.github.blockneko11.simpledbc.api.action.replace;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.TableAction;
import org.jetbrains.annotations.NotNull;

/**
 * 表示一个替换表中数据的 SQL 操作。
 * @see TableAction
 * @author BlockNeko-11
 * @since 1.1.3
 */
public interface ReplaceAction extends TableAction {
    /**
     * 设置要替换的表中的数据。
     * <p>
     * 如果你使用的是 {@link Database#valueInsert(String)} 方法创建的替换操作，则列名将被忽略。
     * @param key 列名
     * @param value 列值
     * @return 当前替换操作
     */
    ReplaceAction value(String key, @NotNull Object value);
}
