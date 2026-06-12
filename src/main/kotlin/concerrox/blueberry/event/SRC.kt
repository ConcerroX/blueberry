package concerrox.blueberry.event

import concerrox.blueberry.Blueberry
import concerrox.blueberry.Ghost
import concerrox.blueberry.integration.sophisticatedbackpacks.InventoryMenuManager
import net.minecraft.client.gui.screens.inventory.InventoryScreen
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.ClientTickEvent
import net.neoforged.neoforge.client.event.ScreenEvent

@EventBusSubscriber(modid = Blueberry.MOD_ID, value = [Dist.CLIENT], bus = EventBusSubscriber.Bus.GAME)
object SRC {

    @SubscribeEvent
    fun onPostClientTick(event: ClientTickEvent.Post) {
        Ghost.doth()
    }

    @SubscribeEvent
    fun a(event: ScreenEvent.Init.Pre) {
        if (event.screen is InventoryScreen) {
            InventoryMenuManager.onShown(event, event.screen as InventoryScreen)
        }
    }

}