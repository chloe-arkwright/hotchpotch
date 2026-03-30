package app.arkwright.hotchpotch.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.tags.IntrinsicHolderTagsProvider
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import app.arkwright.hotchpotch.Hotchpotch
import app.arkwright.hotchpotch.registration.ModItems
import java.util.concurrent.CompletableFuture
import net.minecraft.tags.BlockTags
import app.arkwright.hotchpotch.registration.ModBlocks

class BlockTagGenerator(output: FabricPackOutput, registries: CompletableFuture<HolderLookup.Provider>
) : IntrinsicHolderTagsProvider<Block>(output, Registries.BLOCK, registries, { it.builtInRegistryHolder().key() }) {
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

class ItemTagGenerator(output: FabricPackOutput, registries: CompletableFuture<HolderLookup.Provider>
) : IntrinsicHolderTagsProvider<Item>(output, Registries.ITEM, registries, { it.builtInRegistryHolder().key() }) {
    override fun addTags(registries: HolderLookup.Provider) {
        val scythes = TagKey.create(Registries.ITEM, Hotchpotch.id("scythes"))

        tag(scythes)
            .add(ModItems.WOODEN_SCYTHE)
            .add(ModItems.STONE_SCYTHE)
            .add(ModItems.COPPER_SCYTHE)
            .add(ModItems.IRON_SCYTHE)
            .add(ModItems.GOLDEN_SCYTHE)
            .add(ModItems.DIAMOND_SCYTHE)
            .add(ModItems.NETHERITE_SCYTHE)

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
