package io.github.blockneko11.simpledbc.impl;

import io.github.blockneko11.simpledbc.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBDatabase extends AbstractCredentialDatabase {

    public MariaDBDatabase(@NotNull String url,
                           @NotNull String username,
                           @NotNull String password,
                           @NotNull String databaseName) {
        super(url, username, password, databaseName);
    }

    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        if (!isInitialized()) {
            Class.forName("org.mariadb.jdbc.Driver");
            setInitialized(true);
        }

        if (!isConnected()) {
            String uri = StringUtil.format("jdbc:mariadb://{0}/{1}?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    getUrl(),
                    getDatabaseName()
            );
            setConnection(DriverManager.getConnection(uri, getUsername(), getPassword()));
        }
    }
}
