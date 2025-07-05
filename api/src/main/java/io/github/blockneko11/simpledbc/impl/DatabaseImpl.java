package io.github.blockneko11.simpledbc.impl;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.statement.SQLStatement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseImpl implements Database {
    protected boolean initialized = false;
    private final String url;
    private Connection connection = null;

    protected DatabaseImpl(@NotNull String url) {
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
    public int execute(@NotNull String sql) throws SQLException {
        if (!isConnected()) {
            throw new SQLException("Not connected to database");
        }

        try (Statement statement = getConnection().createStatement()) {
            return statement.executeUpdate(sql);
        }
    }

    @Override
    public int execute(@NotNull SQLStatement sql) throws SQLException {
        if (!isConnected()) {
            throw new SQLException("Not connected to database");
        }

        try (PreparedStatement statement = getConnection().prepareStatement(sql.getSql())) {
            Object[] args = sql.getArgs();

            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }

            return statement.executeUpdate();
        }
    }

    @Override
    public List<Integer> executeBatchString(@NotNull Iterable<String> batch) throws SQLException {
        List<Integer> result = new ArrayList<>();

        for (@NotNull String sql : batch) {
            try (Statement statement = getConnection().createStatement()) {
                result.add(statement.executeUpdate(sql));
            }
        }

        return result;
    }

    @Override
    public List<Integer> executeBatch(@NotNull Iterable<SQLStatement> batch) throws SQLException {
        List<Integer> result = new ArrayList<>();

        for (@NotNull SQLStatement sql : batch) {
            Object[] args = sql.getArgs();

            try (PreparedStatement statement = getConnection().prepareStatement(sql.getSql())) {
                for (int i = 0; i < args.length; i++) {
                    statement.setObject(i + 1, args[i]);
                }

                result.add(statement.executeUpdate());
            }
        }

        return result;
    }
}
