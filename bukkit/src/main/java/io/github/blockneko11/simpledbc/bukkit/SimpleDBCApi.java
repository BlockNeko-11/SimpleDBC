package io.github.blockneko11.simpledbc.bukkit;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.impl.MySQLDatabaseImpl;
import io.github.blockneko11.simpledbc.impl.SQLiteDatabaseImpl;

public final class SimpleDBCApi {
    public static Database createMySQL(String url,
                                       String username,
                                       String password,
                                       String databaseName) {
        return new MySQLDatabaseImpl(url, username, password, databaseName);
    }

    public static Database createSQLite(String path) {
        return new SQLiteDatabaseImpl(path);
    }
}
