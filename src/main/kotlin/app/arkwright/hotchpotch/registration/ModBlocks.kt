package app.arkwright.hotchpotch.registration

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.WallBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import app.arkwright.hotchpotch.Hotchpotch

object ModBlocks {
	val DEEP_SLATE_STAIRS = register(
		"deep_slate_stairs",
		BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
	) { StairBlock(Blocks.DEEPSLATE.defaultBlockState(), it) }

	val DEEP_SLATE_SLAB = register(
		"deep_slate_slab",
		BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE),
		::SlabBlock
	)

	val DEEP_SLATE_WALL = register(
		"deep_slate_wall",
		BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE),
		::WallBlock
	)

	private fun <T : Block> register(name: String, properties: BlockBehaviour.Properties = BlockBehaviour.Properties.of(), block: (BlockBehaviour.Properties) -> T): T {
		val key = ResourceKey.create(Registries.BLOCK, Hotchpotch.id(name))

		return Registry.register(BuiltInRegistries.BLOCK, key, block(properties.setId(key)))
	}

	// Class-loading hook
	internal fun init() = Unit
}
