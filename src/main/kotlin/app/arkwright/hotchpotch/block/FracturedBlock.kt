package app.arkwright.hotchpotch.block

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.util.ColorRGBA
import net.minecraft.world.entity.item.FallingBlockEntity
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.FallingBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

class FracturedBlock(
	val base: Block,
	properties: Properties,
) : FallingBlock(properties) {
	override fun codec(): MapCodec<out FallingBlock> = CODEC

	override fun getDustColor(state: BlockState, level: BlockGetter, pos: BlockPos) = base.defaultMapColor().col

	override fun onLand(level: Level, pos: BlockPos, state: BlockState, replacedBlock: BlockState, entity: FallingBlockEntity) {
		level.setBlock(pos, base.defaultBlockState(), UPDATE_ALL)
	}

	override fun getStateForPlacement(context: BlockPlaceContext): BlockState {
		if (!context.level.getBlockState(context.clickedPos.below()).isAir) {
			return base.defaultBlockState()
		}

		return this.defaultBlockState()
	}

	companion object {
		val CODEC: MapCodec<FracturedBlock> = RecordCodecBuilder.mapCodec { instance ->
			instance.group(
				Block.CODEC.fieldOf("base_block").forGetter(FracturedBlock::base),
				propertiesCodec()
			).apply(instance, ::FracturedBlock)
		}
	}
}
