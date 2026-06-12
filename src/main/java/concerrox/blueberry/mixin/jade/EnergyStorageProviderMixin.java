package concerrox.blueberry.mixin.jade;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import snownee.jade.addon.universal.EnergyStorageProvider;

@Mixin(value = EnergyStorageProvider.class, remap = false)
public class EnergyStorageProviderMixin {

    @ModifyConstant(method = "lambda$append$0", constant = @Constant(stringValue = "jade.fe"))
    private static String wrapEnergyStorage(String constant) {
        return "jade.fe.new";
    }

}
