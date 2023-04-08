package dev.mayaqq.boomrun;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.PathAwareEntity;

import java.util.function.Predicate;

public class FleeExplodingCreeperGoal extends FleeEntityGoal<CreeperEntity> {
    public FleeExplodingCreeperGoal(PathAwareEntity mob, Class<CreeperEntity> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
        super(mob, fleeFromType, distance, slowSpeed, fastSpeed);
    }

    public FleeExplodingCreeperGoal(PathAwareEntity mob, Class<CreeperEntity> fleeFromType, Predicate<LivingEntity> extraInclusionSelector, float distance, double slowSpeed, double fastSpeed, Predicate<LivingEntity> inclusionSelector) {
        super(mob, fleeFromType, extraInclusionSelector, distance, slowSpeed, fastSpeed, inclusionSelector);
    }

    public FleeExplodingCreeperGoal(PathAwareEntity fleeingEntity, Class<CreeperEntity> classToFleeFrom, float fleeDistance, double fleeSlowSpeed, double fleeFastSpeed, Predicate<LivingEntity> inclusionSelector) {
        super(fleeingEntity, classToFleeFrom, fleeDistance, fleeSlowSpeed, fleeFastSpeed, inclusionSelector);
    }

    @Override
    public boolean canStart() {
        CreeperEntity creeper = this.classToFleeFrom.cast(CreeperEntity.class);
        return super.canStart() && creeper.getFuseSpeed() > 0;
    }
}
