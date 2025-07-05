package io.github.blockneko11.simpledbc.impl;

import io.github.blockneko11.simpledbc.api.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabaseImpl extends DatabaseImpl {
    private final String username;
    private final String password;

    protected SQLiteDatabaseImpl(@NotNull String url) {
        this(url, null, null);
    }

    protected SQLiteDatabaseImpl(@NotNull String url, @Nullable String username, @Nullable String password) {
        super(url);
        this.username = username;
        this.password = password;
    }

    @Nullable
    @Override
    public String getUsername() {
        return this.username;
    }

    @Nullable
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getDatabaseName() {
        return null;
    }

    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        if (!initialized) {
            Class.forName("org.sqlite.JDBC");
            initialized = true;
        }

        if (!isConnected()) {
            if (!StringUtil.isEmpty(this.username) && !StringUtil.isEmpty(this.password)) {
                setConnection(DriverManager.getConnection(getUrl(), getUsername(), getPassword()));
            } else {
                setConnection(DriverManager.getConnection(getUrl()));
            }
        }
    }
}
