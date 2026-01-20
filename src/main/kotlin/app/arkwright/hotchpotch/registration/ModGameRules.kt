package app.arkwright.hotchpotch.registration

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder
import net.minecraft.world.level.gamerules.GameRule
import net.minecraft.world.level.gamerules.GameRuleCategory
import app.arkwright.hotchpotch.Hotchpotch

object ModGameRules {
//    @JvmField
//    val CREEPER_EXPLOSION_DELAY: GameRule<Int> = GameRuleBuilder.forInteger(30)
//    .minValue(0)
//    .category(GameRuleCategory.MOBS)
//    .buildAndRegister(Hotchpotch.id("creeper_explosion_delay"))

    @JvmField
	val FAST_LEAF_DECAY: GameRule<Boolean> = GameRuleBuilder.forBoolean(false)
		.category(GameRuleCategory.UPDATES)
		.buildAndRegister(Hotchpotch.id("fast_leaf_decay"))

    fun init() {
        // Just for class loading so the register methods get called.
    }
}