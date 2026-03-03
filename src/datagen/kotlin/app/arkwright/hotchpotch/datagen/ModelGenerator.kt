package app.arkwright.hotchpotch.datagen

import java.util.Optional

import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.BlockModelGenerators.plainVariant
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator
import net.minecraft.client.data.models.model.ModelTemplate
import net.minecraft.client.data.models.model.ModelTemplates
import net.minecraft.client.data.models.model.TextureMapping
import net.minecraft.client.data.models.model.TextureSlot
import net.minecraft.world.level.block.Blocks

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput

import app.arkwright.hotchpotch.Hotchpotch
import app.arkwright.hotchpotch.registration.ModBlocks
import app.arkwright.hotchpotch.registration.ModItems

class ModelGenerator(output: FabricPackOutput) : FabricModelProvider(output) {
	private val fracturedBlockTemplate = ModelTemplate(
		Optional.of(Hotchpotch.id("block/template_fractured_block")),
		Optional.empty(),
		TextureSlot.ALL
	)

	override fun generateBlockStateModels(generator: BlockModelGenerators) {
		val fracturedStone = fracturedBlockTemplate.create(
			ModBlocks.FRACTURED_STONE,
			TextureMapping.cube(Blocks.STONE),
			generator.modelOutput
		)

		generator.blockStateOutput.accept(
			BlockModelGenerators.createSimpleBlock(ModBlocks.FRACTURED_STONE, plainVariant(fracturedStone))
		)

		generator.registerSimpleItemModel(ModBlocks.FRACTURED_STONE, fracturedStone)
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
