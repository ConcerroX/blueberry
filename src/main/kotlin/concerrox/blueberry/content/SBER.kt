package concerrox.blueberry.content

import com.mojang.blaze3d.vertex.PoseStack
import concerrox.blueberry.res
import concerrox.keytips.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.model.Model
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.Sheets
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.blockentity.SignRenderer
import net.minecraft.client.resources.model.Material
import net.minecraft.core.BlockPos
import net.minecraft.util.FormattedCharSequence
import net.minecraft.world.level.block.entity.SignText
import net.minecraft.world.level.block.state.properties.WoodType

class SBER(context: BlockEntityRendererProvider.Context) : SignRenderer(context) {

    companion object {
        private val MATERIAL = Material(Sheets.SIGN_SHEET, res("entity/signs/log"))
    }

    override fun renderSign(
        poseStack: PoseStack,
        buffer: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int,
        woodType: WoodType,
        model: Model
    ) {
        poseStack.pushPose()
        val scale = signModelRenderScale
        poseStack.scale(scale, -scale, -scale)
        renderSignModel(poseStack, packedLight, packedOverlay, model, MATERIAL.buffer(buffer, model::renderType))
        poseStack.popPose()
    }

    override fun renderSignText(
        pos: BlockPos,
        text: SignText,
        poseStack: PoseStack,
        buffer: MultiBufferSource,
        packedLight: Int,
        lineHeight: Int,
        maxWidth: Int,
        isFrontText: Boolean
    ) {
        poseStack.pushPose()
        translateSignText(poseStack, isFrontText, textOffset)
        val backgroundColor = 0x68849F
        val yOffset = 4 * lineHeight / 2
        val formattedCharSequences = text.getRenderMessages(Minecraft.isTextFilteringEnabled) {
            val list = font.split(it, maxWidth)
            if (list.isEmpty()) FormattedCharSequence.EMPTY else list[0]
        }
        val textColor: Int
        val isOutlineVisible: Boolean
        val packedLightCoords: Int
        if (text.hasGlowingText()) {
            textColor = text.color.textColor
            isOutlineVisible = isOutlineVisible(pos, textColor)
            packedLightCoords = 15728880
        } else {
            textColor = backgroundColor
            isOutlineVisible = false
            packedLightCoords = packedLight
        }

        for (line in 0..3) {
            val formattedCharSequence = formattedCharSequences[line]
            val xOffset = -font.width(formattedCharSequence) / 2f
            if (isOutlineVisible) {
                font.drawInBatch8xOutline(
                    formattedCharSequence,
                    xOffset,
                    (line * lineHeight - yOffset).toFloat(),
                    textColor,
                    backgroundColor,
                    poseStack.last().pose(),
                    buffer,
                    packedLightCoords
                )
            } else {
                font.drawInBatch(
                    formattedCharSequence,
                    xOffset,
                    (line * lineHeight - yOffset).toFloat(),
                    textColor,
                    false,
                    poseStack.last().pose(),
                    buffer,
                    Font.DisplayMode.POLYGON_OFFSET,
                    0,
                    packedLightCoords
                )
            }
        }

        poseStack.popPose()
    }

}