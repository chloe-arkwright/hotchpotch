package app.arkwright.hotchpotch.datagen

import java.util.concurrent.CompletableFuture

import net.minecraft.core.HolderLookup

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider

import app.arkwright.hotchpotch.registration.ModBlocks

class BlockLootProvider(
	output: FabricPackOutput,
	registries: CompletableFuture<HolderLookup.Provider>
) : FabricBlockLootSubProvider(output, registries) {
	override fun generate() {
		dropSelf(ModBlocks.DEEP_SLATE_STAIRS)
		dropSelf(ModBlocks.DEEP_SLATE_SLAB)
		dropSelf(ModBlocks.DEEP_SLATE_WALL)
	}
}
