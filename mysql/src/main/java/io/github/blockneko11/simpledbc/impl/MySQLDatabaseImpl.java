package io.github.blockneko11.simpledbc.impl;

import org.jetbrains.annotations.NotNull;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseImpl extends DatabaseImpl {
    private final String username;
    private final String password;
    private final String databaseName;

    public MySQLDatabaseImpl(@NotNull String url,
                                @NotNull String username,
                                @NotNull String password,
                                @NotNull String databaseName) {
        super(url);
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;
    }

    @NotNull
    @Override
    public String getUsername() {
        return this.username;
    }

    @NotNull
    @Override
    public String getPassword() {
        return this.password;
    }

    @NotNull
    @Override
    public String getDatabaseName() {
        return this.databaseName;
    }

    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        if (!initialized) {
            Class.forName("com.mysql.jdbc.Driver");
            initialized = true;
        }

        if (!isConnected()) {
            String uri = "jdbc:mysql://" + getUrl() + "/" + getDatabaseName() + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            setConnection(DriverManager.getConnection(uri, getUsername(), getPassword()));
        }
    }
}
