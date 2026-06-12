package concerrox.blueberry.mixin.mekanism;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.transmitter.RenderMechanicalPipe;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidStackLinkedSet;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;

import java.util.Map;

@Mixin(value = RenderMechanicalPipe.class, remap = false)
public class RenderMechanicalPipeMixin {

    @Shadow @Final private static Int2ObjectMap<Map<FluidStack, Int2ObjectMap<MekanismRenderer.Model3D>>> cachedLiquids;

    @Shadow @Final private static int stages;

    @Unique
    private static final float blueberry$height = 0.370F;
    @Unique
    private static final float blueberry$offset = 0.0025F;

    /**
     * @author ConcerroX
     * @reason To modify those constants
     */
    @Overwrite
    private MekanismRenderer.Model3D getModel(@Nullable Direction side, FluidStack fluid, int stage, boolean renderBase) {
        int sideOrdinal;
        if (side == null) {
            sideOrdinal = renderBase ? 7 : 6;
        } else {
            sideOrdinal = side.ordinal();
        }
        Int2ObjectMap<MekanismRenderer.Model3D> modelMap = cachedLiquids.computeIfAbsent(sideOrdinal, s -> new Object2ObjectOpenCustomHashMap<>(
                FluidStackLinkedSet.TYPE_AND_COMPONENTS))
            .computeIfAbsent(fluid, f -> new Int2ObjectOpenHashMap<>());
        MekanismRenderer.Model3D model = modelMap.get(stage);
        if (model == null) {
            model = new MekanismRenderer.Model3D().setTexture(MekanismRenderer.getFluidTexture(fluid, MekanismRenderer.FluidTextureType.STILL));
            float stageRatio = (stage / (float) stages) * blueberry$height;
            if (side == null) {
                float min;
                float max;
                if (renderBase) {
                    min = 0.3125F + blueberry$offset;
                    max = 0.6875F - blueberry$offset;
                } else {
                    min = 0.3125F - stageRatio / 2;
                    max = 0.6875F + stageRatio / 2;
                }
                return model.xBounds(min, max)
                    .yBounds(0.3125F + blueberry$offset, 0.3125F + blueberry$offset + stageRatio)
                    .zBounds(min, max);
            }
            model.setSideRender(side, false)
                .setSideRender(side.getOpposite(), false);
            if (side.getAxis().isHorizontal()) {
                model.yBounds(0.3125F + blueberry$offset, 0.3125F + blueberry$offset + stageRatio);
                if (side.getAxis() == Direction.Axis.Z) {
                    return setHorizontalBounds(side, model::xBounds, model::zBounds);
                }
                return setHorizontalBounds(side, model::zBounds, model::xBounds);
            }
            float min = 0.5F - stageRatio / 2;
            float max = 0.5F + stageRatio / 2;
            model.xBounds(min, max)
                .zBounds(min, max);
            if (side == Direction.DOWN) {
                model.yBounds(0, 0.3125F + blueberry$offset);
            } else {//Up
                model.yBounds(0.3125F + blueberry$offset + stageRatio, 1);
            }
            modelMap.put(stage, model);
        }
        return model;
    }

    /**
     * @author ConcerroX
     * @reason To modify those constants
     */
    @Overwrite
    private static MekanismRenderer.Model3D setHorizontalBounds(Direction horizontal, MekanismRenderer.Model3D.ModelBoundsSetter axisBased, MekanismRenderer.Model3D.ModelBoundsSetter directionBased) {
        axisBased.set(0.3125F + blueberry$offset, 0.6875F - blueberry$offset);
        if (horizontal.getAxisDirection() == Direction.AxisDirection.POSITIVE) {
            return directionBased.set(0.6875F - blueberry$offset, 1);
        }
        return directionBased.set(0, 0.3125F + blueberry$offset);
    }

}
