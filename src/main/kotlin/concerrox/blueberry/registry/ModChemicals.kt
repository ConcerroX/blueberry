package concerrox.blueberry.registry

import concerrox.blueberry.Blueberry
import concerrox.blueberry.res
import concerrox.blueberry.util.new
import mekanism.api.MekanismAPI
import mekanism.api.chemical.Chemical
import mekanism.api.chemical.ChemicalBuilder
import net.neoforged.neoforge.registries.DeferredRegister

object ModChemicals {

    private fun simpleChemical(path: String) =
        CHEMICALS.new(path) { Chemical(ChemicalBuilder.builder(res("block/fluids/$path"))) }

    val CHEMICALS: DeferredRegister<Chemical> =
        DeferredRegister.create(MekanismAPI.CHEMICAL_REGISTRY_NAME, Blueberry.MOD_ID)

    // Gases
    val STEAM = simpleChemical("steam")
    val WARM_STEAM = simpleChemical("warm_steam")
    val HOT_STEAM = simpleChemical("hot_steam")
    val SUPER_HOT_STEAM = simpleChemical("super_hot_steam")
    val PLASMA_STEAM = simpleChemical("plasma_steam")

}