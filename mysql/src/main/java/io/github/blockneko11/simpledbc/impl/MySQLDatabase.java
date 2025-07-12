package io.github.blockneko11.simpledbc.impl;

import io.github.blockneko11.simpledbc.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabase extends AbstractCredentialDatabase {
    public MySQLDatabase(@NotNull String url,
                         @NotNull String username,
                         @NotNull String password,
                         @NotNull String databaseName) {
        super(url, username, password, databaseName);
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
                    databaseName
            );
            setConnection(DriverManager.getConnection(uri, username, password));
        }
    }
}
