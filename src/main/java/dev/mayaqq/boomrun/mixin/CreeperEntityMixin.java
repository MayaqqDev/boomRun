package dev.mayaqq.boomrun.mixin;

import dev.mayaqq.boomrun.EntityExtensions.CreeperEntityExtension;
import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin implements CreeperEntityExtension {
    @Shadow
    private int currentFuseTime;
    @Override
    public int getCurrentFuseTime() {
        return currentFuseTime;
    }
}