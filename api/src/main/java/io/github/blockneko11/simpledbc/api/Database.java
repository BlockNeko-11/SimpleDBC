package io.github.blockneko11.simpledbc.api;

import io.github.blockneko11.simpledbc.api.statement.SQLStatement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public interface Database {

    // info

    @NotNull
    String getUrl();

    String getUsername();

    String getPassword();

    String getDatabaseName();

    // connection

    void connect() throws ClassNotFoundException, SQLException;

    void disconnect() throws SQLException;

    default boolean isConnected() {
        return getConnection() != null;
    }

    @Nullable
    Connection getConnection();

    // execution

    int execute(@NotNull String sql) throws SQLException;

    default int execute(@NotNull String sql, Object... args) throws SQLException {
        return execute(new SQLStatement(sql, args));
    }

    int execute(@NotNull SQLStatement sql) throws SQLException;

    default List<Integer> executeBatchString(@NotNull String... batch) throws SQLException {
        return executeBatchString(Arrays.asList(batch));
    }

    List<Integer> executeBatchString(@NotNull Iterable<String> batch) throws SQLException;

    default List<Integer> executeBatch(@NotNull SQLStatement... batch) throws SQLException {
        return executeBatch(Arrays.asList(batch));
    }

    List<Integer> executeBatch(@NotNull Iterable<SQLStatement> batch) throws SQLException;
}
