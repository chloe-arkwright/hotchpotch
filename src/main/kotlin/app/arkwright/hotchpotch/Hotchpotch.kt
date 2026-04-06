package app.arkwright.hotchpotch

import net.minecraft.resources.Identifier

import net.fabricmc.api.ModInitializer

import app.arkwright.chloe.lib.registration.Registration
import app.arkwright.hotchpotch.registration.ModBlocks
import app.arkwright.hotchpotch.registration.ModGameRules
import app.arkwright.hotchpotch.registration.ModItems

internal object Hotchpotch : ModInitializer {
	internal const val MOD_ID: String = "hotchpotch"

	override fun onInitialize() {
		listOf(
			ModBlocks,
			ModItems,
			ModGameRules
		).forEach(Registration<*>::init)

		LeafDecayManager.init()
	}

	internal fun id(path: String) = Identifier.fromNamespaceAndPath(MOD_ID, path)
}
