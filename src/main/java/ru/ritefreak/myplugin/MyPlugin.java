package ru.ritefreak.myplugin;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import ru.ritefreak.myplugin.commands.GmCommand;
import ru.ritefreak.myplugin.commands.HealCommand;
import ru.ritefreak.myplugin.listener.PluginListener;

public final class MyPlugin extends JavaPlugin {

    private static Economy econ = null;

    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getCommand("gm").setExecutor(new GmCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getServer().getPluginManager().registerEvents(new PluginListener(econ), this);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
