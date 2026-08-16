package com.magmaguy.elitemobs.integrations.drakes;

import com.magmaguy.elitemobs.api.drakes.DrakesEliteMobsBridge;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class DrakesBridgeBootstrap {

    private static DrakesEliteMobsBridgeImpl provider;

    private DrakesBridgeBootstrap() {
    }

    public static void register(JavaPlugin plugin) {
        shutdown(plugin);
        DrakesBridgeConfig config = DrakesBridgeConfig.load(plugin);
        provider = new DrakesEliteMobsBridgeImpl(plugin, config);
        Bukkit.getServicesManager().register(DrakesEliteMobsBridge.class, provider, plugin, ServicePriority.Normal);
        Bukkit.getPluginManager().registerEvents(provider, plugin);
        plugin.getLogger().info("[DrakesBridge] " + (config.enabled() ? "activo" : "desactivado por configuracion"));
    }

    public static void shutdown(JavaPlugin plugin) {
        if (provider == null) return;
        Bukkit.getServicesManager().unregister(DrakesEliteMobsBridge.class, provider);
        HandlerList.unregisterAll(provider);
        provider = null;
    }
}
