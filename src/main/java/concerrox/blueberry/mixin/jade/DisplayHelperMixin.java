package concerrox.blueberry.mixin.jade;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import snownee.jade.overlay.DisplayHelper;

@Mixin(value = DisplayHelper.class, remap = false)
public class DisplayHelperMixin {

//    @WrapOperation(method = "humanReadableNumber(DLjava/lang/String;ZLjava/text/Format;)Ljava/lang/String;",
//            at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(C)Ljava/lang/StringBuilder;",
//                    ordinal = 2))
//    private static StringBuilder humanReadableNumber(StringBuilder instance, char c,
//                                                     Operation<StringBuilder> original) {
//        instance.append(' ');
//        return original.call(instance, c);
//    }

    @WrapOperation(method = "humanReadableNumber(DLjava/lang/String;ZLjava/text/Format;)Ljava/lang/String;",
            at = @At(value = "INVOKE",
                    target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;",
                    ordinal = 2))
    private static StringBuilder humanReadableNumber(StringBuilder instance, String str,
                                                     Operation<StringBuilder> original) {
        if (str.equals("⚡")) return instance;//.insert(0, str + " ");
        else return original.call(instance, str);
    }

}
