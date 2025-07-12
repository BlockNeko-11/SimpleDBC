package io.github.blockneko11.simpledbc.api;

import io.github.blockneko11.simpledbc.api.statement.SQLStatement;
import io.github.blockneko11.simpledbc.api.table.Table;
import io.github.blockneko11.simpledbc.impl.DatabaseImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

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
     */
    String getDatabaseName();

    // connection

    /**
     * 连接到数据库。
     * @throws ClassNotFoundException 如果无法加载数据库驱动程序
     * @throws SQLException 如果无法连接到数据库
     */
    void connect() throws ClassNotFoundException, SQLException;

    /**
     * 断开数据库连接。
     * @throws SQLException 如果无法断开数据库连接
     */
    void disconnect() throws SQLException;

    /**
     * 检查数据库是否已连接。
     * @return 数据库是否已连接
     */
    default boolean isConnected() {
        return getConnection() != null;
    }

    /**
     * 获取数据库连接。
     * @return 数据库连接。一个 {@link Connection} 实例
     */
    @Nullable
    Connection getConnection();

    // generic
    // execution

    /**
     * 执行 SQL 语句。
     * @param sql SQL 语句
     * @return 影响的行数
     * @throws SQLException 如果 SQL 语句执行失败
     */
    int update(@NotNull String sql) throws SQLException;

    /**
     * @see #update(SQLStatement)
     */
    default int update(@NotNull String sql, Object... args) throws SQLException {
        return update(SQLStatement.of(sql, args));
    }

    /**
     * 执行 SQL 语句。
     * @param sql SQL 语句。一个 {@link SQLStatement} 实例
     * @return 影响的行数
     * @throws SQLException 如果 SQL 语句执行失败
     */
    int update(@NotNull SQLStatement sql) throws SQLException;

    // query

    /**
     * 执行 SQL 查询。
     * @param sql SQL 语句
     * @return 查询结果
     * @throws SQLException 如果 SQL 查询失败
     * @since 1.1.0
     */
    ResultSet query(@NotNull String sql) throws SQLException;

    /**
     * @see #query(SQLStatement)
     * @since 1.1.0
     */
    default ResultSet query(@NotNull String sql, Object... args) throws SQLException {
        return query(SQLStatement.of(sql, args));
    }

    /**
     * 执行 SQL 查询。
     * @param sql SQL 语句。一个 {@link SQLStatement} 实例
     * @return 查询结果
     * @throws SQLException 如果 SQL 查询失败
     * @since 1.1.0
     */
    ResultSet query(@NotNull SQLStatement sql) throws SQLException;

    // table create

    /**
     * 创建表。
     * @param table 表。一个 {@link Table} 实例
     * @return 创建的表行数
     * @throws SQLException 如果创建表失败
     * @since 1.1.1
     */
    int createTable(@NotNull Table table) throws SQLException;

    // insert

    int insertInto(@NotNull String table, @NotNull Object... values) throws SQLException;

    int insertInto(@NotNull String table, @NotNull Map<String, Object> values) throws SQLException;
}
