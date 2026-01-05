package q1d7n.chloearkwright.hotchpotch.registration

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import q1d7n.chloearkwright.hotchpotch.Hotchpotch

object ModItems {
    val MINI_COAL = register("mini_coal")
    val MINI_CHARCOAL = register("mini_charcoal")

    fun init() {
        // Just for class loading so the register methods get called.
    }

    private fun register(name: String, properties: Item.Properties = Item.Properties(), item: (Item.Properties) -> Item = ::Item): Item {
        val key = ResourceKey.create(Registries.ITEM, Hotchpotch.id(name))

        return Registry.register(
            BuiltInRegistries.ITEM,
            key,
            item(properties.setId(key))
        )
    }
}