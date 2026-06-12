package com.simibubi.create.content.fluids.tankf;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;
import concerrox.blueberry.registry.ModChemicals;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.client.render.MekanismRenderer;
import net.createmod.catnip.animation.LerpedFloat;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.platform.NeoForgeCatnipServices;
import net.createmod.catnip.platform.services.ModFluidHelper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidStack;

import static net.createmod.catnip.render.FluidRenderHelper.getFluidBuilder;
import static net.createmod.catnip.render.FluidRenderHelper.renderStillTiledFace;

public class FluidTankRenderer extends SafeBlockEntityRenderer<ChemicalTankBlockEntity> {

    public FluidTankRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    protected void renderSafe(ChemicalTankBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
        int light, int overlay
    ) {
        if (!be.isController())
            return;
        if (!be.window) {
            return;
        }

        LerpedFloat fluidLevel = be.getChemicalLevel();
        if (fluidLevel == null)
            return;

        float capHeight = 1 / 4f;
        float tankHullWidth = 1 / 16f + 1 / 128f;
        float minPuddleHeight = 1 / 16f;
        float totalHeight = be.height - 2 * capHeight - minPuddleHeight;

        float level = fluidLevel.getValue(partialTicks);
        if (level < 1 / (512f * totalHeight))
            return;
        float clampedLevel = Mth.clamp(level * totalHeight, 0, totalHeight);

        BasicChemicalTank tank = be.tankInventory;
        ChemicalStack chemicalStack = tank.getStack();

        if (chemicalStack.isEmpty())
            return;

        		boolean top = true;//chemicalStack.getChemical()
        			//.isLighterThanAir();

        float xMin = tankHullWidth;
        float xMax = xMin + be.width - 2 * tankHullWidth;
        float yMin = totalHeight + capHeight + minPuddleHeight - clampedLevel;
        float yMax = yMin + clampedLevel;

        		if (top) {
        			yMin += totalHeight - clampedLevel;
        			yMax += totalHeight - clampedLevel;
        		}

        float zMin = tankHullWidth;
        float zMax = zMin + be.width - 2 * tankHullWidth;

        ms.pushPose();
        ms.translate(0, clampedLevel - totalHeight, 0);
//        NeoForgeCatnipServices.FLUID_RENDERER.renderFluidBox(new FluidStack(Fluids.WATER, 9999)
        //            //MekanismRenderer.getFluidTexture(new FluidStack(Fluids.WATER, 999),
        //            , xMin, yMin, zMin, xMax, yMax, zMax, buffer, ms, light, false, true);
        renderFluidBox(new FluidStack(Fluids.WATER, 9999), xMin, yMin, zMin, xMax, yMax, zMax, NeoForgeCatnipServices.FLUID_RENDERER.getFluidBuilder(buffer), ms, light, false,
            true);
        ms.popPose();
    }

    public void renderFluidBox(FluidStack fluid, float xMin, float yMin, float zMin, float xMax, float yMax, float zMax,
        VertexConsumer builder, PoseStack ms, int light, boolean renderBottom, boolean invertGasses) {
        ModFluidHelper<FluidStack> helper = NeoForgeCatnipServices.FLUID_HELPER;

        TextureAtlasSprite fluidTexture = MekanismRenderer.getChemicalTexture(ModChemicals.INSTANCE.getSTEAM());//helper.getStillTextureOrMissing(fluid);
        int color = helper.getColor(new FluidStack(Fluids.LAVA, 9999), null, null);

        int blockLightIn = (light >> 4) & 0xF;
        int luminosity = Math.max(blockLightIn, helper.getLuminosity(fluid));
        light = (light & 0xF00000) | luminosity << 4;

        Vec3 center = new Vec3(xMin + (xMax - xMin) / 2, yMin + (yMax - yMin) / 2, zMin + (zMax - zMin) / 2);
        ms.pushPose();
        if (invertGasses && true) {
            ms.translate(center.x, center.y, center.z);
            ms.mulPose(Axis.XP.rotationDegrees(180));
            ms.translate(-center.x, -center.y, -center.z);
        }

        for (Direction side : Iterate.directions) {
            if (side == Direction.DOWN && !renderBottom)
                continue;

            boolean positive = side.getAxisDirection() == Direction.AxisDirection.POSITIVE;
            if (side.getAxis()
                .isHorizontal()) {
                if (side.getAxis() == Direction.Axis.X) {
                    renderStillTiledFace(side, zMin, yMin, zMax, yMax, positive ? xMax : xMin,
                        builder, ms, light, color, fluidTexture);
                } else {
                    renderStillTiledFace(side, xMin, yMin, xMax, yMax, positive ? zMax : zMin,
                        builder, ms, light, color, fluidTexture);
                }
            } else {
                renderStillTiledFace(side, xMin, zMin, xMax, zMax, positive ? yMax : yMin,
                    builder, ms, light, color, fluidTexture);
            }
        }

        ms.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(ChemicalTankBlockEntity be) {
        return true;//be.isController();
    }

}
