package com.magmaguy.elitemobs.api.drakes;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.Optional;
import java.util.UUID;

/**
 * Stable Bukkit-only contract used by DrakesCraft boss plugins.
 * Consumers discover this service through Bukkit's ServicesManager.
 */
public interface DrakesEliteMobsBridge {

    boolean isEnabled();

    Optional<UUID> spawnBoss(String template, Location location, int level, String owner);

    boolean claim(Entity entity, String owner);

    Optional<String> owner(Entity entity);

    boolean isElite(Entity entity);

    boolean remove(Entity entity);
}
