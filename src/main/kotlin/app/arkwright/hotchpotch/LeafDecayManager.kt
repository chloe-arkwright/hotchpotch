package app.arkwright.hotchpotch

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.gamerule.v1.GameRuleEvents
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.Level
import app.arkwright.hotchpotch.registration.ModGameRules

object LeafDecayManager {
     val decayingLeaves = mutableListOf<Triple<Int, BlockPos, ResourceKey<Level>>>()

    fun init() {
        GameRuleEvents.changeCallback(ModGameRules.FAST_LEAF_DECAY).register { value, _ ->
            if (!value) {
                decayingLeaves.clear()
            }
        }

        ServerTickEvents.END_SERVER_TICK.register { server ->
            while (decayingLeaves.isNotEmpty()) {
                val leaf = decayingLeaves.first()

                if (leaf.first > server.tickCount) {
                    break
                }

                val dimension = server.getLevel(leaf.third)!!
                val state = dimension.getBlockState(leaf.second)

                state.randomTick(dimension, leaf.second, dimension.random)

                decayingLeaves.removeFirst()
            }
        }
    }

    private fun isAlreadyRegistered(pos: BlockPos): Boolean {
        return decayingLeaves.any { it.second == pos }
    }

    @JvmStatic
    fun submitLeafForDecay(pos: BlockPos, level: ServerLevel) {
        val decayTick = level.server.tickCount + level.random.nextInt(10, 100)

        if (isAlreadyRegistered(pos)) {
            return
        }

        for ((index, pair) in decayingLeaves.withIndex()) {
            if (decayTick <= pair.first) {
                decayingLeaves.add(index, Triple(decayTick, pos, level.dimension()))
                return
            }
        }

        decayingLeaves.addLast(Triple(decayTick, pos, level.dimension()))
    }
}