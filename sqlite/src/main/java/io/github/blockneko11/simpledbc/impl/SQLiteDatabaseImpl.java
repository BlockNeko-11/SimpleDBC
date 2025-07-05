package io.github.blockneko11.simpledbc.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabaseImpl extends DatabaseImpl {
    public SQLiteDatabaseImpl(@NotNull String path) {
        super(path);
    }

    @Nullable
    @Override
    public String getUsername() {
        return null;
    }

    @Nullable
    @Override
    public String getPassword() {
        return null;
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
            setConnection(DriverManager.getConnection(getUrl()));
        }
    }
}
