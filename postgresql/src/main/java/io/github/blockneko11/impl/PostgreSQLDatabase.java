package io.github.blockneko11.impl;

import io.github.blockneko11.simpledbc.impl.AbstractCredentialDatabase;
import io.github.blockneko11.simpledbc.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLDatabase extends AbstractCredentialDatabase {
    protected PostgreSQLDatabase(@NotNull String url,
                                 @NotNull String username,
                                 @NotNull String password,
                                 @NotNull String databaseName) {
        super(url, username, password, databaseName);
    }

    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        if (!isInitialized()) {
            Class.forName("org.postgresql.Driver");
            setInitialized(true);
        }

        if (!isConnected()) {
            String uri = StringUtil.format("jdbc:postgresql://{0}/{1}",
                    getUrl(),
                    getDatabaseName()
            );
            setConnection(DriverManager.getConnection(uri, getUsername(), getPassword()));
        }
    }
}
