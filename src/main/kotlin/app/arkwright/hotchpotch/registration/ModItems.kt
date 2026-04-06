package app.arkwright.hotchpotch.registration

import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.ToolMaterial

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents
import net.fabricmc.fabric.api.registry.FuelValueEvents

import app.arkwright.chloe.lib.register
import app.arkwright.chloe.lib.registration.ItemRegistration
import app.arkwright.hotchpotch.Hotchpotch
import app.arkwright.hotchpotch.items.MumboPadItem
import app.arkwright.hotchpotch.items.ScytheItem

object ModItems : ItemRegistration(Hotchpotch.MOD_ID) {
	val MINI_COAL = item("mini_coal")
	val MINI_CHARCOAL = item("mini_charcoal")

	val WOODEN_SCYTHE = item("wooden_scythe") { ScytheItem(ToolMaterial.WOOD, 0.0F, -3.0F, 5, it) }
	val STONE_SCYTHE = item("stone_scythe") { ScytheItem(ToolMaterial.STONE, -1.0F, -2.0F, 8, it) }
	val COPPER_SCYTHE = item("copper_scythe") { ScytheItem(ToolMaterial.COPPER, -2.0F, -1.0F, 10, it) }
	val IRON_SCYTHE = item("iron_scythe") { ScytheItem(ToolMaterial.IRON, -2.0F, -1.0F, 16, it) }
	val GOLDEN_SCYTHE = item("golden_scythe") { ScytheItem(ToolMaterial.GOLD, 0.0F, -3.0F, 10, it) }
	val DIAMOND_SCYTHE = item("diamond_scythe") { ScytheItem(ToolMaterial.DIAMOND, -3.0F, 0.0F, 20, it) }
	val NETHERITE_SCYTHE = item("netherite_scythe", Properties().fireResistant()) {
		ScytheItem(ToolMaterial.NETHERITE, -4.0F, 0.0F, 40, it)
	}

	val SCYTHES = arrayOf(WOODEN_SCYTHE, STONE_SCYTHE, COPPER_SCYTHE, IRON_SCYTHE, GOLDEN_SCYTHE, DIAMOND_SCYTHE, NETHERITE_SCYTHE)

	val MUMBO_PAD = item("crafting_pad", Properties().stacksTo(1).durability(250), ::MumboPadItem)

	val DEEP_SLATE_STAIRS = block(ModBlocks.DEEP_SLATE_STAIRS)
	val DEEP_SLATE_SLAB = block(ModBlocks.DEEP_SLATE_SLAB)
	val DEEP_SLATE_WALL = block(ModBlocks.DEEP_SLATE_WALL)

	override fun init() {
		MINI_COAL.register()
		MINI_CHARCOAL.register()
		SCYTHES.forEach(Item::register)
		MUMBO_PAD.register()
		DEEP_SLATE_STAIRS.register()
		DEEP_SLATE_SLAB.register()
		DEEP_SLATE_WALL.register()

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

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register { entries ->
			entries.insertAfter(Items.DEEPSLATE, DEEP_SLATE_STAIRS, DEEP_SLATE_SLAB, DEEP_SLATE_WALL)
		}
	}

	private fun item(name: String, properties: Item.Properties = Item.Properties()): Item {
		return item(name, properties, ::Item)
	}
}
