package concerrox.blueberry.registry

import com.simibubi.create.content.fluids.tankf.ChemicalTankBlockEntity
import com.simibubi.create.content.fluids.tankf.CreativeFluidTankBlockEntity
import com.simibubi.create.content.fluids.tankf.FluidTankRenderer
import com.tterrag.registrate.util.entry.BlockEntityEntry
import com.tterrag.registrate.util.nullness.NonNullFunction
import concerrox.blueberry.Blueberry.Companion.MOD_ID
import concerrox.blueberry.content.SignBlockEntity2
import concerrox.blueberry.registry.ModBlocks.REGISTRATE
import concerrox.blueberry.util.new
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.SignBlockEntity
import net.neoforged.neoforge.registries.DeferredRegister

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object ModBlockEntityTypes {

    val BLOCK_ENTITY_TYPES: DeferredRegister<BlockEntityType<*>> =
        DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID)

    val SIGN = BLOCK_ENTITY_TYPES.new("sign") {
        BlockEntityType.Builder.of(::SignBlockEntity2, ModBlocks.LOGSIGN.get()).build(null)
    }

//    val X_1_DRAWER = BLOCK_ENTITY_TYPES.new("x_1_drawer", ModBlocks.X_1_DRAWER.get().tileEntityFactory)

    @JvmField
    val FLUID_TANK: BlockEntityEntry<ChemicalTankBlockEntity> = REGISTRATE.blockEntity<ChemicalTankBlockEntity>(
        "fluid_tank", ::ChemicalTankBlockEntity
    ).validBlocks(ModBlocks.FLUID_TANK).renderer { NonNullFunction { a -> FluidTankRenderer(a) } }.register()

    @JvmField
    val CREATIVE_FLUID_TANK: BlockEntityEntry<CreativeFluidTankBlockEntity> =
        REGISTRATE.blockEntity<CreativeFluidTankBlockEntity>("creative_fluid_tank", ::CreativeFluidTankBlockEntity)
            .validBlocks(ModBlocks.CREATIVE_FLUID_TANK).renderer { NonNullFunction { a -> FluidTankRenderer(a) } }
            .register()


//    @JvmField
//    val FLUID_TANK2: RegistryEntry<MountedChemicalStorageType<*>, MountedChemicalStorageType> =
//        simpleFluid(
//            "fluid_tank",
//            Supplier<FluidTankMountedStorageType> { FluidTankMountedStorageType() })
//    @JvmField
//    val CREATIVE_FLUID_TANK2: RegistryEntry<MountedChemicalStorageType<*>, CreativeFluidTankMountedStorageType> =
//        simpleFluid<CreativeFluidTankMountedStorageType>(
//            "creative_fluid_tank",
//            Supplier<CreativeFluidTankMountedStorageType> { CreativeFluidTankMountedStorageType() })
//
//    private fun <T : MountedChemicalStorageType<*>?> simpleFluid(
//        name: String,
//        supplier: Supplier<T>
//    ): RegistryEntry<MountedFluidStorageType<*>, T> {
//        return CreateRegistrate.create(Blueberry.MOD_ID).mountedFluidStorage(name, supplier).register()
//    }

}