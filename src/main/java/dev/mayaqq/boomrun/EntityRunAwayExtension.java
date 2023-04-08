package dev.mayaqq.boomrun;

import net.minecraft.entity.LivingEntity;

public interface EntityRunAwayExtension {
    default void runAway(LivingEntity target) {
    }
}
