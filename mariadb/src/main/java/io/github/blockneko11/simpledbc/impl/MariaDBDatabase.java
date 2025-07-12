package io.github.blockneko11.simpledbc.impl;

import io.github.blockneko11.simpledbc.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBDatabase extends AbstractDatabase {
    private final String username;
    private final String password;
    private final String databaseName;

    public MariaDBDatabase(@NotNull String url,
                           @NotNull String username,
                           @NotNull String password,
                           @NotNull String databaseName) {
        super(url);
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getDatabaseName() {
        return this.databaseName;
    }

    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        if (!initialized) {
            Class.forName("org.mariadb.jdbc.Driver");
            initialized = true;
        }

        if (!isConnected()) {
            String uri = StringUtil.format("jdbc:mariadb://{0}/{1}?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    url,
                    this.databaseName
            );
            setConnection(DriverManager.getConnection(uri, this.username, this.password));
        }
    }
}
