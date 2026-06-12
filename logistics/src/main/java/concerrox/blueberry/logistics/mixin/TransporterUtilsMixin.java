package concerrox.blueberry.logistics.mixin;

import mekanism.common.util.TransporterUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = TransporterUtils.class, remap = false)
public class TransporterUtilsMixin {

    @ModifyConstant(method = "getStackPosition", constant = @Constant(floatValue = 0.25f))
    private static float modifyStackPosition(float original) {
        return 0.125f;
    }

}
