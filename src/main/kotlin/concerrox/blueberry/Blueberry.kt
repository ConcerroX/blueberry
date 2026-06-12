package concerrox.blueberry

import com.jerry.mekextras.MekanismExtras
import com.mojang.logging.LogUtils
import concerrox.blueberry.Blueberry.Companion.MOD_ID
import concerrox.blueberry.logistics.BlueberryLogistics
import concerrox.blueberry.registry.*
import concerrox.keytips.KeyTips
import net.minecraft.resources.ResourceLocation
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.config.ModConfig
import org.slf4j.Logger

internal fun res(path: String): ResourceLocation {
    return ResourceLocation.fromNamespaceAndPath(MOD_ID, path)
}

@Mod(MOD_ID)
class Blueberry(modEventBus: IEventBus, modContainer: ModContainer) {

    init {
        BlueberryLogistics.initialize(modEventBus, modContainer)

        KeyTips(modEventBus, modContainer)

        var mekExtras = MekanismExtras(modContainer, modEventBus)

        ModBlocks.BLOCKS.register(modEventBus)
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus)

        ModBlocks.EXTRA_BLOCKS.register(modEventBus)

        ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus)

        ModItems.ITEMS.register(modEventBus)
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus)

        ModBlockEntityTypes.FLUID_TANK

        ModChemicals.CHEMICALS.register(modEventBus)
        ModChemicals.STEAM

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC)
    }

//    @EventBusSubscriber(modid = ID, bus = EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
//    object ClientModEvents {
//        @SubscribeEvent
//        fun onClientSetup(event: FMLClientSetupEvent?) {
//            LOGGER.info("HELLO FROM CLIENT SETUP")
//            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().user.name)
//            event.
//        }
//    }

    companion object {
        const val MOD_ID: String = "blueberry"
        private val LOGGER: Logger = LogUtils.getLogger()
    }

}
