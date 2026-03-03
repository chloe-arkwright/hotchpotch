package app.arkwright.hotchpotch.registration

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.ToolMaterial
import net.minecraft.world.level.block.Block

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents
import net.fabricmc.fabric.api.registry.FuelValueEvents

import app.arkwright.hotchpotch.Hotchpotch
import app.arkwright.hotchpotch.items.MumboPadItem
import app.arkwright.hotchpotch.items.ScytheItem

object ModItems {
	val MINI_COAL = register("mini_coal")
	val MINI_CHARCOAL = register("mini_charcoal")

	val WOODEN_SCYTHE = register("wooden_scythe") { ScytheItem(ToolMaterial.WOOD, 0.0F, -3.0F, 5, it) }
	val STONE_SCYTHE = register("stone_scythe") { ScytheItem(ToolMaterial.STONE, -1.0F, -2.0F, 8, it) }
	val COPPER_SCYTHE = register("copper_scythe") { ScytheItem(ToolMaterial.COPPER, -2.0F, -1.0F, 10, it) }
	val IRON_SCYTHE = register("iron_scythe") { ScytheItem(ToolMaterial.IRON, -2.0F, -1.0F, 16, it) }
	val GOLDEN_SCYTHE = register("golden_scythe") { ScytheItem(ToolMaterial.GOLD, 0.0F, -3.0F, 10, it) }
	val DIAMOND_SCYTHE = register("diamond_scythe") { ScytheItem(ToolMaterial.DIAMOND, -3.0F, 0.0F, 20, it) }
	val NETHERITE_SCYTHE = register("netherite_scythe", Item.Properties().fireResistant()) {
		ScytheItem(ToolMaterial.NETHERITE, -4.0F, 0.0F, 40, it)
	}

	val MUMBO_PAD = register("crafting_pad", Item.Properties().stacksTo(1).durability(250), ::MumboPadItem)

	val FRACTURED_STONE = blockItem(ModBlocks.FRACTURED_STONE)

	fun init() {
		FuelValueEvents.BUILD.register { builder, context ->
			builder.add(MINI_COAL, context.baseSmeltTime())
			builder.add(MINI_CHARCOAL, context.baseSmeltTime())
		}

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register { entries ->
			entries.insertBefore(Items.COAL, MINI_COAL, MINI_CHARCOAL)
		}

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register { entries ->
			entries.insertAfter(Items.WOODEN_HOE, WOODEN_SCYTHE)
			entries.insertAfter(Items.STONE_HOE, STONE_SCYTHE)
			entries.insertAfter(Items.COPPER_HOE, COPPER_SCYTHE)
			entries.insertAfter(Items.IRON_HOE, IRON_SCYTHE)
			entries.insertAfter(Items.GOLDEN_HOE, GOLDEN_SCYTHE)
			entries.insertAfter(Items.DIAMOND_HOE, DIAMOND_SCYTHE)
			entries.insertAfter(Items.NETHERITE_HOE, NETHERITE_SCYTHE)
			entries.accept(MUMBO_PAD)
		}
	}

	private fun blockItem(block: Block): BlockItem {
		return register(BuiltInRegistries.BLOCK.getKey(block), Item.Properties().useBlockDescriptionPrefix()) { BlockItem(block, it) }
	}

	private fun register(name: String, properties: Item.Properties = Item.Properties(), item: (Item.Properties) -> Item = ::Item): Item {
		return register(Hotchpotch.id(name), properties, item)
	}

	private fun <T: Item> register(id: Identifier, properties: Item.Properties = Item.Properties(), item: (Item.Properties) -> T): T {
		val key = ResourceKey.create(Registries.ITEM, id)

		return Registry.register(
			BuiltInRegistries.ITEM,
			key,
			item(properties.setId(key))
		)
	}


}
