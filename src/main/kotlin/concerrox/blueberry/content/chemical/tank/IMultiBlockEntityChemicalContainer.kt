package concerrox.blueberry.content.chemical.tank

import com.simibubi.create.foundation.blockEntity.IMultiBlockEntityContainer
import mekanism.api.chemical.ChemicalStack
import mekanism.api.chemical.IChemicalTank

interface IMultiBlockEntityChemicalContainer : IMultiBlockEntityContainer {

    fun hasTank(): Boolean {
        return false
    }

    fun getTankSize(tank: Int): Int {
        return 0
    }

    fun setTankSize(tank: Int, blocks: Int) {}

    fun getTank(tank: Int): IChemicalTank? {
        return null
    }

    fun getFluid(tank: Int): ChemicalStack {
        return ChemicalStack.EMPTY
    }

}