package dev.mayaqq.boomrun.goals;

import dev.mayaqq.boomrun.entityExtensions.CreeperEntityExtension;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.PathAwareEntity;

public class FleeExplodingCreeperGoal extends FleeEntityGoal<CreeperEntity> {
    public FleeExplodingCreeperGoal(PathAwareEntity mob, Class<CreeperEntity> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
        super(mob, fleeFromType, distance, slowSpeed, fastSpeed);
    }

    @Override
    public boolean canStart() {
        return super.canStart() && ((CreeperEntityExtension)this.targetEntity).getCurrentFuseTime() > 0;
    }
}