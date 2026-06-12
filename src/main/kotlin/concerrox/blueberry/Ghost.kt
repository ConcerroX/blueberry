package concerrox.blueberry

import concerrox.blueberry.registry.ModChemicals
import mekanism.client.render.MekanismRenderer
import net.createmod.catnip.ghostblock.GhostBlocks
import net.minecraft.client.Minecraft
import net.minecraft.world.InteractionHand
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.phys.BlockHitResult
import net.neoforged.fml.common.Mod


object Ghost {

    fun doth() {
        val minecraft = Minecraft.getInstance()
        val level = minecraft.level ?: return
        val player = minecraft.player ?: return
        val hitResult = minecraft.hitResult ?: return
        if (player.isShiftKeyDown) return
        if (hitResult !is BlockHitResult) return

        for (hand in InteractionHand.entries) {
            val heldStack = player.getItemInHand(hand)
            val pos = hitResult.blockPos
            val state = level.getBlockState(pos)
            GhostBlocks.getInstance().showGhostState(this, Blocks.RAIL.defaultBlockState())
                .at(pos)
                .breathingAlpha()
        }
    }


}