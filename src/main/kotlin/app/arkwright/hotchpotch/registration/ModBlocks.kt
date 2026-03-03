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
	val FRACTURED_STONE = register(
		"fractured_stone",
		{ properties -> FracturedBlock(Blocks.STONE, properties) },
		BlockBehaviour.Properties.of()
	)

	private fun register(
		name: String,
		block: (BlockBehaviour.Properties) -> Block,
		properties: BlockBehaviour.Properties
	): Block {
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
