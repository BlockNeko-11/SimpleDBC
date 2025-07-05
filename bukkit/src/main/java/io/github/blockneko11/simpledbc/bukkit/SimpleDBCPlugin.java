package io.github.blockneko11.simpledbc.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleDBCPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("SimpleDBC plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SimpleDBC plugin disabled!");
    }
}
