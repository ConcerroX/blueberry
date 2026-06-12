package concerrox.blueberry.content.chemical.tank

import mekanism.api.Action
import mekanism.api.chemical.ChemicalStack
import mekanism.api.chemical.IChemicalHandler

/**
 * Partial implementation of a MountedFluidStorage that wraps a fluid handler.
 */
abstract class WrapperMountedChemicalStorage<T : IChemicalHandler?> protected constructor(
    type: MountedChemicalStorageType<*>,
    protected val wrapped: T
) :
    MountedChemicalStorage(type) {

    override fun getChemicalTanks(): Int {
        return wrapped!!.chemicalTanks
    }

    override fun setChemicalInTank(tank: Int, stack: ChemicalStack) {
        return wrapped!!.setChemicalInTank(tank, stack)
    }

    override fun getChemicalInTank(tank: Int): ChemicalStack {
        return wrapped!!.getChemicalInTank(tank)
    }

    override fun getChemicalTankCapacity(tank: Int): Long {
        return wrapped!!.getChemicalTankCapacity(tank)
    }

    override fun isValid(tank: Int, stack: ChemicalStack): Boolean {
        return wrapped!!.isValid(tank, stack)
    }

    override fun insertChemical(tank: Int, stack: ChemicalStack, action: Action): ChemicalStack {
        return wrapped!!.insertChemical(tank, stack, action)
    }

    override fun insertChemical(resource: ChemicalStack, action: Action): ChemicalStack {
        return wrapped!!.insertChemical(resource, action)
    }

    override fun extractChemical(resource: ChemicalStack, action: Action): ChemicalStack {
        return wrapped!!.extractChemical(resource, action)
    }

    override fun extractChemical(maxDrain: Long, action: Action): ChemicalStack {
        return wrapped!!.extractChemical(maxDrain, action)
    }

    override fun extractChemical(tank: Int, amount: Long, action: Action): ChemicalStack {
        return wrapped!!.extractChemical(tank, amount, action)
    }

}