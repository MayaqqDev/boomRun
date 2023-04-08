package dev.mayaqq.boomrun.mixin;

import dev.mayaqq.boomrun.EntityRunAwayExtension;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {
    @Inject(at = @At("TAIL"), method = "ignite")
    private void ignite(CallbackInfo info) {
        CreeperEntity creeper = (CreeperEntity) (Object) this;
        creeper.world.getEntitiesByClass(LivingEntity.class, creeper.getBoundingBox().expand(5), Objects::nonNull).forEach((entity) -> {
            if (entity != null && !(entity instanceof CreeperEntity)) {
                EntityRunAwayExtension entity1 = (EntityRunAwayExtension) entity;
                entity1.runAway(creeper);
            }
        });
    }
}
