package io.github.blockneko11.simpledbc.api;

import io.github.blockneko11.simpledbc.api.statement.SQLStatement;
import io.github.blockneko11.simpledbc.impl.DatabaseImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * 数据库接口。
 * @see DatabaseImpl
 * @author BlockNeko-11
 * @since 1.0.0
 */
public interface Database {

    // info

    /**
     * 获取数据库地址。
     * @return 数据库地址
     * @since 1.0.0
     */
    @NotNull
    String getUrl();

    /**
     * 获取数据库用户名。
     * <p>
     * 使用 SQLite 的实现时，此方法一定返回 {@code null}。
     * @return 数据库用户名
     * @since 1.0.2
     */
    String getUsername();

    /**
     * 获取数据库密码。
     * <p>
     * 使用 SQLite 的实现时，此方法一定返回 {@code null}。
     * @return 数据库密码
     * @since 1.0.2
     */
    String getPassword();

    /**
     * 获取数据库名称。
     * <p>
     * 使用 SQLite 的实现时，此方法一定返回 {@code null}。
     * @return 数据库名称
     * @since 1.0.0
     */
    String getDatabaseName();

    // connection

    /**
     * 连接到数据库。
     * @throws ClassNotFoundException 如果无法加载数据库驱动程序
     * @throws SQLException 如果无法连接到数据库
     * @since 1.0.0
     */
    void connect() throws ClassNotFoundException, SQLException;

    /**
     * 断开数据库连接。
     * @throws SQLException 如果无法断开数据库连接
     * @since 1.0.0
     */
    void disconnect() throws SQLException;

    /**
     * 检查数据库是否已连接。
     * @return 数据库是否已连接
     * @since 1.0.0
     */
    default boolean isConnected() {
        return getConnection() != null;
    }

    /**
     * 获取数据库连接。
     * @return 数据库连接。一个 {@link Connection} 实例
     * @since 1.0.0
     */
    @Nullable
    Connection getConnection();

    // execution

    /**
     * 执行 SQL 语句。
     * @param sql SQL 语句
     * @return 影响的行数
     * @throws SQLException 如果 SQL 语句执行失败
     * @since 1.0.0
     */
    int execute(@NotNull String sql) throws SQLException;

    /**
     * @see #execute(SQLStatement)
     * @since 1.0.0
     */
    default int execute(@NotNull String sql, Object... args) throws SQLException {
        return execute(new SQLStatement(sql, args));
    }

    /**
     * 执行 SQL 语句。
     * @param sql SQL 语句。一个 {@link SQLStatement} 实例
     * @return 影响的行数
     * @throws SQLException 如果 SQL 语句执行失败
     * @since 1.0.0
     */
    int execute(@NotNull SQLStatement sql) throws SQLException;

    /**
     * @see #executeBatch(Iterable)
     * @since 1.0.0
     */
    default List<Integer> executeBatchString(@NotNull String... batch) throws SQLException {
        return executeBatchString(Arrays.asList(batch));
    }

    /**
     * 批量执行 SQL 语句。
     * @param batch 批量 SQL 语句
     * @return 影响的行数
     * @throws SQLException 如果 SQL 语句执行失败
     * @since 1.0.0
     */
    List<Integer> executeBatchString(@NotNull Iterable<String> batch) throws SQLException;

    /**
     * @see #executeBatchString(Iterable)
     */
    default List<Integer> executeBatch(@NotNull SQLStatement... batch) throws SQLException {
        return executeBatch(Arrays.asList(batch));
    }

    /**
     * 批量执行 SQL 语句
     * @param batch 批量 SQL 语句
     * @return 影响的行数
     * @throws SQLException 如果 SQL 语句执行失败
     * @since 1.0.0
     */
    List<Integer> executeBatch(@NotNull Iterable<SQLStatement> batch) throws SQLException;
}
