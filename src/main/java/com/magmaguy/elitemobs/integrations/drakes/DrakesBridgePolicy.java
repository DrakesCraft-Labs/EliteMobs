package com.magmaguy.elitemobs.integrations.drakes;

import java.util.Locale;
import java.util.Set;

/** Applies deny-by-default ownership, world and template restrictions. */
final class DrakesBridgePolicy {

    private final Set<String> trustedOwners;
    private final Set<String> worldPrefixes;
    private final Set<String> allowedTemplates;

    DrakesBridgePolicy(Set<String> trustedOwners, Set<String> worldPrefixes, Set<String> allowedTemplates) {
        this.trustedOwners = normalize(trustedOwners);
        this.worldPrefixes = normalize(worldPrefixes);
        this.allowedTemplates = normalize(allowedTemplates);
    }

    boolean allowsOwner(String owner) {
        return owner != null && trustedOwners.contains(normalize(owner));
    }

    boolean allowsWorld(String world) {
        if (world == null) return false;
        String normalizedWorld = normalize(world);
        return worldPrefixes.stream().anyMatch(normalizedWorld::startsWith);
    }

    boolean allowsTemplate(String template) {
        return template != null && allowedTemplates.contains(normalize(template));
    }

    boolean allowsSpawn(String owner, String world, String template) {
        return allowsOwner(owner) && allowsWorld(world) && allowsTemplate(template);
    }

    private static Set<String> normalize(Set<String> values) {
        return values.stream().map(DrakesBridgePolicy::normalize).collect(java.util.stream.Collectors.toUnmodifiableSet());
    }

    private static String normalize(String value) {
        return value.trim().toLowerCase(Locale.ROOT);
    }
}
