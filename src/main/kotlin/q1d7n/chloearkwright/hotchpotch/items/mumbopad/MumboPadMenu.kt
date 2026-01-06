package q1d7n.chloearkwright.hotchpotch.items.mumbopad

import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.inventory.CraftingMenu
import net.minecraft.world.inventory.Slot
import q1d7n.chloearkwright.hotchpotch.registration.ModItems

class MumboPadMenu(containerId: Int, inventory: Inventory, access: ContainerLevelAccess, val hand: InteractionHand) : CraftingMenu(containerId, inventory, access) {
    override fun addResultSlot(player: Player, x: Int, y: Int): Slot {
        return addSlot(MumboPadResultSlot(player, craftSlots, resultSlots, 0, x, y, this::getUsedHand))
    }

    override fun stillValid(player: Player): Boolean {
        return player.getItemInHand(hand).`is`(ModItems.MUMBO_PAD)
    }

    private fun getUsedHand(): InteractionHand = hand
}