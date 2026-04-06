package app.arkwright.hotchpotch.datagen

import java.util.concurrent.CompletableFuture

import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.tags.IntrinsicHolderTagsProvider
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput

import app.arkwright.chloe.lib.holder
import app.arkwright.chloe.lib.registryKey
import app.arkwright.chloe.lib.tagKey
import app.arkwright.hotchpotch.Hotchpotch
import app.arkwright.hotchpotch.registration.ModBlocks
import app.arkwright.hotchpotch.registration.ModItems

class BlockTagGenerator(
	output: FabricPackOutput,
	registries: CompletableFuture<HolderLookup.Provider>
) : IntrinsicHolderTagsProvider<Block>(output, Registries.BLOCK, registries, { it.registryKey() }) {
	override fun addTags(registries: HolderLookup.Provider) {
		tag(BlockTags.STAIRS)
			.add(ModBlocks.DEEP_SLATE_STAIRS)

		tag(BlockTags.SLABS)
			.add(ModBlocks.DEEP_SLATE_SLAB)

		tag(BlockTags.WALLS)
			.add(ModBlocks.DEEP_SLATE_WALL)

		tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(ModBlocks.DEEP_SLATE_STAIRS)
			.add(ModBlocks.DEEP_SLATE_SLAB)
			.add(ModBlocks.DEEP_SLATE_WALL)
	}
}

class ItemTagGenerator(
	output: FabricPackOutput,
	registries: CompletableFuture<HolderLookup.Provider>
) : IntrinsicHolderTagsProvider<Item>(output, Registries.ITEM, registries, { it.holder().key() }) {
	override fun addTags(registries: HolderLookup.Provider) {
		val scythes = Registries.ITEM.tagKey(Hotchpotch.id("scythes"))

		tag(scythes)
			.add(*ModItems.SCYTHES)

		tag(ItemTags.MINING_ENCHANTABLE)
			.addTag(scythes)

		tag(ItemTags.MINING_LOOT_ENCHANTABLE)
			.addTag(scythes)

		tag(ItemTags.DURABILITY_ENCHANTABLE)
			.addTag(scythes)
			.add(ModItems.MUMBO_PAD)

		tag(ItemTags.STAIRS)
			.add(ModItems.DEEP_SLATE_STAIRS)

		tag(ItemTags.SLABS)
			.add(ModItems.DEEP_SLATE_SLAB)

		tag(ItemTags.WALLS)
			.add(ModItems.DEEP_SLATE_WALL)
	}
}
