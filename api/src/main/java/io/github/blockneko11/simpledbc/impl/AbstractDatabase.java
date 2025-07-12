package io.github.blockneko11.simpledbc.impl;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.insert.InsertAction;
import io.github.blockneko11.simpledbc.api.action.table.TableCreateAction;
import io.github.blockneko11.simpledbc.impl.action.insert.ColumnInsertAction;
import io.github.blockneko11.simpledbc.impl.action.insert.ValueInsertAction;
import io.github.blockneko11.simpledbc.impl.action.table.TableCreateActionImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDatabase implements Database {
    private boolean initialized = false;
    private final String url;
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

    protected boolean isInitialized() {
        return this.initialized;
    }

    protected void setInitialized(boolean initialized) {
        this.initialized = initialized;
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
    public TableCreateAction createTable(@NotNull String table) throws SQLException {
        this.checkConnection();
        return new TableCreateActionImpl(this, table);

//        return this.update("CREATE TABLE IF NOT EXISTS " +
//                table.getName() +
//                " (" +
//                String.join(", ", table.getColumns().stream().map(column -> column.getName() +
//                        " " +
//                        column.getType()).toArray(String[]::new)) +
//                ");");
    }

    @Override
    public InsertAction valueInsert(@NotNull String table) throws SQLException {
        this.checkConnection();

        return new ValueInsertAction(this, table);
    }

    @Override
    public InsertAction columnInsert(@NotNull String table) throws SQLException {
        this.checkConnection();

        return new ColumnInsertAction(this, table);
    }

    protected void checkConnection() throws SQLException {
        if (!isConnected()) {
            throw new SQLException("Not connected to database");
        }
    }
}
