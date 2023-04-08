package dev.mayaqq.boomrun.mixin;

import dev.mayaqq.boomrun.EntityRunAwayExtension;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.CreeperIgniteGoal;
import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(CreeperIgniteGoal.class)
public class CreeperIgniteGoalMixin {
    @Final
    @Shadow private CreeperEntity creeper;
    @Shadow private LivingEntity target;

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    private void tick(CallbackInfo info) {
        if (this.target == null) {
            this.creeper.setFuseSpeed(-1);
        } else if (this.creeper.squaredDistanceTo(this.target) > 49.0) {
            this.creeper.setFuseSpeed(-1);
        } else if (!this.creeper.getVisibilityCache().canSee(this.target)) {
            this.creeper.setFuseSpeed(-1);
        } else {
            this.creeper.setFuseSpeed(1);
            //get all entities in a 5 block radius
            //if any of them are players, set fuse speed to 0
            this.creeper.world.getEntitiesByClass(LivingEntity.class, this.creeper.getBoundingBox().expand(5), Objects::nonNull).forEach((entity) -> {
                if (entity != null && !(entity instanceof CreeperEntity)) {
                    EntityRunAwayExtension entity1 = (EntityRunAwayExtension) entity;
                    entity1.runAway(this.creeper);
                }
            });
        }
        info.cancel();
    }
}
