package q1d7n.chloearkwright.hotchpotch.items

import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.item.Item
import net.minecraft.world.level.Level
import q1d7n.chloearkwright.hotchpotch.items.mumbopad.MumboPadMenu

class MumboPadItem(properties: Properties) : Item(properties) {
    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
        if (!level.isClientSide) {
            player.openMenu(SimpleMenuProvider(
                { id, inventory, _ ->
                    MumboPadMenu(
                        id,
                        inventory,
                        ContainerLevelAccess.create(level, BlockPos.ZERO),
                        hand
                    )
                },
                name
            ))
        }

        return InteractionResult.SUCCESS
    }
}