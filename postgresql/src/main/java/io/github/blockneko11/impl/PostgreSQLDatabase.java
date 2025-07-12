package io.github.blockneko11.impl;

import io.github.blockneko11.simpledbc.impl.CredentialDatabase;
import io.github.blockneko11.simpledbc.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLDatabase extends CredentialDatabase {
    protected PostgreSQLDatabase(@NotNull String url,
                                 @NotNull String username,
                                 @NotNull String password,
                                 @NotNull String databaseName) {
        super(url, username, password, databaseName);
    }

    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        if (!initialized) {
            Class.forName("org.postgresql.Driver");
            initialized = true;
        }

        if (!isConnected()) {
            String uri = StringUtil.format("jdbc:postgresql://{0}/{1}",
                    url,
                    this.databaseName
            );
            setConnection(DriverManager.getConnection(uri, this.username, this.password));
        }
    }
}
