package me.the_ktc.performance;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("performance").setExecutor((CommandExecutor) new Performance());
        System.out.println("Performance Plugin geladen!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
