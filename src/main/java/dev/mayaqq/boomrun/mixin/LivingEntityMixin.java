package dev.mayaqq.boomrun.mixin;

import dev.mayaqq.boomrun.EntityRunAwayExtension;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.ai.pathing.Path;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements EntityRunAwayExtension {
    @Override
    public void runAway(LivingEntity target) {
        LivingEntity entityCheck = (LivingEntity) (Object) this;
        if (!(entityCheck instanceof PathAwareEntity)) {
            return;
        }
        PathAwareEntity entity = (PathAwareEntity) (Object) this;
        BlockPos location = target.getBlockPos();
        double dx = entity.getX() - location.getX();
        double dz = entity.getZ() - location.getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);
        dx /= dist;
        dz /= dist;

        double newX = entity.getX() + dx * 5;
        double newZ = entity.getZ() + dz * 5;

        Path path = entity.getNavigation().findPathTo(new BlockPos((int) newX, (int) entity.getY(),(int) newZ), 0);

        entity.getNavigation().startMovingAlong(path, 2D);
    }
}
