package concerrox.blueberry.mixin.integration.sophisticatedbackpacks;

import concerrox.blueberry.integration.sophisticatedbackpacks.InventoryMenuManager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryMenu.class)
public abstract class InventoryMenuMixin extends AbstractContainerMenu {

    protected InventoryMenuMixin(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    @Inject(at = @At("TAIL"), method = "<init>")
    public void addBackpackSlots(Inventory playerInventory, boolean active, Player owner, CallbackInfo ci) {
        InventoryMenuManager.INSTANCE.onAddSlots((InventoryMenu) (AbstractContainerMenu) this, playerInventory, owner);
    }

}
