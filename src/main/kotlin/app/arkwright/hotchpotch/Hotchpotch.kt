package app.arkwright.hotchpotch

import net.fabricmc.api.ModInitializer
import net.minecraft.resources.Identifier
import org.apache.logging.log4j.LogManager
import app.arkwright.hotchpotch.registration.ModGameRules
import app.arkwright.hotchpotch.registration.ModItems

internal object Hotchpotch : ModInitializer {
	internal const val MOD_ID: String = "hotchpotch"
    internal val LOGGER = LogManager.getLogger(MOD_ID)

	override fun onInitialize() {
		ModItems.init()
		ModGameRules.init()

		LeafDecayManager.init()
	}

	internal fun id(path: String) = Identifier.fromNamespaceAndPath(MOD_ID, path)
}