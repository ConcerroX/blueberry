package concerrox.blueberry.earlywindow.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ReloadInstance;
import net.neoforged.fml.earlydisplay.DisplayWindow;
import net.neoforged.neoforge.client.loading.NeoForgeLoadingOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.function.Consumer;

@Mixin(NeoForgeLoadingOverlay.class)
public class NeoForgeLoadingOverlayMixin {

    //    @Nullable
//    @Unique
//    private SimpleCustomEarlyLoadingWindow stargateWindow;
    @Shadow
    private long fadeOutStart;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(Minecraft mc, ReloadInstance reloader, Consumer<Optional<Throwable>> errorConsumer,
                     DisplayWindow displayWindow, CallbackInfo ci) throws Exception {
//        if (displayWindow instanceof SimpleCustomEarlyLoadingWindow stargateEarlyLoadingWindow) {
//            stargateWindow = stargateEarlyLoadingWindow;
        fadeOutStart = -2L;
//        } else {
//            stargateWindow = null;
//        }
    }

//    @Inject(method = "render", at = @At("RETURN"))
//    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) throws Exception {
////        if (stargateWindow == null) return; // how the hell this happened?
////        throw new Exception("");
//        if (stargateWindow.isFinished && fadeOutStart < 0) {
//            fadeOutStart = -1L;
////        }
//    }

}