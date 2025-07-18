package io.github.blockneko11.simpledbc.impl;

import org.jetbrains.annotations.NotNull;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabase extends AbstractDatabase {
    public SQLiteDatabase(@NotNull String path) {
        super(path);
    }

    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        if (!isInitialized()) {
            Class.forName("org.sqlite.JDBC");
            setInitialized(true);
        }

        if (!isConnected()) {
            setConnection(DriverManager.getConnection(getUrl()));
        }
    }
}
