package io.github.blockneko11.simpledbc.impl;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.table.Table;
import io.github.blockneko11.simpledbc.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class AbstractDatabase implements Database {
    protected boolean initialized = false;
    protected final String url;
    private Connection connection = null;

    protected AbstractDatabase(@NotNull String url) {
        this.url = url;
    }

    @NotNull
    @Override
    public String getUrl() {
        return this.url;
    }

    @Nullable
    @Override
    public Connection getConnection() {
        return this.connection;
    }

    protected void setConnection(@Nullable Connection connection) {
        this.connection = connection;
    }

    @Override
    public void disconnect() throws SQLException {
        if (isConnected()) {
            getConnection().close();
        }
    }

    @Override
    public int update(@NotNull String sql) throws SQLException {
        this.checkConnection();

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            return statement.executeUpdate();
        }
    }

    @Override
    public int update(@NotNull String sql, @NotNull Object... args) throws SQLException {
        this.checkConnection();

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }

            return statement.executeUpdate();
        }
    }

    @Override
    public ResultSet query(@NotNull String sql) throws SQLException {
        this.checkConnection();

        PreparedStatement statement = getConnection().prepareStatement(sql);
        return statement.executeQuery();
    }

    @Override
    public ResultSet query(@NotNull String sql, @NotNull Object... args) throws SQLException {
        this.checkConnection();

        PreparedStatement statement = getConnection().prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            statement.setObject(i + 1, args[i]);
        }

        return statement.executeQuery();
    }

    @Override
    public int createTable(@NotNull Table table) throws SQLException {
        return this.update("CREATE TABLE IF NOT EXISTS " +
                table.getName() +
                " (" +
                String.join(", ", table.getColumns().stream().map(column -> column.getName() +
                        " " +
                        column.getType()).toArray(String[]::new)) +
                ");");
    }

    @Override
    public int insertInto(@NotNull String table, @NotNull Object... values) throws SQLException {
        this.checkConnection();

        return update("INSERT INTO " +
                table +
                " VALUES " +
                "(" +
                String.join(", ", StringUtil.repeat(values.length, "?")) +
                ");", values);
    }

    @Override
    public int insertInto(@NotNull String table, @NotNull Map<String, Object> values) throws SQLException {
        this.checkConnection();

        return update("INSERT INTO " +
                table +
                " (" +
                String.join(", ", values.keySet()) +
                ") VALUES " +
                "(" +
                String.join(", ", StringUtil.repeat(values.size(), "?")) +
                ");", values.values().toArray());
    }

    private void checkConnection() throws SQLException {
        if (!isConnected()) {
            throw new SQLException("Not connected to database");
        }
    }
}
