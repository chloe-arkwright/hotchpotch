package app.arkwright.hotchpotch.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import app.arkwright.hotchpotch.LeafDecayManager;
import app.arkwright.hotchpotch.registration.ModGameRules;

@Mixin(LeavesBlock.class)
public abstract class LeavesDecayFaster {
    @Shadow
    protected abstract boolean decaying(BlockState state);

    @WrapOperation(
        method = "tick(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/LeavesBlock;updateDistance(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;"
        )
    )
    private BlockState hotchpotch$submitLeafForDecay(BlockState state, LevelAccessor level, BlockPos pos, Operation<BlockState> original) {
        BlockState newState = original.call(state, level, pos);

        if (
            level instanceof ServerLevel serverLevel &&
            serverLevel.getGameRules().get(ModGameRules.FAST_LEAF_DECAY) &&
            decaying(newState)
        ) {
            LeafDecayManager.submitLeafForDecay(pos, serverLevel);
        }

        return newState;
    }
}
