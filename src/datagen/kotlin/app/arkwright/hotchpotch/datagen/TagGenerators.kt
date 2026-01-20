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

class BlockTagGenerator(output: FabricPackOutput, registries: CompletableFuture<HolderLookup.Provider>
) : IntrinsicHolderTagsProvider<Block>(output, Registries.BLOCK, registries, { it.builtInRegistryHolder().key() }) {
    override fun addTags(registries: HolderLookup.Provider) {

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
    }
}