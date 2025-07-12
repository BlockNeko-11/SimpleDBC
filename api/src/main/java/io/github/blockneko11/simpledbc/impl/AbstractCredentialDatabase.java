package io.github.blockneko11.simpledbc.impl;

import io.github.blockneko11.simpledbc.api.CredentialDatabase;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCredentialDatabase extends AbstractDatabase implements CredentialDatabase {
    protected final String username;
    protected final String password;
    protected final String databaseName;

    protected AbstractCredentialDatabase(@NotNull String url,
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
}
