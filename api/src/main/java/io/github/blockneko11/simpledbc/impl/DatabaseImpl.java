package io.github.blockneko11.simpledbc.impl;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.statement.SQLStatement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public int update(@NotNull String sql) throws SQLException {
        this.checkConnection();

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            return statement.executeUpdate();
        }
    }

    @Override
    public int update(@NotNull SQLStatement sql) throws SQLException {
        this.checkConnection();

        try (PreparedStatement statement = getConnection().prepareStatement(sql.getSQL())) {
            Object[] args = sql.getArgs();

            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }

            return statement.executeUpdate();
        }
    }

    @Override
    public ResultSet query(@NotNull String sql) throws SQLException {
        this.checkConnection();

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            return statement.executeQuery();
        }
    }

    @Override
    public ResultSet query(@NotNull SQLStatement sql) throws SQLException {
        this.checkConnection();

        try (PreparedStatement statement = getConnection().prepareStatement(sql.getSQL())) {
            Object[] args = sql.getArgs();

            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }

            return statement.executeQuery();
        }
    }

    private void checkConnection() throws SQLException {
        if (!isConnected()) {
            throw new SQLException("Not connected to database");
        }
    }
}
