package concerrox.blueberry.mixin.jade;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import snownee.jade.util.CommonProxy;

@Mixin(value = CommonProxy.class, remap = false)
public class CommonProxyMixin {

    @ModifyConstant(method = "wrapEnergyStorage", constant = @Constant(stringValue = "FE"))
    private static String wrapEnergyStorage(String constant) {
        return "⚡";
    }

}
