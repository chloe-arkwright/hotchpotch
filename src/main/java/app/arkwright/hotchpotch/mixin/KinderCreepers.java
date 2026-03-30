package app.arkwright.hotchpotch.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Creeper.class)
public class KinderCreepers {
    @Shadow
    private int maxSwell;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void hotchpotch$updateExplosionDelay(EntityType<? extends @NotNull Creeper> type, Level level, CallbackInfo ci) {
//        maxSwell = 45;
    }
}
