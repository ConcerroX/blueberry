package concerrox.blueberry

import com.buuz135.functionalstorage.block.tile.DrawerTile
import com.buuz135.functionalstorage.client.DrawerRenderer
import com.jerry.mekextras.client.render.transmitter.*
import com.jerry.mekextras.common.registries.ExtraBlocks
import com.jerry.mekextras.common.registries.ExtraTileEntityTypes
import com.jerry.mekextras.common.tile.transmitter.ExtraTileEntityLogisticalTransporter
import concerrox.blueberry.content.SBER
import concerrox.blueberry.content.rail.RailPlacementHelper
import concerrox.blueberry.registry.ModBlockEntityTypes
import concerrox.blueberry.registry.ModBlocks
import concerrox.blueberry.registry.ModTileEntityTypes
import mekanism.client.ClientRegistrationUtil
import mekanism.client.render.item.TransmitterTypeDecorator
import mekanism.client.render.transmitter.RenderMechanicalPipe
import mekanism.common.util.WorldUtils
import net.createmod.catnip.placement.PlacementHelpers
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockAndTintGetter
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent
import net.neoforged.neoforge.client.gui.ConfigurationScreen
import net.neoforged.neoforge.client.gui.IConfigScreenFactory


@Mod(value = Blueberry.MOD_ID, dist = [Dist.CLIENT])
@EventBusSubscriber(modid = Blueberry.MOD_ID, value = [Dist.CLIENT])
class BlueberryClient(container: ModContainer) {

    init {
        PlacementHelpers.register(RailPlacementHelper())
        container.eventBus?.addListener(::bindTileEntityRenderers)
        container.eventBus!!.addListener { e: EntityRenderersEvent.RegisterRenderers ->
            registerRenderers(e)
        }
        container.eventBus!!.addListener(::registerBlockColorHandlers)
        container.eventBus!!.addListener(::registerItemDecorations)

        container.registerExtensionPoint(
            IConfigScreenFactory::class.java, IConfigScreenFactory { mod, parent -> ConfigurationScreen(mod, parent) })
    }

    fun bindTileEntityRenderers(event: EntityRenderersEvent.RegisterRenderers) {
        ClientRegistrationUtil.bindTileEntityRenderer(
            event, ::RenderMechanicalPipe, ModTileEntityTypes.WINDOWED_COPPER_PRESSURED_PIPE
        )
    }

    @SubscribeEvent
    @Throws(Exception::class)
    fun registerRenderers(event: EntityRenderersEvent.RegisterRenderers) {
        event.registerBlockEntityRenderer(ModBlockEntityTypes.SIGN.get(), ::SBER)

        event.registerBlockEntityRenderer(
            ModBlocks.X_1_DRAWER.type().get() as BlockEntityType<out DrawerTile?>
        ) { p_173571_ -> DrawerRenderer() }
        event.registerBlockEntityRenderer(
            ModBlocks.X_4_DRAWER.type().get() as BlockEntityType<out DrawerTile?>
        ) { p_173571_ -> DrawerRenderer() }


        //Transmitters
        ClientRegistrationUtil.bindTileEntityRenderer(
            event,
            { context: BlockEntityRendererProvider.Context? ->
                ExtraRenderLogisticalTransporter(context)
            },
            ExtraTileEntityTypes.ABSOLUTE_LOGISTICAL_TRANSPORTER,
            ExtraTileEntityTypes.SUPREME_LOGISTICAL_TRANSPORTER,
            ExtraTileEntityTypes.COSMIC_LOGISTICAL_TRANSPORTER,
            ExtraTileEntityTypes.INFINITE_LOGISTICAL_TRANSPORTER
        )
        ClientRegistrationUtil.bindTileEntityRenderer(
            event,
            { context: BlockEntityRendererProvider.Context? ->
                ExtraRenderMechanicalPipe(context)
            },
            ExtraTileEntityTypes.ABSOLUTE_MECHANICAL_PIPE,
            ExtraTileEntityTypes.SUPREME_MECHANICAL_PIPE,
            ExtraTileEntityTypes.COSMIC_MECHANICAL_PIPE,
            ExtraTileEntityTypes.INFINITE_MECHANICAL_PIPE
        )
        ClientRegistrationUtil.bindTileEntityRenderer(
            event,
            { context: BlockEntityRendererProvider.Context? ->
                ExtraRenderPressurizedTube(context)
            },
            ExtraTileEntityTypes.ABSOLUTE_PRESSURIZED_TUBE,
            ExtraTileEntityTypes.SUPREME_PRESSURIZED_TUBE,
            ExtraTileEntityTypes.COSMIC_PRESSURIZED_TUBE,
            ExtraTileEntityTypes.INFINITE_PRESSURIZED_TUBE
        )
        ClientRegistrationUtil.bindTileEntityRenderer(
            event,
            { context: BlockEntityRendererProvider.Context? ->
                ExtraRenderUniversalCable(context)
            },
            ExtraTileEntityTypes.ABSOLUTE_UNIVERSAL_CABLE,
            ExtraTileEntityTypes.SUPREME_UNIVERSAL_CABLE,
            ExtraTileEntityTypes.COSMIC_UNIVERSAL_CABLE,
            ExtraTileEntityTypes.INFINITE_UNIVERSAL_CABLE
        )
        ClientRegistrationUtil.bindTileEntityRenderer(
            event,
            { context: BlockEntityRendererProvider.Context? ->
                ExtraRenderThermodynamicConductor(context)
            },
            ExtraTileEntityTypes.ABSOLUTE_THERMODYNAMIC_CONDUCTOR,
            ExtraTileEntityTypes.SUPREME_THERMODYNAMIC_CONDUCTOR,
            ExtraTileEntityTypes.COSMIC_THERMODYNAMIC_CONDUCTOR,
            ExtraTileEntityTypes.INFINITE_THERMODYNAMIC_CONDUCTOR
        )
    }

