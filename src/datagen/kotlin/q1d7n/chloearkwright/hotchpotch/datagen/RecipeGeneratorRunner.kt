package q1d7n.chloearkwright.hotchpotch.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.world.item.Items
import q1d7n.chloearkwright.hotchpotch.Hotchpotch
import q1d7n.chloearkwright.hotchpotch.registration.ModItems
import java.util.concurrent.CompletableFuture

class RecipeGeneratorRunner(
    output: FabricPackOutput,
    registries: CompletableFuture<HolderLookup.Provider>
) : FabricRecipeProvider(output, registries) {
    override fun createRecipeProvider(registries: HolderLookup.Provider, output: RecipeOutput): RecipeProvider {
       return RecipeGenerator(registries, output)
    }

    override fun getName(): String = "Recipes"
}

class RecipeGenerator(registries: HolderLookup.Provider, output: RecipeOutput) : RecipeProvider(registries, output) {
    override fun buildRecipes() {
        shapeless(RecipeCategory.MISC, ModItems.MINI_COAL, 8)
            .requires(Items.COAL)
            .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
            .save(output)

        shapeless(RecipeCategory.MISC, ModItems.MINI_CHARCOAL, 8)
            .requires(Items.CHARCOAL)
            .unlockedBy(getHasName(Items.CHARCOAL), has(Items.CHARCOAL))
            .save(output)

        shaped(RecipeCategory.MISC, Items.COAL)
            .pattern("MMM")
            .pattern("M M")
            .pattern("MMM")
            .define('M', ModItems.MINI_COAL)
            .unlockedBy(getHasName(ModItems.MINI_COAL), has(ModItems.MINI_COAL))
            .save(output, "${Hotchpotch.MOD_ID}:coal_from_mini_coal")

        shaped(RecipeCategory.MISC, Items.CHARCOAL)
            .pattern("MMM")
            .pattern("M M")
            .pattern("MMM")
            .define('M', ModItems.MINI_CHARCOAL)
            .unlockedBy(getHasName(ModItems.MINI_CHARCOAL), has(ModItems.MINI_CHARCOAL))
            .save(output, "${Hotchpotch.MOD_ID}:charcoal_from_mini_charcoal")
    }
}