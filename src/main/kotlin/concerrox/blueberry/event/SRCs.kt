package concerrox.blueberry.event

import com.simibubi.create.content.fluids.tankf.ChemicalTankBlockEntity
import concerrox.blueberry.Blueberry
import concerrox.blueberry.registry.ModBlockEntityTypes
import mekanism.common.capabilities.Capabilities
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent

@EventBusSubscriber(modid = Blueberry.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
object SRCs {

    @SubscribeEvent
    fun registerCapabilities(event: RegisterCapabilitiesEvent) {
        event.registerBlockEntity(
            Capabilities.CHEMICAL.block, ModBlockEntityTypes.FLUID_TANK.get()
        ) { be, _ ->
            if (be.chemicalCapability == null) be.refreshCapability()
            be.chemicalCapability
        }
    }

}