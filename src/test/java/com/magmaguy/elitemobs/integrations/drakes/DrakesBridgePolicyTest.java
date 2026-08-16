package com.magmaguy.elitemobs.integrations.drakes;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DrakesBridgePolicyTest {

    private final DrakesBridgePolicy policy = new DrakesBridgePolicy(
            Set.of("DrakesBosses"), Set.of("boss_arena"), Set.of("garou.yml"));

    @Test
    void allowsOnlyExplicitOwnerWorldAndTemplate() {
        assertTrue(policy.allowsSpawn("drakesbosses", "boss_arena_4", "GAROU.YML"));
        assertFalse(policy.allowsSpawn("unknown", "boss_arena_4", "garou.yml"));
        assertFalse(policy.allowsSpawn("DrakesBosses", "world", "garou.yml"));
        assertFalse(policy.allowsSpawn("DrakesBosses", "boss_arena_4", "zeus.yml"));
    }

    @Test
    void emptyTemplateListFailsClosed() {
        DrakesBridgePolicy closed = new DrakesBridgePolicy(Set.of("DrakesBosses"), Set.of("boss_arena"), Set.of());
        assertFalse(closed.allowsSpawn("DrakesBosses", "boss_arena", "garou.yml"));
    }
}
