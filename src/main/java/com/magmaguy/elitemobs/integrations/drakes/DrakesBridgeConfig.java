package com.magmaguy.elitemobs.integrations.drakes;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashSet;

final class DrakesBridgeConfig {

    private final boolean enabled;
    private final boolean suppressOwnedLoot;
    private final DrakesBridgePolicy policy;

    private DrakesBridgeConfig(boolean enabled, boolean suppressOwnedLoot, DrakesBridgePolicy policy) {
        this.enabled = enabled;
        this.suppressOwnedLoot = suppressOwnedLoot;
        this.policy = policy;
    }

    static DrakesBridgeConfig load(JavaPlugin plugin) {
        File target = new File(plugin.getDataFolder(), "drakes-integration.yml");
        if (!target.exists()) plugin.saveResource("drakes-integration.yml", false);

        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(target);
        return new DrakesBridgeConfig(
                yaml.getBoolean("enabled", false),
                yaml.getBoolean("suppress-elite-loot-for-owned-bosses", true),
                new DrakesBridgePolicy(
                        new HashSet<>(yaml.getStringList("trusted-owners")),
                        new HashSet<>(yaml.getStringList("allowed-world-prefixes")),
                        new HashSet<>(yaml.getStringList("allowed-templates"))));
    }

    boolean enabled() {
        return enabled;
    }

    boolean suppressOwnedLoot() {
        return suppressOwnedLoot;
    }

    DrakesBridgePolicy policy() {
        return policy;
    }
}
