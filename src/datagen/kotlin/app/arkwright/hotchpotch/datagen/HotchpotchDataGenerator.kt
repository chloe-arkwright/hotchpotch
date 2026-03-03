package app.arkwright.hotchpotch.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

import app.arkwright.hotchpotch.Hotchpotch

object HotchpotchDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
		with(generator.createPack()) {
			addProvider(::ModelGenerator)
			addProvider(::RecipeGeneratorRunner)
			addProvider(::BlockTagGenerator)
			addProvider(::ItemTagGenerator)
		}
	}

	override fun getEffectiveModId(): String = Hotchpotch.MOD_ID
}
