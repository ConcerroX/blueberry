package com.djinfinite.manors_bounty

import net.neoforged.api.distmarker.Dist
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.client.gui.ConfigurationScreen
import net.neoforged.neoforge.client.gui.IConfigScreenFactory

@Mod(value = ManorsBounty.ID, dist = [Dist.CLIENT])
class ManorsBountyClient(container: ModContainer) {

    init {
        container.registerExtensionPoint(IConfigScreenFactory::class.java,
            IConfigScreenFactory { mod, parent -> ConfigurationScreen(mod, parent) })
    }

}