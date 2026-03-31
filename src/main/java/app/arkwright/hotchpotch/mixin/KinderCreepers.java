package app.arkwright.hotchpotch.mixin;

import app.arkwright.hotchpotch.registration.ModGameRules;

import com.llamalad7.mixinextras.sugar.Local;

import com.mojang.serialization.Codec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.storage.ValueInput;

import org.jspecify.annotations.NonNull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(Creeper.class)
public abstract class KinderCreepers extends Entity {
	@Shadow
	private int maxSwell;

	@Unique
	@SuppressWarnings("WrongEntityDataParameterClass")
	private static final EntityDataAccessor<Integer> DATA_MAX_SWELL = SynchedEntityData.defineId(
		Creeper.class,
		EntityDataSerializers.INT
	);

	@Inject(
		method = "readAdditionalSaveData(Lnet/minecraft/world/level/storage/ValueInput;)V",
		at = @At("TAIL")
	)
	private void hotchpotch$updateExplosionDelayOnDataRead(
		final ValueInput input,
		final CallbackInfo ci
	) {
		Optional<Short> fuse = input.read("Fuse", Codec.SHORT);

		if (fuse.isPresent()) {
			maxSwell = fuse.get();
		} else if (level() instanceof ServerLevel level) {
			maxSwell = level.getServer().getGameRules().get(ModGameRules.CREEPER_EXPLOSION_DELAY);
		}

		entityData.set(DATA_MAX_SWELL, maxSwell, true);
	}

	@Inject(
		method = "defineSynchedData(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V",
		at = @At("TAIL")
	)
	private void hotchpotch$syncExplosionDelay(
		final CallbackInfo ci,
		@Local(argsOnly = true)
		final SynchedEntityData.Builder entityData
	) {
		entityData.define(DATA_MAX_SWELL, maxSwell);
	}

	@Override
	public void onSyncedDataUpdated(
		@NonNull
		final List<SynchedEntityData.DataValue<?>> updatedItems
	) {
		super.onSyncedDataUpdated(updatedItems);

		for (SynchedEntityData.DataValue<?> updatedItem : updatedItems) {
			if (updatedItem.id() == DATA_MAX_SWELL.id()) {
				maxSwell = (int) updatedItem.value();
			}
		}
	}

	private KinderCreepers(final EntityType<?> type, final Level level) {
		super(type, level);
	}
}
