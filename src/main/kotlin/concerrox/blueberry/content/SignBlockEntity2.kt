package concerrox.blueberry.content

import concerrox.blueberry.registry.ModBlockEntityTypes
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.SignBlockEntity
import net.minecraft.world.level.block.state.BlockState

class SignBlockEntity2(pos: BlockPos, blockState: BlockState) : SignBlockEntity(pos, blockState) {

    override fun getType(): BlockEntityType<*> {
        return ModBlockEntityTypes.SIGN.get()
    }

}