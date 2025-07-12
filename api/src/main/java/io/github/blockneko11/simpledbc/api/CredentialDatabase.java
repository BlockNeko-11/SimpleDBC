package io.github.blockneko11.simpledbc.api;

import org.jetbrains.annotations.NotNull;

/**
 * 带有凭据（用户名、密码等）数据库接口，继承自 {@link Database}。
 * @author BlockNeko-11
 * @since 1.1.2
 */
public interface CredentialDatabase extends Database {
    /**
     * 获取数据库用户名。
     * @return 数据库用户名
     */
    @NotNull
    String getUsername();

    /**
     * 获取数据库密码。
     * @return 数据库密码
     */
    @NotNull
    String getPassword();

    /**
     * 获取数据库名称。
     * @return 数据库名称
     */
    @NotNull
    String getDatabaseName();
}
