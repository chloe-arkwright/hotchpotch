package app.arkwright.hotchpotch.registration

import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.WallBlock
import net.minecraft.world.level.block.state.BlockBehaviour

import app.arkwright.chloe.lib.register
import app.arkwright.chloe.lib.registration.BlockRegistration
import app.arkwright.hotchpotch.Hotchpotch

object ModBlocks : BlockRegistration(Hotchpotch.MOD_ID) {
	val DEEP_SLATE_STAIRS = block(
		"deep_slate_stairs",
		BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
	) { StairBlock(Blocks.DEEPSLATE.defaultBlockState(), it) }

	val DEEP_SLATE_SLAB = block(
		"deep_slate_slab",
		BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE),
		::SlabBlock
	)

	val DEEP_SLATE_WALL = block(
		"deep_slate_wall",
		BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE),
		::WallBlock
	)

	override fun init() {
		DEEP_SLATE_STAIRS.register()
		DEEP_SLATE_SLAB.register()
		DEEP_SLATE_WALL.register()
	}
}
