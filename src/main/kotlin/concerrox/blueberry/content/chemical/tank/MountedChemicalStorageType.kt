package concerrox.blueberry.content.chemical.tank

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.simibubi.create.api.registry.CreateBuiltInRegistries
import com.simibubi.create.api.registry.CreateRegistries
import com.simibubi.create.api.registry.SimpleRegistry
import com.tterrag.registrate.builders.BlockBuilder
import com.tterrag.registrate.util.entry.RegistryEntry
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

abstract class MountedChemicalStorageType<T : MountedChemicalStorage?> protected constructor(val codec: MapCodec<out T?>) {
    abstract fun mount(level: Level?, state: BlockState?, pos: BlockPos?, be: BlockEntity?): T?

    companion object {
//        val CODEC: Codec<MountedChemicalStorageType<*>> = CreateBuiltInRegistries.MOUNTED_FLUID_STORAGE_TYPE.byNameCodec()
        val REGISTRY: SimpleRegistry<Block, MountedChemicalStorageType<*>> = SimpleRegistry.create()

        /**
         * Utility for use with Registrate builders. Creates a builder transformer
         * that will register the given MountedFluidStorageType to a block when ready.
         */
        fun <B : Block?, P> mountedFluidStorage(type: RegistryEntry<MountedChemicalStorageType<*>?, out MountedChemicalStorageType<*>>): NonNullUnaryOperator<BlockBuilder<B, P>> {
            return NonNullUnaryOperator { builder: BlockBuilder<B, P> ->
                builder.onRegisterAfter(
                    CreateRegistries.MOUNTED_FLUID_STORAGE_TYPE
                ) { block: B ->
                    REGISTRY.register(
                        block,
                        type.get()
                    )
                }
            }
        }
    }
}