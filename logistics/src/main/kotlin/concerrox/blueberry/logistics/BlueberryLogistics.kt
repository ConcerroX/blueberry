package concerrox.blueberry.logistics

import concerrox.blueberry.logistics.data.DataGenerator
import concerrox.blueberry.logistics.registry.ModBlocks
import concerrox.blueberry.logistics.registry.ModTileEntityTypes
import mekanism.client.ClientRegistrationUtil
import mekanism.client.render.transmitter.RenderLogisticalTransporter
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.loading.FMLEnvironment
import net.neoforged.neoforge.client.event.EntityRenderersEvent

object BlueberryLogistics {

    const val MOD_ID = "blueberry_logistics"

    fun initialize(modEventBus: IEventBus, modContainer: ModContainer) {
        ModBlocks.MEKANISM_BLOCKS.register(modEventBus)
        ModTileEntityTypes.MEKANISM_TILE_ENTITY_TYPES.register(modEventBus)

        modEventBus.addListener(DataGenerator::initialize)

        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener { event: EntityRenderersEvent.RegisterRenderers ->
                ClientRegistrationUtil.bindTileEntityRenderer(
                    event,
                    ::RenderLogisticalTransporter,
                    ModTileEntityTypes.WINDOWED_ANDESITE_ITEM_DUCT,
                    ModTileEntityTypes.WINDOWED_BRASS_ITEM_DUCT,
                )
            }
        }
    }

}