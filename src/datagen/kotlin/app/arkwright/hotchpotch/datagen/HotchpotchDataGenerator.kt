package app.arkwright.hotchpotch.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object HotchpotchDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
		with(generator.createPack()) {
			addProvider(::ModelGenerator)
			addProvider(::RecipeGeneratorRunner)
			addProvider(::BlockTagGenerator)
			addProvider(::ItemTagGenerator)
		}
	}
}