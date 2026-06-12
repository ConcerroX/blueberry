package concerrox.blueberry.content.rail;

import net.createmod.catnip.placement.IPlacementHelper
import net.createmod.catnip.placement.PlacementOffset
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.RailBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import java.util.function.Predicate

class RailPlacementHelper : IPlacementHelper {

    override fun getItemPredicate() = Predicate<ItemStack> {
        val item = it.item
        if (item !is BlockItem) false else item.block is RailBlock
    }

    override fun getStatePredicate() = Predicate<BlockState> {
        true
    }

    override fun getOffset(
        player: Player, world: Level, state: BlockState, pos: BlockPos, ray: BlockHitResult
    ): PlacementOffset {
        return PlacementOffset.success(pos.above())
    }

    override fun displayGhost(offset: PlacementOffset) {
        super.displayGhost(offset)
    }

}
