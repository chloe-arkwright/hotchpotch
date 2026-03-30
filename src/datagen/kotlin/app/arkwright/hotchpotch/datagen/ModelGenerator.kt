package app.arkwright.hotchpotch.datagen

import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.model.ModelTemplates
import net.minecraft.world.level.block.Blocks

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import app.arkwright.hotchpotch.registration.ModBlocks

import app.arkwright.hotchpotch.registration.ModItems

class ModelGenerator(output: FabricPackOutput) : FabricModelProvider(output) {
	override fun generateBlockStateModels(generator: BlockModelGenerators) {
		val deepslateFamilyProvider = generator.family(Blocks.DEEPSLATE)

		deepslateFamilyProvider.stairs(ModBlocks.DEEP_SLATE_STAIRS)
		deepslateFamilyProvider.slab(ModBlocks.DEEP_SLATE_SLAB)
		deepslateFamilyProvider.wall(ModBlocks.DEEP_SLATE_WALL)
	}

	override fun generateItemModels(generator: ItemModelGenerators) {
		generator.generateFlatItem(ModItems.MINI_COAL, ModelTemplates.FLAT_ITEM)
		generator.generateFlatItem(ModItems.MINI_CHARCOAL, ModelTemplates.FLAT_ITEM)

		generator.generateFlatItem(ModItems.WOODEN_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM)
		generator.generateFlatItem(ModItems.STONE_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM)
		generator.generateFlatItem(ModItems.COPPER_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM)
		generator.generateFlatItem(ModItems.IRON_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM)
		generator.generateFlatItem(ModItems.GOLDEN_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM)
		generator.generateFlatItem(ModItems.DIAMOND_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM)
		generator.generateFlatItem(ModItems.NETHERITE_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM)

		generator.generateFlatItem(ModItems.MUMBO_PAD, ModelTemplates.FLAT_ITEM)
	}
}
