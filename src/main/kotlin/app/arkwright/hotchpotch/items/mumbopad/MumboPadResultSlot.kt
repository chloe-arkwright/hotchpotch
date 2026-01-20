package app.arkwright.hotchpotch.items.mumbopad

import net.minecraft.world.Container
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.CraftingContainer
import net.minecraft.world.inventory.RecipeCraftingHolder
import net.minecraft.world.inventory.ResultSlot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.CraftingRecipe
import java.util.function.Supplier

class MumboPadResultSlot(val player: Player, val craftSlots: CraftingContainer, container: Container, id: Int, x: Int, y: Int, val hand: Supplier<InteractionHand>) : ResultSlot(player, craftSlots, container, id, x, y) {
    override fun checkTakeAchievements(carried: ItemStack) {
        if (removeCount > 0) {
            val hand = hand.get()

            val holder = container as RecipeCraftingHolder
            val recipe = holder.recipeUsed?.value as? CraftingRecipe
            val result = recipe?.assemble(craftSlots.asCraftInput(), player.level().registryAccess())
            player.getItemInHand(hand).hurtAndBreak(removeCount / (result?.count ?: 1), player, hand)
        }

        super.checkTakeAchievements(carried)
    }
}