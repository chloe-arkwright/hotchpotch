package app.arkwright.hotchpotch.registration

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour

import app.arkwright.hotchpotch.Hotchpotch
import app.arkwright.hotchpotch.block.FracturedBlock

object ModBlocks {
	val FRACTURED_DIRT = register(
		"fractured_dirt",
		{ properties -> FracturedBlock(Blocks.DIRT, properties) },
		BlockBehaviour.Properties.of()
	)

	val FRACTURED_CLAY = register(
		"fractured_clay",
		{ properties -> FracturedBlock(Blocks.CLAY, properties) },
		BlockBehaviour.Properties.of()
	)

	val FRACTURED_STONE = register(
		"fractured_stone",
		{ properties -> FracturedBlock(Blocks.STONE, properties) },
		BlockBehaviour.Properties.of()
	)

	val FRACTURED_ANDESITE = register(
		"fractured_andesite",
		{ properties -> FracturedBlock(Blocks.ANDESITE, properties) },
		BlockBehaviour.Properties.of()
	)

	val FRACTURED_DIORITE = register(
		"fractured_diorite",
		{ properties -> FracturedBlock(Blocks.DIORITE, properties) },
		BlockBehaviour.Properties.of()
	)

	val FRACTURED_GRANITE = register(
		"fractured_grantite",
		{ properties -> FracturedBlock(Blocks.GRANITE, properties) },
		BlockBehaviour.Properties.of()
	)

	val FRACTURED_NETHERRACK = register(
		"fractured_netherrack",
		{ properties -> FracturedBlock(Blocks.NETHERRACK, properties) },
		BlockBehaviour.Properties.of()
	)

	private fun <T : Block> register(
		name: String,
		block: (BlockBehaviour.Properties) -> T,
		properties: BlockBehaviour.Properties
	): T {
		val id = ResourceKey.create(Registries.BLOCK, Hotchpotch.id(name))

		return Registry.register(
			BuiltInRegistries.BLOCK,
			id,
			block(
				properties.setId(id)
					.isViewBlocking(Blocks::never)
			)
		)
	}

	fun init() {
	}
}
