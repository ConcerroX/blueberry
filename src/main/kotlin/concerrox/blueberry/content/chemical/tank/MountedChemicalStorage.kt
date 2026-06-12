package concerrox.blueberry.content.chemical.tank

import com.mojang.serialization.Codec
import mekanism.api.chemical.IChemicalHandler
import net.minecraft.core.BlockPos
import net.minecraft.nbt.NbtOps
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.RegistryOps
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import java.util.*

abstract class MountedChemicalStorage protected constructor(type: MountedChemicalStorageType<*>?) : IChemicalHandler {
    val type: MountedChemicalStorageType<out MountedChemicalStorage?> = Objects.requireNonNull(type)!!

    /**
     * Un-mount this storage back into the world. The expected storage type of the target
     * block has already been checked to make sure it matches this storage's type.
     */
    abstract fun unmount(level: Level?, state: BlockState?, pos: BlockPos?, be: BlockEntity?)

    companion object {
//        val CODEC: Codec<MountedChemicalStorage> = MountedChemicalStorageType.CODEC.dispatch(
//            { storage: MountedChemicalStorage -> storage.type },
//            { type: MountedChemicalStorageType<*> -> type.codec }
//        )

//        @Suppress("deprecation")
//        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, MountedChemicalStorage> = StreamCodec.of(
//            { b: RegistryFriendlyByteBuf, t: MountedChemicalStorage ->
//                b.writeWithCodec(
//                    RegistryOps.create(
//                        NbtOps.INSTANCE,
//                        b.registryAccess()
//                    ), CODEC, t
//                )
//            },
//            { b: RegistryFriendlyByteBuf ->
//                b.readWithCodecTrusted(
//                    RegistryOps.create(
//                        NbtOps.INSTANCE,
//                        b.registryAccess()
//                    ), CODEC
//                )
//            }
//        )
    }
}