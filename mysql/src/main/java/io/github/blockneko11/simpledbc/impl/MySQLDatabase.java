package io.github.blockneko11.simpledbc.impl;

import io.github.blockneko11.simpledbc.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabase extends AbstractDatabase {
    private final String username;
    private final String password;
    private final String databaseName;

    public MySQLDatabase(@NotNull String url,
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
            String uri = StringUtil.format("jdbc:mysql://{0}/{1}?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    url,
                    this.databaseName
            );
            setConnection(DriverManager.getConnection(uri, this.username, this.password));
        }
    }
}
