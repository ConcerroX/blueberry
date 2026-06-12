package concerrox.blueberry.content

import net.minecraft.Util
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.WallSignBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.WoodType

class WallSignBlock2(properties: Properties) : WallSignBlock(WoodType.OAK, properties) {

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return SignBlockEntity2(pos, state)
    }

    override fun getDescriptionId(): String {
        return Util.makeDescriptionId("block", BuiltInRegistries.BLOCK.getKey(this))
    }

}