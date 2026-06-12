package concerrox.blueberry.earlywindow;

import net.neoforged.fml.earlydisplay.DisplayWindow;
import net.neoforged.neoforgespi.earlywindow.ImmediateWindowProvider;
import org.jetbrains.annotations.Nullable;

public class BlueberryEarlyWindow extends DisplayWindow implements ImmediateWindowProvider {

    public static final String WINDOW_PROVIDER = "blueberry";

    @Override
    public String name() {
        return WINDOW_PROVIDER;
    }

    @Override
    public Runnable start(@Nullable String mcVersion, String forgeVersion) {
        return this::periodicTick;
    }

}