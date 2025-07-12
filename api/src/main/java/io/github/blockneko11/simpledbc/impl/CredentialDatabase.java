package io.github.blockneko11.simpledbc.impl;

import org.jetbrains.annotations.NotNull;

public abstract class CredentialDatabase extends AbstractDatabase {
    protected final String username;
    protected final String password;
    protected final String databaseName;

    protected CredentialDatabase(@NotNull String url,
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
}
