package io.github.blockneko11.simpledbc.api;

import io.github.blockneko11.simpledbc.api.statement.SQLStatement;
import io.github.blockneko11.simpledbc.impl.DatabaseImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    int update(@NotNull String sql) throws SQLException;

    /**
     * @see #update(SQLStatement)
     * @since 1.0.0
     */
    default int update(@NotNull String sql, Object... args) throws SQLException {
        return update(SQLStatement.of(sql, args));
    }

    /**
     * 执行 SQL 语句。
     * @param sql SQL 语句。一个 {@link SQLStatement} 实例
     * @return 影响的行数
     * @throws SQLException 如果 SQL 语句执行失败
     * @since 1.0.0
     */
    int update(@NotNull SQLStatement sql) throws SQLException;


    ResultSet query(@NotNull String sql) throws SQLException;

    default ResultSet query(@NotNull String sql, Object... args) throws SQLException {
        return query(SQLStatement.of(sql, args));
    }

    ResultSet query(@NotNull SQLStatement sql) throws SQLException;
}
