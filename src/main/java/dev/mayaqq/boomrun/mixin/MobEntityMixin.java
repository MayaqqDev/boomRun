package dev.mayaqq.boomrun.mixin;

import dev.mayaqq.boomrun.goals.FleeExplodingCreeperGoal;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.EntityType;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @Final
    @Shadow
    protected GoalSelector goalSelector;
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstructed(EntityType entityType, World world, CallbackInfo ci) {
        if (world != null && !world.isClient) {
            initCreeperGoals();
        }
    }
    private void initCreeperGoals() {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity instanceof CreeperEntity) return;
        if (!(entity instanceof PathAwareEntity)) return;
        PathAwareEntity mob = (PathAwareEntity) (Object) this;
        goalSelector.add(1, new FleeExplodingCreeperGoal(mob, CreeperEntity.class, 6.0F, 2.0, 2.0));
    }
}
