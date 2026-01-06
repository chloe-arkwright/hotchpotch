package q1d7n.chloearkwright.hotchpotch.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.core.HolderLookup
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.tags.ItemTags
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

        shaped(RecipeCategory.TOOLS, ModItems.WOODEN_SCYTHE)
            .pattern("MMM")
            .pattern(" S ")
            .pattern("S  ")
            .define('M', ItemTags.WOODEN_TOOL_MATERIALS)
            .define('S', Items.STICK)
            .unlockedBy("has_planks", has(ItemTags.WOODEN_TOOL_MATERIALS))
            .save(output)

        shaped(RecipeCategory.TOOLS, ModItems.STONE_SCYTHE)
            .pattern("MMM")
            .pattern(" S ")
            .pattern("S  ")
            .define('M', ItemTags.STONE_TOOL_MATERIALS)
            .define('S', Items.STICK)
            .unlockedBy("has_cobblestone", has(ItemTags.STONE_TOOL_MATERIALS))
            .save(output)

        shaped(RecipeCategory.TOOLS, ModItems.COPPER_SCYTHE)
            .pattern("MMM")
            .pattern(" S ")
            .pattern("S  ")
            .define('M', ItemTags.COPPER_TOOL_MATERIALS)
            .define('S', Items.STICK)
            .unlockedBy("has_copper_ingot", has(ItemTags.COPPER_TOOL_MATERIALS))
            .save(output)

        shaped(RecipeCategory.TOOLS, ModItems.IRON_SCYTHE)
            .pattern("MMM")
            .pattern(" S ")
            .pattern("S  ")
            .define('M', ItemTags.IRON_TOOL_MATERIALS)
            .define('S', Items.STICK)
            .unlockedBy("has_iron_ingot", has(ItemTags.IRON_TOOL_MATERIALS))
            .save(output)

        shaped(RecipeCategory.TOOLS, ModItems.GOLDEN_SCYTHE)
            .pattern("MMM")
            .pattern(" S ")
            .pattern("S  ")
            .define('M', ItemTags.GOLD_TOOL_MATERIALS)
            .define('S', Items.STICK)
            .unlockedBy("has_gold_ingot", has(ItemTags.GOLD_TOOL_MATERIALS))
            .save(output)

        shaped(RecipeCategory.TOOLS, ModItems.DIAMOND_SCYTHE)
            .pattern("MMM")
            .pattern(" S ")
            .pattern("S  ")
            .define('M', ItemTags.DIAMOND_TOOL_MATERIALS)
            .define('S', Items.STICK)
            .unlockedBy("has_diamond", has(ItemTags.DIAMOND_TOOL_MATERIALS))
            .save(output)

        netheriteSmithing(ModItems.DIAMOND_SCYTHE, RecipeCategory.TOOLS, ModItems.NETHERITE_SCYTHE)

        shaped(RecipeCategory.TOOLS, ModItems.MUMBO_PAD)
            .pattern("NNN")
            .pattern("ICI")
            .pattern("NNN")
            .define('N', ConventionalItemTags.IRON_NUGGETS)
            .define('I', ConventionalItemTags.IRON_INGOTS)
            .define('C', Items.CRAFTER)
            .unlockedBy(getHasName(Items.CRAFTER), has(Items.CRAFTER))
            .save(output)
    }
}