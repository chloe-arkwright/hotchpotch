package q1d7n.chloearkwright.hotchpotch.items

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.stats.Stats
import net.minecraft.tags.ItemTags
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ToolMaterial
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CropBlock
import kotlin.math.max
import kotlin.math.min

class ScytheItem(
    toolMaterial: ToolMaterial,
    attackDamageBaseline: Float,
    attackSpeedBaseline: Float,
    val range: Int,
    properties: Properties
) : Item(properties.hoe(toolMaterial, attackDamageBaseline, attackSpeedBaseline)) {

    override fun useOn(context: UseOnContext): InteractionResult {
        val level = context.level
        val state = context.level.getBlockState(context.clickedPos)
        val block = state.block

        if (block is CropBlock && block.isMaxAge(state)) {
            if (level !is ServerLevel) {
                return InteractionResult.CONSUME
            }

            val handStack = context.itemInHand
            val player = context.player as? ServerPlayer
            val drops = mutableMapOf<Item, Int>()
            val maxBlocks = min(handStack.maxDamage - handStack.damageValue, range)

            val blocksHarvested = BlockPos.breadthFirstTraversal(context.clickedPos, Int.MAX_VALUE, maxBlocks, { pos, consumer ->
                for (direction in Direction.Plane.HORIZONTAL) {
                    val newPos = pos.relative(direction)
                    val newState = level.getBlockState(newPos)

                    if (newState == state) {
                        consumer.accept(newPos)
                    }
                }
            }) {
                val state = level.getBlockState(it)
                val entity = level.getBlockEntity(it)

                if (level.setBlock(it, block.getStateForAge(0), Block.UPDATE_ALL)) {
                    if (player != null) {
                        for (stack in Block.getDrops(state, level, it, entity, player, handStack)) {
                            drops.merge(stack.item, stack.count) { old, new -> old + new }
                        }
                        state.spawnAfterBreak(level, it, handStack, true)
                    } else {
                        for (stack in Block.getDrops(state, level, it, entity, null, handStack)) {
                            drops.merge(stack.item, stack.count) { old, new -> old + new }
                        }
                        state.spawnAfterBreak(level, it, handStack, true)
                    }
                }

                return@breadthFirstTraversal BlockPos.TraversalNodeStatus.ACCEPT
            }

            val dropPos = (player?.onPos ?: context.clickedPos).above()

            player?.awardStat(Stats.BLOCK_MINED.get(state.block), blocksHarvested)

            for ((item, count) in drops) {
                var count = if (item.builtInRegistryHolder().`is`(ItemTags.VILLAGER_PLANTABLE_SEEDS)) {
                    max(1, (0.75 * count).toInt())
                } else {
                    count
                }

                while (count > 0) {
                    val dropAmount = count.coerceAtMost(64)

                    Block.popResource(level, dropPos, ItemStack(item, dropAmount))

                    count -= dropAmount
                }
            }

            if (player == null || !player.hasInfiniteMaterials()) {
                handStack.hurtAndBreak(blocksHarvested, level, null) {
                    player?.onEquippedItemBroken(handStack.item, context.hand.asEquipmentSlot())
                }
            }

            return InteractionResult.SUCCESS_SERVER
        }

        return super.useOn(context)
    }
}