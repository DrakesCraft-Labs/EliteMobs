package com.magmaguy.elitemobs.integrations.drakes;

import com.magmaguy.elitemobs.api.EliteMobDeathEvent;
import com.magmaguy.elitemobs.api.drakes.DrakesEliteMobsBridge;
import com.magmaguy.elitemobs.api.internal.RemovalReason;
import com.magmaguy.elitemobs.entitytracker.EntityTracker;
import com.magmaguy.elitemobs.mobconstructor.custombosses.CustomBossEntity;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;
import java.util.UUID;

final class DrakesEliteMobsBridgeImpl implements DrakesEliteMobsBridge, Listener {

    private final JavaPlugin plugin;
    private final DrakesBridgeConfig config;
    private final NamespacedKey ownerKey;

    DrakesEliteMobsBridgeImpl(JavaPlugin plugin, DrakesBridgeConfig config) {
        this.plugin = plugin;
        this.config = config;
        this.ownerKey = new NamespacedKey(plugin, "drakes_owner");
    }

    @Override
    public boolean isEnabled() {
        return config.enabled();
    }

    @Override
    public Optional<UUID> spawnBoss(String template, Location location, int level, String owner) {
        if (!isEnabled() || location == null || location.getWorld() == null) return Optional.empty();
        if (!config.policy().allowsSpawn(owner, location.getWorld().getName(), template)) return Optional.empty();

        try {
            CustomBossEntity boss = CustomBossEntity.createCustomBossEntity(template);
            if (boss == null) return Optional.empty();
            boss.spawn(location, Math.max(1, level), false);
            LivingEntity entity = boss.getLivingEntity();
            if (entity == null || !entity.isValid()) return Optional.empty();
            markOwner(entity, owner);
            return Optional.of(entity.getUniqueId());
        } catch (RuntimeException exception) {
            plugin.getLogger().severe("[DrakesBridge] No se pudo crear " + template + ": " + exception.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean claim(Entity entity, String owner) {
        if (!isEnabled() || entity == null || !EntityTracker.isEliteMob(entity)) return false;
        if (!config.policy().allowsOwner(owner)) return false;
        markOwner(entity, owner);
        return true;
    }

    @Override
    public Optional<String> owner(Entity entity) {
        if (entity == null) return Optional.empty();
        return Optional.ofNullable(entity.getPersistentDataContainer().get(ownerKey, PersistentDataType.STRING));
    }

    @Override
    public boolean isElite(Entity entity) {
        return entity != null && EntityTracker.isEliteMob(entity);
    }

    @Override
    public boolean remove(Entity entity) {
        if (entity == null || owner(entity).isEmpty()) return false;
        return EntityTracker.unregisterEliteEntity(entity, RemovalReason.REMOVE_COMMAND);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onOwnedEliteDeath(EliteMobDeathEvent event) {
        if (!config.suppressOwnedLoot() || owner(event.getEntity()).isEmpty()) return;
        if (event.getEntityDeathEvent() == null) return;

        // DrakesBosses is the sole reward authority for bridge-owned encounters.
        event.getEntityDeathEvent().getDrops().clear();
        event.getEntityDeathEvent().setDroppedExp(0);
    }

    private void markOwner(Entity entity, String owner) {
        entity.getPersistentDataContainer().set(ownerKey, PersistentDataType.STRING, owner);
    }
}
