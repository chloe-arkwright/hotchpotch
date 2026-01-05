package q1d7n.chloearkwright.hotchpotch.datagen

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.model.ModelTemplates
import q1d7n.chloearkwright.hotchpotch.registration.ModItems

class ModelGenerator(output: FabricPackOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockModelGenerators) {

    }

    override fun generateItemModels(generator: ItemModelGenerators) {
        generator.generateFlatItem(ModItems.MINI_COAL, ModelTemplates.FLAT_ITEM)
        generator.generateFlatItem(ModItems.MINI_CHARCOAL, ModelTemplates.FLAT_ITEM)
    }
}