    @SubscribeEvent
    fun registerBlockColorHandlers(event: RegisterColorHandlersEvent.Block) {
        ClientRegistrationUtil.registerBlockColorHandler(
            event,
            { state: BlockState?, world: BlockAndTintGetter?, pos: BlockPos?, tintIndex: Int ->
                if (tintIndex == 1 && pos != null) {
                    val transporter = WorldUtils.getTileEntity(
                        ExtraTileEntityLogisticalTransporter::class.java, world, pos
                    )
                    if (transporter != null) {
                        val renderColor = transporter.transmitter.color
                        if (renderColor != null) {
                            return@registerBlockColorHandler renderColor.packedColor
                        }
                    }
                }
                -1
            },
            ExtraBlocks.ABSOLUTE_LOGISTICAL_TRANSPORTER,
            ExtraBlocks.SUPREME_LOGISTICAL_TRANSPORTER,
            ExtraBlocks.COSMIC_LOGISTICAL_TRANSPORTER,
            ExtraBlocks.INFINITE_LOGISTICAL_TRANSPORTER
        )
    }

    @SubscribeEvent
    fun registerItemColorHandlers(event: RegisterColorHandlersEvent.Item) {
//        ClientRegistrationUtil.registerBucketColorHandler(event, MekanismFluids.FLUIDS)
    }

    @SubscribeEvent
    fun registerItemDecorations(event: RegisterItemDecorationsEvent) {
        TransmitterTypeDecorator.registerDecorators(
            event,
            ExtraBlocks.ABSOLUTE_PRESSURIZED_TUBE,
            ExtraBlocks.SUPREME_PRESSURIZED_TUBE,
            ExtraBlocks.COSMIC_PRESSURIZED_TUBE,
            ExtraBlocks.INFINITE_PRESSURIZED_TUBE,
            ExtraBlocks.ABSOLUTE_THERMODYNAMIC_CONDUCTOR,
            ExtraBlocks.SUPREME_THERMODYNAMIC_CONDUCTOR,
            ExtraBlocks.COSMIC_THERMODYNAMIC_CONDUCTOR,
            ExtraBlocks.INFINITE_THERMODYNAMIC_CONDUCTOR,
            ExtraBlocks.ABSOLUTE_UNIVERSAL_CABLE,
            ExtraBlocks.SUPREME_UNIVERSAL_CABLE,
            ExtraBlocks.COSMIC_UNIVERSAL_CABLE,
            ExtraBlocks.INFINITE_UNIVERSAL_CABLE
        )
    }

}