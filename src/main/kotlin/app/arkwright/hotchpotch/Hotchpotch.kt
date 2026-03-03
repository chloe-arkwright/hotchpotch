package app.arkwright.hotchpotch

import org.apache.logging.log4j.LogManager

import net.minecraft.resources.Identifier
import net.minecraft.world.level.block.Blocks

import net.fabricmc.api.ModInitializer

import app.arkwright.hotchpotch.registration.ModBlocks
import app.arkwright.hotchpotch.registration.ModGameRules
import app.arkwright.hotchpotch.registration.ModItems

internal object Hotchpotch : ModInitializer {
	internal const val MOD_ID: String = "hotchpotch"
	internal val LOGGER = LogManager.getLogger(MOD_ID)

	override fun onInitialize() {
		ModBlocks.init()
		ModItems.init()
		ModGameRules.init()

		LeafDecayManager.init()
	}

	internal fun id(path: String) = Identifier.fromNamespaceAndPath(MOD_ID, path)
}
