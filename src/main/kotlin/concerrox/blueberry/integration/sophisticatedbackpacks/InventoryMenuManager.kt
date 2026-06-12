package concerrox.blueberry.integration.sophisticatedbackpacks

import net.minecraft.client.gui.screens.inventory.InventoryScreen
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.InventoryMenu
import net.neoforged.neoforge.client.event.ScreenEvent
import net.neoforged.neoforge.items.SlotItemHandler
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContext
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider

object InventoryMenuManager {

    var menu2: InventoryMenu? = null

    fun onAddSlots(
        inventoryMenu: InventoryMenu, playerInventory: Inventory, owner: Player
    ) {
        menu2 = inventoryMenu
        PlayerInventoryProvider.get().runOnBackpacks(owner) { backpackStack, inventoryName, identifier, backpackSlot ->
            val backpackContext = BackpackContext.Item(inventoryName, identifier, backpackSlot)
            val storageWrapper = backpackContext.getBackpackWrapper(owner)
            val inventoryHandler = storageWrapper.inventoryHandler
            var slotIndex = 0
            println(backpackStack)
            inventoryHandler.let {
                inventoryMenu.addSlot(SlotItemHandler(it, 7, -16, -16))
            }
//            val noSortSlotIndexes: Set<Int> = getNoSortSlotIndexes()
//            while (slotIndex < inventoryHandler.slots) {
//                val finalSlotIndex = slotIndex
//                val slot =
////                val slot =
////                    object : StorageInventorySlot(player.level().isClientSide, storageWrapper, finalSlotIndex, player) {
////                        override fun set(@Nonnull stack: ItemStack) {
////                            super.set(stack)
////                            onStorageInventorySlotSet(finalSlotIndex)
////                        }
////
////                        override fun getNoItemIcon(): Pair<ResourceLocation, ResourceLocation>? {
////                            return if (inaccessibleSlots.contains(finalSlotIndex)) INACCESSIBLE_SLOT_BACKGROUND else emptySlotIcons.getOrDefault(
////                                finalSlotIndex,
////                                null
////                            )
////                        }
////
////                        override fun mayPlace(@Nonnull stack: ItemStack): Boolean {
////                            return !inaccessibleSlots.contains(finalSlotIndex) && super.mayPlace(stack)
////                        }
////
////                        override fun mayPickup(playerIn: Player): Boolean {
////                            return !inaccessibleSlots.contains(finalSlotIndex) && super.mayPickup(playerIn)
////                        }
////
////                        override fun getMaxStackSize(stack: ItemStack): Int {
////                            return if (slotLimitOverrides.containsKey(finalSlotIndex)) slotLimitOverrides.get(
////                                finalSlotIndex
////                            ) else super.getMaxStackSize(stack)
////                        }
////
////                        override fun getMaxStackSize(): Int {
////                            return if (slotLimitOverrides.containsKey(finalSlotIndex)) slotLimitOverrides.get(
////                                finalSlotIndex
////                            ) else super.getMaxStackSize()
////                        }
////                    }
////                if (noSortSlotIndexes.contains(slotIndex)) {
////                    addNoSortSlot(slot)
////                } else {
////                    addSlot(slot)
////                }
//                slotIndex++
//            }

            true
        }
    }

    fun onShown(event: ScreenEvent.Init, screen: InventoryScreen) {
        PlayerInventoryProvider.get().runOnBackpacks(screen.minecraft.player) { backpackStack, inventoryName, identifier, backpackSlot ->
            val backpackContext = BackpackContext.Item(inventoryName, identifier, backpackSlot)
            val storageWrapper = backpackContext.getBackpackWrapper(screen.minecraft.player)
            val inventoryHandler = storageWrapper.inventoryHandler
            var slotIndex = 0
            println(backpackStack)
            inventoryHandler.let {
                menu2?.addSlot(SlotItemHandler(it, 7, -16, -16))
            }
            true
        }
    }

}