package q1d7n.chloearkwright.hotchpotch

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents
import net.fabricmc.fabric.api.registry.FuelValueEvents
import net.minecraft.resources.Identifier
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Items
import org.apache.logging.log4j.LogManager
import q1d7n.chloearkwright.hotchpotch.registration.ModGameRules
import q1d7n.chloearkwright.hotchpotch.registration.ModItems

internal object Hotchpotch : ModInitializer {
	internal const val MOD_ID: String = "hotchpotch"
    internal val LOGGER = LogManager.getLogger(MOD_ID)

	override fun onInitialize() {
		ModItems.init()
		ModGameRules.init()

		LeafDecayManager.init()

		FuelValueEvents.BUILD.register { builder, context ->
            builder.add(ModItems.MINI_COAL, context.baseSmeltTime())
            builder.add(ModItems.MINI_CHARCOAL, context.baseSmeltTime())
        }

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register { entries ->
            entries.insertAfter(Items.CHARCOAL, ModItems.MINI_COAL, ModItems.MINI_CHARCOAL)
        }
	}

	internal fun id(path: String) = Identifier.fromNamespaceAndPath(MOD_ID, path)
}