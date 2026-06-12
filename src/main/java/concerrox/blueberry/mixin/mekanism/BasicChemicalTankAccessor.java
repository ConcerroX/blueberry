package concerrox.blueberry.mixin.mekanism;

import mekanism.api.chemical.BasicChemicalTank;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BasicChemicalTank.class)
public interface BasicChemicalTankAccessor {

    @Mutable
    @Accessor("capacity")
    void setCapacity(long capacity);

}
