package concerrox.blueberry.event

import com.simibubi.create.content.fluids.tankf.FluidTankRenderer
import concerrox.blueberry.Blueberry
import concerrox.blueberry.Ghost
import concerrox.blueberry.registry.ModBlockEntityTypes
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.ClientTickEvent
import net.neoforged.neoforge.client.event.EntityRenderersEvent

@EventBusSubscriber(modid = Blueberry.MOD_ID, value = [Dist.CLIENT], bus = EventBusSubscriber.Bus.MOD)
object x {

    @SubscribeEvent
    fun a(event: EntityRenderersEvent.RegisterRenderers) {
        event.registerBlockEntityRenderer(ModBlockEntityTypes.FLUID_TANK.get(), ::FluidTankRenderer)
    }

}