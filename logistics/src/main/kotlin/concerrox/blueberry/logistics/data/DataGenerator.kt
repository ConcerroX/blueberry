package concerrox.blueberry.logistics.data

import net.neoforged.neoforge.data.event.GatherDataEvent

object DataGenerator {

    fun initialize(event: GatherDataEvent) {
        val generator = event.generator
        val output = generator.packOutput
        val existingFileHelper = event.existingFileHelper

        event.addProvider(ModBlockStateProvider(output, existingFileHelper))
        event.addProvider(ModItemModelProvider(output, existingFileHelper))
    }

}