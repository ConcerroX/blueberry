package concerrox.blueberry.earlywindow

import concerrox.blueberry.main.BlueberryLogistics
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod

@Mod(BlueberryLogistics.MOD_ID)
class BlueberryLogisticsEntrypoint(modEventBus: IEventBus, modContainer: ModContainer) {

    init {
        BlueberryLogistics.initialize(modEventBus, modContainer)
    }

}